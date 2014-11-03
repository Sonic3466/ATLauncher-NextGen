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
package com.atlauncher.utils;

import java.io.ByteArrayOutputStream;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class Digester{
    private Digester(){}

    private static final MessageDigest MD5;
    private static final MessageDigest SHA1;

    static{
        try{
            MD5 = MessageDigest.getInstance("MD5");
            SHA1 = MessageDigest.getInstance("SHA-1");
        } catch(NoSuchAlgorithmException e){
            throw new RuntimeException(e);
        }
    }

    public static String getMD5(Path path){
        try{
            if(!Files.exists(path)){
                return "0";
            }

            try(ByteArrayOutputStream bos = new ByteArrayOutputStream();
                FileChannel channel = FileChannel.open(path);
                WritableByteChannel wbc = Channels.newChannel(bos)){

                channel.transferTo(0, Long.MAX_VALUE, wbc);

                byte[] bits = MD5.digest(bos.toByteArray());
                MD5.reset();
                return hexify(bits);
            }
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    public static String getSHA1(Path path){
        try{
            if(!Files.exists(path)){
                return "0";
            }

            try(ByteArrayOutputStream bos = new ByteArrayOutputStream();
                FileChannel channel = FileChannel.open(path);
                WritableByteChannel wbc = Channels.newChannel(bos)){

                channel.transferTo(0, Long.MAX_VALUE, wbc);

                byte[] bits = SHA1.digest(bos.toByteArray());
                SHA1.reset();
                return hexify(bits);
            }
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    private static String hexify(byte[] bits){
        StringBuilder builder = new StringBuilder();

        for(byte b : bits){
            builder.append(Integer.toString((b & 0xFF) + 0x100, 16).substring(1));
        }

        return builder.toString();
    }
}