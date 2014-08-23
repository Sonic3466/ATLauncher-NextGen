package com.atlauncher.obj;

import com.atlauncher.annot.Json;

@Json
public final class Library{
    public final String url;
    public final String file;
    public final String server;
    public final String md5;
    public final String download;

    public Library(String url, String file, String server, String md5, String download){
        this.url = url;
        this.file = file;
        this.server = server;
        this.md5 = md5;
        this.download = download;
    }
}