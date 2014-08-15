package com.atlauncher.obj;

import com.atlauncher.Accounts;
import com.atlauncher.Settings;

import com.google.gson.annotations.SerializedName;

import java.awt.image.BufferedImage;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.TreeSet;
import javax.imageio.ImageIO;

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

    public final transient Set<String> allowed_players;
    public final transient Set<String> testers;

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