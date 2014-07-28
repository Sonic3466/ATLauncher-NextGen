package com.atlauncher;

import com.atlauncher.obj.Downloadable;
import com.atlauncher.obj.FileHash;
import com.atlauncher.obj.Server;

import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class Settings{
    public static final Gson GSON = new Gson();

    public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.72 Safari/537.36";

    public static final Path CORE = Paths.get(System.getProperty("user.home"), ".atlauncher");
    public static final Path DATA = CORE.resolve("data");
    public static final Path IMAGES = DATA.resolve("images");

    public static final Server.Servers SERVERS = ATLauncher.getInjector().getInstance(Server.Servers.class);

    private Settings(){}

    private static FileHash[] getFileHashes(){
        Path hashes = DATA.resolve("hashes.json");
        if(!Files.exists(hashes)){
            new Downloadable("launcher/json/hashes.json", Settings.DATA).run();
        }

        try{
            InputStream fis = new FileInputStream(hashes.toFile());
            FileHash[] h = GSON.fromJson(new InputStreamReader(fis), FileHash[].class);
            fis.close();
            return h;
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    public static void updateLauncherFiles(){
        FileHash[] hashes = Settings.getFileHashes();
        for(FileHash hash : hashes){
            if(hash.size != 0){
                hash.getDownload().run();
            }
        }
    }
}