package com.atlauncher.obj;

import com.atlauncher.Settings;

import com.google.gson.annotations.SerializedName;

import java.awt.image.BufferedImage;
import java.nio.file.Path;
import javax.imageio.ImageIO;

public final class Pack{
    public final String display_name;
    public final String description;
    public final String code;
    public final Type type;
    public final Version[] versions;

    public Pack(String display_name, String description, Type type, Version[] versions, Version[] devVersions, String code){
        this.display_name = display_name;
        this.description = description;
        this.type = type;
        this.versions = versions;
        this.code = code;
    }

    public BufferedImage getImage(){
        Path path = Settings.IMAGES.resolve(this.display_name.toLowerCase().replace(" ", "") + ".png");
        try{
            return ImageIO.read(path.toFile());
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    public String getSafeName(){
        return this.display_name.toLowerCase().replace(" ", "");
    }

    public static enum Type{
        @SerializedName("public") PUBLIC,
        @SerializedName("semipublic") SEMI_PUBLIC,
        @SerializedName("private") PRIVATE
    }

    public static final class Version{
        public final String version;
        public final String minecraft_version;
        public final Mod[] mods;
        public final boolean isDeveloper;

        public Version(String version, String minecraft_version, Mod[] mods, boolean isDeveloper){
            this.version = version;
            this.minecraft_version = minecraft_version;
            this.mods = mods;
            this.isDeveloper = isDeveloper;
        }
    }
}