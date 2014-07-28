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