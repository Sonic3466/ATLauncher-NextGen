package com.atlauncher;

import com.atlauncher.event.PacksFlushEvent;
import com.atlauncher.thread.CollectPacksRunnable;
import com.atlauncher.ui.diag.LoadingDialog;

//TODO: Really need this?
public enum Packs{
    instance;

    private Packs(){}

    public void load(){
        try{
            ATLauncher.EVENT_BUS.post(new PacksFlushEvent());
            LoadingDialog diag = new LoadingDialog("Loading Packs");
            CollectPacksRunnable worker = new CollectPacksRunnable(diag);
            ATLauncher.TASKS.execute(worker);
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
}