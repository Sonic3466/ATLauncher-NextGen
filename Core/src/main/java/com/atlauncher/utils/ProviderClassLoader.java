/*
 * ATLauncher-NextGen - https://github.com/ATLauncher/ATLauncher-NextGen
 * Copyright (C) 2013 ATLauncher
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.atlauncher.utils;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;

//TODO: Optimize
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
            }
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    public ProviderClassLoader(){
        super(ProviderClassLoader.jars, URLClassLoader.getSystemClassLoader());
    }
}