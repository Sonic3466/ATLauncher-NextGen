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
package com.atlauncher.obj;

import com.atlauncher.Accounts;
import com.atlauncher.Settings;
import com.atlauncher.annot.Json;

import com.google.gson.annotations.SerializedName;

import java.awt.image.BufferedImage;
import java.nio.file.Files;
import java.nio.file.Path;
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

    public BufferedImage getImage(){
        Path path = Settings.IMAGES.resolve(this.getSafeName().toLowerCase() + ".png");
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
        return this.name.replaceAll("[^A-Za-z0-9]", "");
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

        @Override
        public String toString(){
            return this.version + " (" + this.minecraft_version + ")" + (this.is_developer ? " - Developer" : "");
        }
    }
}