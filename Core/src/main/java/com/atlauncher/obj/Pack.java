package com.atlauncher.obj;

import com.atlauncher.Settings;

import java.awt.image.BufferedImage;
import java.nio.file.Path;
import javax.imageio.ImageIO;

public final class Pack{
    public final String display_name;
    public final String description;

    public Pack(String display_name, String description){
        this.display_name = display_name;
        this.description = description;
    }

    public BufferedImage getImage(){
        Path path = Settings.IMAGES.resolve(this.display_name.toLowerCase().replace(" ", "") + ".png");
        try{
            return ImageIO.read(path.toFile());
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
}