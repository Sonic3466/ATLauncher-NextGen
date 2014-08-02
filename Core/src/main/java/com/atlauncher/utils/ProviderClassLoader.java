package com.atlauncher.utils;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;

public final class ProviderClassLoader
extends URLClassLoader{
    private static final Path libs = OS.getStorageLocation().resolve("libs");
    private static final URL[] jars;

    static
    {
        try{
            File[] files = libs.toFile().listFiles(new FilenameFilter(){
                @Override
                public boolean accept(File dir, String name){
                    return name.endsWith(".jar");
                }
            });
            jars = new URL[files.length];
            for(int i = 0; i < files.length; i++){
                jars[i] = files[i].toURI().toURL();
                System.out.println(files[i]);
            }
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    public ProviderClassLoader(){
        super(ProviderClassLoader.jars, URLClassLoader.getSystemClassLoader());
    }
}