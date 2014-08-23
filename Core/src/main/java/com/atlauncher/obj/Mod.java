package com.atlauncher.obj;

import com.atlauncher.annot.Json;

@Json
public final class Mod{
    public final String name;
    public final String version;
    public final String url;
    public final String file;
    public final String md5;
    public final String download;
    public final String website;
    public final String authors;
    public final String type;
    public final String description;
    public final String[] depends;
    public final boolean client;
    public final boolean optional;
    public final boolean recommended;

    public Mod(String name, String version, String url, String file, String md5, String download, String website, String authors, String type, String description, String[] depends, boolean client, boolean optional, boolean recommended){
        this.name = name;
        this.version = version;
        this.url = url;
        this.file = file;
        this.md5 = md5;
        this.download = download;
        this.website = website;
        this.authors = authors;
        this.type = type;
        this.description = description;
        this.depends = depends;
        this.client = client;
        this.optional = optional;
        this.recommended = recommended;
    }
}