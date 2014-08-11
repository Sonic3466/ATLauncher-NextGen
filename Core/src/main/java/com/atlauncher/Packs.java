package com.atlauncher;

import com.atlauncher.obj.Pack;
import com.atlauncher.thread.CollectPacksWorker;

import java.util.Set;
import java.util.TreeSet;

public enum Packs{
    instance;

    private final Set<Pack> loaded = new TreeSet<>();

    private Packs(){

    }

    public Set<Pack> all(){
        return this.loaded;
    }

    public void load(){
        this.loaded.clear();
        try{
            this.loaded.addAll(ATLauncher.TASKS.submit(new CollectPacksWorker()).get());
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
}