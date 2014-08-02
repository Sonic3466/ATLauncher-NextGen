package com.atlauncher;

import com.atlauncher.obj.Pack;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public enum Packs{
    instance;

    private final Set<Pack> loaded = new HashSet<>();

    private Packs(){
        try{
            InputStream in = System.class.getResourceAsStream("/packs.json");
            Pack[] packs = Settings.GSON.fromJson(new InputStreamReader(in), Pack[].class);
            for(Pack pack : packs){
                this.loaded.add(pack);
            }
        } catch(Exception ex){
            ex.printStackTrace(System.err);
        }
    }

    public Set<Pack> all(){
        return this.loaded;
    }
}