package com.atlauncher.obj;

import java.util.LinkedList;
import java.util.List;

public final class Version{
    public final String version;
    public final String minecraft;
    public final int memory;
    public final int permGen;
    public final List<Library> libraries;
    public final List<Mod> mods;

    public Version(String version, String minecraft, int memory, int permGen, List<Library> libraries, List<Mod> mods){
        this.version = version;
        this.minecraft = minecraft;
        this.memory = memory;
        this.permGen = permGen;
        this.libraries = libraries;
        this.mods = mods;
    }

    public List<Mod> getClientMods(){
        List<Mod> mods = new LinkedList<>();
        for(Mod mod : this.mods){
            if(mod.client){
                mods.add(mod);
            }
        }
        return mods;
    }
}