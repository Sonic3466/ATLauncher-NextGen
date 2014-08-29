package com.atlauncher.thread;

import com.atlauncher.ATLauncher;
import com.atlauncher.Settings;
import com.atlauncher.obj.Downloadable;
import com.atlauncher.obj.Pack;
import com.atlauncher.obj.Pack.Version;
import com.atlauncher.obj.PackMeta;
import com.atlauncher.ui.diag.LoadingDialog;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

public final class InstanceInstallWorker
extends SwingWorker<Void, Void>{
    private final LoadingDialog diag;
    private final Pack pack;
    private final Version version;

    private final Path root;

    private PackMeta meta;

    public InstanceInstallWorker(final LoadingDialog diag, Pack pack, Version version){
        this.diag = diag;
        this.pack = pack;
        this.version = version;
        this.root = Settings.INSTANCES.resolve(pack.name);
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                diag.setVisible(true);
            }
        });
    }

    @Override
    protected Void doInBackground()
    throws Exception{
        ATLauncher.LOGGER.info("Starting Installation Process");
        ATLauncher.LOGGER.info("Creating Directory");
        Files.createDirectories(this.root);
        this.downloadConfigs();
        ATLauncher.LOGGER.info("Done Downloading Configuration");
        ATLauncher.LOGGER.info("Downloading Mods");
        this.downloadMods();
        ATLauncher.LOGGER.info("Done Downloading Mods");
        this.downloadLibs();
        ATLauncher.LOGGER.info("Done Downloading Libraries");
        return null;
    }

    @Override
    public void done(){
        this.diag.dispose();
    }

    private void downloadConfigs(){
        String path = String.format("packs/%s/versions/%s/Configs.zip", this.pack.getSafeName(), this.version.version);
        Path output = Settings.TMP.resolve("Configs.zip");
        Downloadable dl = new Downloadable(path, Settings.TMP, null, false);
        dl.run();
        unzip(this.root, output);
    }

    private void downloadLibs()
    throws Exception{
        this.diag.bar.setValue(0);
        this.diag.title.setText("Downloading Libraries");

        if(!Files.exists(Settings.LIBS)){
            Files.createDirectories(Settings.LIBS);
        }

        for(int i = 0; i < this.meta.libraries.length; i++){
            this.diag.bar.setValue(this.percent(i, this.meta.libraries.length));
            ATLauncher.TASKS.submit(this.meta.libraries[i].getDownload(Settings.LIBS));
        }
    }

    private void downloadMods(){
        try{
            this.diag.bar.setValue(0);
            this.diag.title.setText("Downloading Mods");
            String path = String.format("packs/%s/versions/%s/Configs.json", this.pack.getSafeName(), this.version.version);
            Path output = Settings.TMP.resolve("Configs.json");
            System.out.println(path);
            Downloadable dl = new Downloadable(path, Settings.TMP, null, false);
            dl.run();
            this.meta  = Settings.GSON.fromJson(new InputStreamReader(new FileInputStream(output.toFile())), PackMeta.class);
            Path mods = root.resolve("mods");
            if(!Files.exists(mods)){
                Files.createDirectories(mods);
            }
            for(int i = 0; i < this.meta.mods.length; i++){
                this.diag.bar.setValue(this.percent(i, this.meta.mods.length));
                ATLauncher.TASKS.submit(this.meta.mods[i].getDownload(mods));
            }
        } catch(Exception e){
            ATLauncher.LOGGER.error(e);
        }
    }

    private int percent(int i, int max){
        return (i * 100) / max;
    }

    private void unzip(Path dir, Path file){
        try(ZipInputStream zis = new ZipInputStream(new FileInputStream(file.toFile()))){
            ZipEntry e;
            while((e = zis.getNextEntry()) != null){
                Path out = dir.resolve(e.getName());

                if(e.isDirectory()){
                    Files.createDirectories(out);
                    continue;
                }

                try(FileChannel channel = FileChannel.open(out, StandardOpenOption.WRITE, StandardOpenOption.CREATE)){
                    ReadableByteChannel rbc = Channels.newChannel(zis);
                    channel.transferFrom(rbc, 0, Long.MAX_VALUE);
                }
            }
        } catch(IOException ex){
            ATLauncher.LOGGER.error(ex);
        }
    }
}