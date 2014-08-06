package com.atlauncher.bootstrap;

import com.atlauncher.bootstrap.obj.Dependency;
import com.atlauncher.bootstrap.utils.OS;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;

public final class ATLBoot{
    public static final Gson GSON = new Gson();

    public static void main(String... args){
        try{
            Path core = OS.getStorageLocation();

            if(!Files.exists(core)){
                Files.createDirectories(core);
            }

            Path c = core.resolve("ATLauncher-Core.jar");

            if(!Files.exists(c)){
                try(InputStream in = new URL("http://www.creeperrepo.net/ATL/newlauncher/modules/ATLauncher-Core.jar").openStream();
                    FileChannel channel = FileChannel.open(c, EnumSet.of(
                            StandardOpenOption.CREATE, StandardOpenOption.WRITE));
                    ReadableByteChannel rbc = Channels.newChannel(in)){

                    channel.transferFrom(rbc, 0, Long.MAX_VALUE);
                }
            }

            checkDependencies(core.resolve("libs"));
            launch(core);
        } catch(Exception ex){
            ex.printStackTrace(System.out);
        }
    }

    private static void checkDependencies(Path parent)
    throws IOException{
        if(!Files.exists(parent)){
            Files.createDirectories(parent);
        }

        Path provider = parent.resolve("ATLauncher-DataProvider.jar");
        if(!Files.exists(provider)){
            try(InputStream in = new URL("http://www.creeperrepo.net/ATL/newlauncher/modules/ATLauncher-DataProvider.jar").openStream();
                FileChannel channel = FileChannel.open(provider, EnumSet.of(
                        StandardOpenOption.CREATE, StandardOpenOption.WRITE));
                ReadableByteChannel rbc = Channels.newChannel(in)){

                channel.transferFrom(rbc, 0, Long.MAX_VALUE);
            }
        }

        Path authLib = parent.resolve("authlib-1.5.16.jar");
        if(!Files.exists(authLib)){
            try(InputStream in = new URL("https://libraries.minecraft.net/com/mojang/authlib/1.5.16/authlib-1.5.16.jar").openStream();
                FileChannel channel = FileChannel.open(authLib, EnumSet.of(
                        StandardOpenOption.CREATE, StandardOpenOption.WRITE));
                ReadableByteChannel rbc = Channels.newChannel(in)){

                channel.transferFrom(rbc, 0, Long.MAX_VALUE);
            }
        }

        InputStream in = System.class.getResourceAsStream("/package.json");
        Dependency[] dependencies = ATLBoot.GSON.fromJson(new InputStreamReader(in), Dependency[].class);
        in.close();

        for(Dependency dep : dependencies){
            System.out.println("Checking Dependency -> " + dep);

            if(!Files.exists(dep.resolve(parent))){
                System.out.println("Resolving Dependency -> " + dep);

                dep.download(parent);
            }
        }
    }

    private static void launch(Path parent)
    throws IOException{
        try{
            List<String> a = new LinkedList<>();

            if(OS.getCurrent() == OS.MAC && Files.exists(Paths.get(System.getProperty("user.home")).getParent())){
                a.add("open");
                a.add(Paths.get(System.getProperty("user.home")).getParent().getParent().getParent().toAbsolutePath().toString());
            } else{
                String p = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
                if(OS.getCurrent() == OS.WINDOWS){
                    p += "w";
                }
                a.add(p);
                a.add("-jar");
                a.add(parent.resolve("ATLauncher-Core.jar").toString());
                a.add("--provider");
                a.add("com.atlauncher.ATLModule");
            }

            Process process = new ProcessBuilder().command(a).start();
            try(BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))){
                String line;
                while((line = reader.readLine()) != null){
                    System.out.println(line);
                }
            } catch(Exception ex){
                ex.printStackTrace(System.out);
                try(BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()))){
                    String line;
                    while((line = reader.readLine()) != null){
                        System.out.println(line);
                    }
                } catch(Exception e){
                    e.printStackTrace(System.out);
                }
            }

            try{
                int exit = process.waitFor();
                System.out.println("Exit Value: " + exit);
            } catch(Exception ex){
                ex.printStackTrace(System.out);
            }
        } catch(Exception ex){
            ex.printStackTrace(System.out);
        }
    }
}