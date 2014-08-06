package com.atlauncher;

import com.atlauncher.obj.Downloadable;
import com.atlauncher.obj.FileHash;
import com.atlauncher.obj.News;
import com.atlauncher.obj.Server;
import com.atlauncher.utils.OS;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public final class Settings{
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public static final Properties properties = new Properties();

    public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.72 Safari/537.36";

    public static final Path CORE = OS.getStorageLocation();
    public static final Path DATA = CORE.resolve("data");
    public static final Path JSON = DATA.resolve("json");
    public static final Path IMAGES = DATA.resolve("image");
    public static final Path SKINS = DATA.resolve("skin");
    public static final Path LANGUAGES = DATA.resolve("languages");
    public static final Path ACCOUNTS = DATA.resolve("accounts");

    static
    {
        try{
            Path path = CORE.resolve("atlauncher.cfg");

            if(!Files.exists(path)){
                Files.createFile(path);
            }

            InputStream in = new FileInputStream(path.toFile());
            properties.load(in);
            in.close();
        } catch(Exception ex){
            ex.printStackTrace(System.err);
        }

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable(){
            @Override
            public void run(){
                try{
                    Path path = CORE.resolve("atlauncher.cfg");

                    OutputStream out = new FileOutputStream(path.toFile());
                    properties.store(out, "Don't Edit This File");
                    out.close();
                } catch(Exception ex){
                    ex.printStackTrace(System.err);
                }
            }
        }));
    }

    public static final Server.Servers SERVERS = ATLauncher.getInjector().getInstance(Server.Servers.class);

    private Settings(){}

    private static FileHash[] getFileHashes(){
        Path hashes = DATA.resolve("hashes.json");
        if(!Files.exists(hashes)){
            new Downloadable("newlauncher/hashes.json", Settings.DATA).run();
        }

        try{
            InputStream fis = new FileInputStream(hashes.toFile());
            FileHash[] h = GSON.fromJson(new InputStreamReader(fis), FileHash[].class);
            fis.close();
            return h;
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    public static News[] getNews(){
        Path news = JSON.resolve("news.json");
        if(!Files.exists(news)){
            updateLauncherFiles();
            return getNews();
        }

        try{
            InputStream fis = new FileInputStream(news.toFile());
            News[] n = GSON.fromJson(new InputStreamReader(fis), News[].class);
            fis.close();
            return n;
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    public static void updateLauncherFiles(){
        FileHash[] hashes = Settings.getFileHashes();
        for(FileHash hash : hashes){
            if(hash.size != 0){
                ATLauncher.TASKS.execute(hash.getDownload());
            }
        }
    }
}