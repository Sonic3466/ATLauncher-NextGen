package com.atlauncher.obj;

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
}