package com.atlauncher.obj;

import com.atlauncher.Resources;
import com.atlauncher.Settings;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.imageio.ImageIO;

public final class Account{
    public final String name;

    public Account(String name){
        this.name = name;
    }

    public BufferedImage getSkin(){
        Path path = Settings.SKINS.resolve(this.name.toLowerCase() + ".png");

        if(!Files.exists(path)){
            this.updateSkin();
        }

        try{
            return ImageIO.read(path.toFile());
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    public void updateSkin(){
        try{
            Path path = Settings.SKINS.resolve(this.name.toLowerCase() + ".png");

            if(Files.exists(path)){
                Files.delete(path);
            }

            HttpURLConnection conn = (HttpURLConnection) new URL("http://s3.amazonaws.com/MinecraftSkins/" + this.name + ".png").openConnection();
            try(InputStream in = conn.getInputStream();
                ReadableByteChannel rbc = Channels.newChannel(in);
                FileChannel channel = FileChannel.open(path, Resources.WRITE)){

                channel.transferFrom(rbc, 0, Long.MAX_VALUE);
            }
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    public Image getHead(){
        try{
            BufferedImage skin = this.getSkin();
            BufferedImage main = skin.getSubimage(8, 8, 8, 8);
            BufferedImage head = new BufferedImage(8, 8, BufferedImage.TYPE_INT_ARGB);

            Graphics g = head.getGraphics();
            g.drawImage(main, 0, 0, null);
            return head.getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
}