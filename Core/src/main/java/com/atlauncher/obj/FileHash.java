package com.atlauncher.obj;

import com.atlauncher.Settings;

public final class FileHash{
    public final String name;
    public final String type;
    public final String md5;
    public final String sha1;
    public final int size;

    public FileHash(String name, String type, String md5, String sha1, int size){
        this.name = name;
        this.type = type;
        this.md5 = md5;
        this.sha1 = sha1;
        this.size = size;
    }

    public Downloadable getDownload(){
        switch(type)
        {
            case "library":
            case "json":{
                return new Downloadable("newlauncher/" + this.name, Settings.DATA.resolve(this.type.toLowerCase()),
                        md5, true);
            }
            default:{
                return new Downloadable("launcher/" + this.type.toLowerCase() + "s" + "/" + this.name, Settings.DATA.resolve(this.type.toLowerCase()),
                        md5, true);
            }
        }
    }
}