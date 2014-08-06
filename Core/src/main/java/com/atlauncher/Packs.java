package com.atlauncher;

import com.atlauncher.obj.Pack;
import com.atlauncher.thread.LoadPacksThread;

import java.util.Set;
import java.util.TreeSet;

public enum Packs{
    instance;

    private final Set<Pack> loaded = new TreeSet<>();

    private Packs(){
        try{
            loaded.addAll(ATLauncher.TASKS.submit(new LoadPacksThread()).get());
        } catch(Exception ex){
            ex.printStackTrace(System.err);
        }
    }

    public Set<Pack> all(){
        try{
            this.loaded.clear();
            this.loaded.addAll(ATLauncher.TASKS.submit(new LoadPacksThread()).get());
            return this.loaded;
        } catch(Exception ex){
            ex.printStackTrace(System.err);
            return new TreeSet<>();
        }
    }
}