package com.atlauncher.obj;

import com.atlauncher.ATLauncher;
import com.atlauncher.Accounts;
import com.atlauncher.Settings;
import com.atlauncher.annot.Json;

import com.google.gson.annotations.SerializedName;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Set;
import java.util.TreeSet;
import javax.imageio.ImageIO;

@Json
public final class Pack{
    public final String name;
    public final String description;
    public final String code;
    public final String support_url;
    public final String website_url;
    public final boolean can_create_server;
    public final boolean is_leaderboards_enabled;
    public final boolean is_logging_enabled;
    public final int position;
    public final Type type;
    public final Version[] versions;

    private transient Set<String> allowed_players;
    private transient Set<String> testers;

    private Pack(String display_name, String description, String support_url, String website_url, boolean can_create_server, boolean is_leaderboards_enabled, Type type, Version[] versions, Version[] devVersions, String code, boolean is_logging_enabled, int position){
        this.name = display_name;
        this.description = description;
        this.support_url = support_url;
        this.website_url = website_url;
        this.can_create_server = can_create_server;
        this.is_leaderboards_enabled = is_leaderboards_enabled;
        this.type = type;
        this.versions = versions;
        this.code = code;
        this.is_logging_enabled = is_logging_enabled;
        this.position = position;
        this.allowed_players = new TreeSet<>();
        this.testers = new TreeSet<>();
    }

    public void allow(String player){
        if(this.allowed_players == null){
            this.allowed_players = new TreeSet<>();
        }

        this.allowed_players.add(player);
    }

    public void allowTester(String player){
        if(this.testers == null){
            this.testers = new TreeSet<>();
        }

        this.testers.add(player);
    }

    public String getJson(String version){
        Path json = Settings.PACK.resolve(this.getSafeName()).resolve(version).resolve("Configs.json");
        if(Files.exists(json)){
            try(ByteArrayOutputStream bos = new ByteArrayOutputStream();
                FileChannel channel = FileChannel.open(json, StandardOpenOption.READ);
                WritableByteChannel wbc = Channels.newChannel(bos)){

                channel.transferTo(0, Long.MAX_VALUE, wbc);

                return new String(bos.toByteArray());
            } catch(IOException e){
                throw new RuntimeException(e);
            }
        } else{
            Downloadable dl = new Downloadable("packs/" + this.getSafeName() + "/versions/" + version + "/Configs.json", json, null, false);
            ATLauncher.TASKS.submit(dl);
            return this.getJson(version);
        }
    }

    public BufferedImage getImage(){
        Path path = Settings.IMAGES.resolve(this.getSafeName() + ".png");
        try{
            if(!Files.exists(path)){
                return ImageIO.read(Settings.IMAGES.resolve("vanillaminecraft.png").toFile());
            } else{
                return ImageIO.read(path.toFile());
            }
        } catch(Exception ex){
            return null;
        }
    }

    public String getSafeName(){
        return this.name.toLowerCase().replaceAll("[^A-Za-z0-9]", "");
    }

    public boolean isTester(){
        Account acc = Accounts.instance.getCurrent();
        if(acc == null || acc == Account.DEFAULT){
            return false;
        }

        if(this.testers != null){
            for(String tester : this.testers){
                if(tester.equalsIgnoreCase(acc.name)){
                    return true;
                }
            }
        }

        return false;
    }

    public Version getLatest(){
        if(this.isTester()){
            return this.versions[0];
        } else{
            for(Version version : this.versions){
                if(!version.is_developer){
                    return version;
                }
            }
        }

        return null;
    }

    public boolean isAllowed(){
        if(this.type != Type.PRIVATE){
            return true;
        }

        Account acc = Accounts.instance.getCurrent();
        if(acc == null || acc == Account.DEFAULT){
            return false;
        }

        if(this.allowed_players == null){
            return false;
        }

        for(String player : this.allowed_players){
            if(player.equalsIgnoreCase(acc.name)){
                return true;
            }
        }

        return false;
    }

    public static enum Type{
        @SerializedName("public") PUBLIC,
        @SerializedName("semipublic") SEMI_PUBLIC,
        @SerializedName("private") PRIVATE
    }

    public static final class Version{
        public final String version;
        public final String minecraft_version;
        public final boolean is_developer;

        public Version(String version, String minecraft_version, boolean isDeveloper){
            this.version = version;
            this.minecraft_version = minecraft_version;
            this.is_developer = isDeveloper;
        }
    }
}