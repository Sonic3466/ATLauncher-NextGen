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

import com.atlauncher.ATLauncher;
import com.atlauncher.Resources;
import com.atlauncher.Settings;
import com.atlauncher.plaf.UIUtils;

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
    public final String username;

    public static final Account DEFAULT = new Account("Default", "");

    public Account(String name, String username){
        this.name = name;
        this.username = username;
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

    public Image getBody(){
        BufferedImage skin = this.getSkin();

        BufferedImage head = skin.getSubimage(8, 8, 8, 8);
        BufferedImage arm = skin.getSubimage(44, 20, 4, 12);
        BufferedImage body = skin.getSubimage(20, 20, 8, 12);
        BufferedImage leg = skin.getSubimage(4, 20, 4, 12);
        BufferedImage s = new BufferedImage(16, 32, BufferedImage.TYPE_INT_ARGB);
        Graphics g = s.getGraphics();
        g.drawImage(head, 4, 0, null);
        g.drawImage(arm, 0, 8, null);
        g.drawImage(UIUtils.flip(arm), 12, 8, null);
        g.drawImage(body, 4, 8, null);
        g.drawImage(leg, 4, 20, null);
        g.drawImage(UIUtils.flip(leg), 8, 20, null);

        return s.getScaledInstance(128, 256, Image.SCALE_SMOOTH);
    }

    public String getUserMetaURL(){
        return Settings.server.getFileURL("newlauncher/users/" + this.name.toLowerCase() + ".json");
    }

    public Account updateSkin(){
        try{
            if(this.name.toLowerCase().equalsIgnoreCase("default")){
                return this;
            }

            Path path = Settings.SKINS.resolve(this.name.toLowerCase() + ".png");

            if(Files.exists(path)){
                return this;
            }

            HttpURLConnection conn = (HttpURLConnection) new URL("http://s3.amazonaws.com/MinecraftSkins/" + this.name + ".png").openConnection();
            try(InputStream in = conn.getInputStream();
                ReadableByteChannel rbc = Channels.newChannel(in);
                FileChannel channel = FileChannel.open(path, Resources.WRITE)){

                ATLauncher.LOGGER.info("Updating Skin For: " + this.name);
                channel.transferFrom(rbc, 0, Long.MAX_VALUE);
            }
        } catch(Exception ex){
            throw new RuntimeException("Error updating skin for " + this.name, ex);
        }
        return this;
    }

    public Account forceUpdate(){
        try{
            if(this.name.toLowerCase().equalsIgnoreCase("default")){
                return this;
            }

            Path path = Settings.SKINS.resolve(this.name.toLowerCase() + ".png");

            if(Files.exists(path)){
                Files.delete(path);
            }

            HttpURLConnection conn = (HttpURLConnection) new URL("http://s3.amazonaws.com/MinecraftSkins/" + this.name + ".png").openConnection();
            try(InputStream in = conn.getInputStream();
                ReadableByteChannel rbc = Channels.newChannel(in);
                FileChannel channel = FileChannel.open(path, Resources.WRITE)){

                ATLauncher.LOGGER.info("Updating Skin For: " + this.name);
                channel.transferFrom(rbc, 0, Long.MAX_VALUE);
            }
        } catch(Exception ex){
            throw new RuntimeException("[Forced] Error updating skin for " + this.name, ex);
        }
        return this;
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
            throw new RuntimeException("Error getting head for " + this.name, ex);
        }
    }

    @Override
    public boolean equals(Object obj){
        return obj instanceof Account && ((Account) obj).username.equalsIgnoreCase(this.username);
    }
}