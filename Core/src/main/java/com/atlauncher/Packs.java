package com.atlauncher;

import com.atlauncher.event.PacksFlushEvent;
import com.atlauncher.thread.CollectPacksWorker;
import com.atlauncher.ui.diag.LoadingDialog;

public enum Packs{
    instance;

    private Packs(){}

    public void load(){
        try{
            ATLauncher.EVENT_BUS.post(new PacksFlushEvent());
            LoadingDialog diag = new LoadingDialog("Loading Packs");
            CollectPacksWorker worker = new CollectPacksWorker(diag);
            ATLauncher.TASKS.execute(worker);
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
}