package com.atlauncher.bootstrap;

import com.atlauncher.bootstrap.obj.Dependency;
import com.atlauncher.bootstrap.utils.OS;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public final class ATLBoot{
    public static final Gson GSON = new Gson();

    public static void main(String... args)
    throws IOException{
        try{
            Path core = Paths.get(System.getProperty("user.home"), ".atlauncher");
            checkDependencies(core.resolve("libs"));
            System.out.println(System.getProperty("java.version"));
            launch(core);
        } catch(Exception ex){
            ex.printStackTrace(System.err);
        }
    }

    private static void checkDependencies(Path parent)
    throws IOException{
        if(!Files.exists(parent)){
            Files.createDirectories(parent);
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
                a.add(parent.resolve("atlauncher.jar").toString());
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