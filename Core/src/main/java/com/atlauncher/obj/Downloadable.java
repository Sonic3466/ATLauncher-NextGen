package com.atlauncher.obj;

import com.atlauncher.Settings;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;
import java.util.zip.GZIPInputStream;

public final class Downloadable
implements Runnable{
    public final String endpoint;
    public final Path output;

    private HttpURLConnection conn;

    public Downloadable(String endpoint, Path output){
        this.endpoint = endpoint;
        this.output = output.resolve(endpoint.substring(endpoint.lastIndexOf('/') + 1));

        try{
            Files.createDirectories(output);
        } catch(IOException e){
            e.printStackTrace(System.out);
        }
    }

    private boolean isGzip(){
        return (this.getConnection().getContentEncoding() != null && this.getConnection().getContentEncoding().equalsIgnoreCase("gzip"));
    }

    private HttpURLConnection getConnection(){
        if(this.conn == null){
            try{
                this.conn = (HttpURLConnection) new URL(Settings.SERVERS.get(0).getFileURL(this.endpoint)).openConnection();

                this.conn.setUseCaches(false);
                this.conn.setDefaultUseCaches(false);
                this.conn.setRequestProperty("Accept-Encoding", "gzip");
                this.conn.setRequestProperty("User-Agent", Settings.USER_AGENT);
                this.conn.setRequestProperty("Cache-Control", "no-store,max-age=0,no-cache");
                this.conn.setRequestProperty("Expires", "0");
                this.conn.setRequestProperty("Pragma", "no-cache");
                this.conn.connect();
            } catch(Exception ex){
                ex.printStackTrace(System.err);
            }
        }

        return this.conn;
    }

    @Override
    public void run(){
        if(Files.exists(this.output) && !this.endpoint.contains("json")){
            return;
        }

        try(InputStream stream = this.getStream();
            FileChannel channel = this.getChannel();
            ReadableByteChannel rbc = Channels.newChannel(stream)){

            channel.transferFrom(rbc, 0, Long.MAX_VALUE);
        } catch(Exception ex){
            ex.printStackTrace(System.out);
        }
    }

    private FileChannel getChannel()
    throws IOException{
        return FileChannel.open(this.output, EnumSet.of(StandardOpenOption.CREATE, StandardOpenOption.WRITE));
    }

    private InputStream getStream(){
        try{
            return (this.isGzip() ? new GZIPInputStream(this.getConnection().getInputStream()) : this.getConnection().getInputStream());
        } catch(Exception ex){
            ex.printStackTrace(System.err);
            return this.getConnection().getErrorStream();
        }
    }
}