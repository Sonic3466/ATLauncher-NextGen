package com.atlauncher.obj;

import java.util.List;

public final class Server{
    public final String name;
    public final String url;
    public final boolean selectable;
    public final boolean master;

    public Server(String name, String url, boolean selectable, boolean master){
        this.name = name;
        this.url = url;
        this.selectable = selectable;
        this.master = master;
    }

    public String getFileURL(String endpoint){
        return "http://" + this.url + "/" + endpoint;
    }

    @Override
    public String toString(){
        return this.name;
    }

    public static interface Servers
    extends List<Server>{}
}