package com.atlauncher.thread;

import com.atlauncher.Settings;
import com.atlauncher.obj.Pack;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public final class CollectSPPackRunnable
implements Runnable{
    @Override
    public void run(){
        try{
            InputStream in = new FileInputStream(Settings.JSON.resolve("packs.json").toFile());
            Pack[] packs = Settings.GSON.fromJson(new InputStreamReader(in), Pack[].class);
            in.close();
        } catch(Exception ex){
            ex.printStackTrace(System.err);
        }
    }
}