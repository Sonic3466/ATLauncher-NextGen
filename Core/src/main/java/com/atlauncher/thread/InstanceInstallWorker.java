package com.atlauncher.thread;

import com.atlauncher.Settings;
import com.atlauncher.obj.Downloadable;
import com.atlauncher.obj.Pack;
import com.atlauncher.obj.Pack.Version;
import com.atlauncher.ui.diag.LoadingDialog;

import java.nio.file.Path;
import javax.swing.SwingWorker;

public final class InstanceInstallWorker
extends SwingWorker<Void, Void>{
    private final LoadingDialog diag;
    private final Pack pack;
    private final Version version;

    public InstanceInstallWorker(LoadingDialog diag, Pack pack, Version version){
        this.diag = diag;
        this.pack = pack;
        this.version = version;
    }

    @Override
    protected Void doInBackground()
    throws Exception{
        this.downloadConfigs();
        return null;
    }

    private void downloadConfigs(){
        String path = String.format("packs/%s/versions/%s/Configs.zip", this.pack.getSafeName(), this.version.version);
        Path output = Settings.TMP.resolve("Configs.zip");
        Downloadable dl = new Downloadable(path, output, null, false);
        dl.run();
        path = String.format("packs/%s/versions/%s/Configs.json", this.pack.getSafeName(), this.version.version);
        output = Settings.TMP.resolve("Configs.json");
        dl = new Downloadable(path, output, null, false);
        dl.run();
    }
}