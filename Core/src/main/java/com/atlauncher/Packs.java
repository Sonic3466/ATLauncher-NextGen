package com.atlauncher;

import com.atlauncher.thread.CollectPacksRunnable;
import com.atlauncher.ui.diag.LoadingDialog;

public final class Packs{
    private Packs(){}

    public static void load(){
        try{
            LoadingDialog diag = new LoadingDialog("Loading Packs");
            new CollectPacksRunnable(diag).execute();
            diag.setVisible(true);
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
}