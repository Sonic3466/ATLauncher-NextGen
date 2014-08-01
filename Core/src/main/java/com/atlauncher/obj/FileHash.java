package com.atlauncher.obj;

import com.atlauncher.Settings;

public final class FileHash{
    public final String name;
    public final String folder;
    public final String md5;
    public final String sha1;
    public final int size;

    public FileHash(String name, String folder, String md5, String sha1, int size){
        this.name = name;
        this.folder = folder;
        this.md5 = md5;
        this.sha1 = sha1;
        this.size = size;
    }

    public Downloadable getDownload(){
        return new Downloadable("/newlauncher/" + this.folder.toLowerCase() + "/" + this.name, Settings.DATA.resolve(this.folder.toLowerCase()));
    }
}