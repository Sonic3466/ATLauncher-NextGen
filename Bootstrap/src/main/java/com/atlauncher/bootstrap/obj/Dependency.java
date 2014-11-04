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
package com.atlauncher.bootstrap.obj;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

public final class Dependency{
    public final String group;
    public final String artifact;
    public final String version;

    @SuppressWarnings("unused")
    private Dependency(String group, String artifact, String version){
        this.group = group;
        this.artifact = artifact;
        this.version = version;
    }

    public String url(){
        return "http://central.maven.org/maven2/" + this.group.replace(".", "/") + "/" + this.artifact + "/" + this.version + "/" + this.artifact + "-" + this.version + ".jar";
    }

    public void download(Path parent)
    throws IOException{
        try(FileChannel channel = FileChannel.open(this.resolve(parent), EnumSet.of(
                StandardOpenOption.CREATE, StandardOpenOption.WRITE));
            InputStream in = new URL(this.url()).openStream();
            ReadableByteChannel rbc = Channels.newChannel(in)){

            channel.transferFrom(rbc, 0, Long.MAX_VALUE);
        }
    }

    public Path resolve(Path parent){
        return parent.resolve(this.artifact + "-" + this.version + ".jar");
    }

    @Override
    public String toString(){
        return String.format("%s:%s:%s", this.group, this.artifact, this.version);
    }
}