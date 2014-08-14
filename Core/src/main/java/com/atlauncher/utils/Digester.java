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