package com.atlauncher.obj;

public final class Mod{
    public final String description;
    public final String name;
    public final String download;
    public final boolean recommended;

    public Mod(String description, String name, String download, boolean recommended){
        this.description = description;
        this.name = name;
        this.download = download;
        this.recommended = recommended;
    }
}