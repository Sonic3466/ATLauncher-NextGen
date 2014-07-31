package com.atlauncher.utils;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public final class ProviderClassLoader
extends URLClassLoader{
    private static final Path libs = OS.getStorageLocation().resolve("libs");
    private static final URL[] jars;

    static
    {
        try{
            jars = new URL[]{
                    libs.resolve("ATLauncher-DataProvider.jar").toUri().toURL()
            };
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    public ProviderClassLoader(){
        super(ProviderClassLoader.jars, URLClassLoader.getSystemClassLoader());
    }

    public void emptyProviderJar()
    throws IOException{
        JarFile jarFile = new JarFile(libs.resolve("ATLauncher-DataProvider.jar").toFile());
        Enumeration<JarEntry> entries = jarFile.entries();
        while(entries.hasMoreElements()){
            JarEntry entry = entries.nextElement();
            System.out.println(entry.getName());
        }
    }
}