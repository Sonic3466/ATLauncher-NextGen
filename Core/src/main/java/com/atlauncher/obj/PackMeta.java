package com.atlauncher.obj;

import java.util.Map;

public final class PackMeta{
    public final String version;
    public final String minecraft;
    public final Map<String, String> mainClass;
    public final Map<String, String> extraArguments;
    public final Library[] libraries;
    public final Mod[] mods;

    public PackMeta(String version, String minecraft, Map<String, String> mainClass, Map<String, String> extraArguments, Library[] libraries, Mod[] mods){
        this.version = version;
        this.minecraft = minecraft;
        this.mainClass = mainClass;
        this.extraArguments = extraArguments;
        this.libraries = libraries;
        this.mods = mods;
    }
}