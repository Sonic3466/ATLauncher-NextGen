package com.atlauncher.thread;

import com.atlauncher.Settings;
import com.atlauncher.obj.Pack;
import com.atlauncher.obj.PackUser;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.Callable;

public final class LoadPacksThread
implements Callable<Set<Pack>>{
    @Override
    public Set<Pack> call()
    throws Exception{
        Set<Pack> loaded = new TreeSet<>();
        try{
            InputStream in = new FileInputStream(Settings.JSON.resolve("packs.json").toFile());
            Pack[] packs = Settings.GSON.fromJson(new InputStreamReader(in), Pack[].class);
            in.close();
            Path users = Settings.JSON.resolve("users.json");
            in = new FileInputStream(users.toFile());
            PackUser[] packUsers = Settings.GSON.fromJson(new InputStreamReader(in), PackUser[].class);

            for(Pack pack : packs){
                for(PackUser u : packUsers){
                    if(u.pack_name.equalsIgnoreCase(pack.name)){
                        System.out.println(u.pack_name);

                        if(u.allowed_players != null){
                            pack.allowed_players.addAll(Arrays.asList(u.allowed_players));
                        }

                        if(u.testers != null && u.testers.length != 0){
                            pack.testers.addAll(Arrays.asList(u.testers));
                        }
                    }
                }
            }

            in.close();
            Files.delete(users);

            for(Pack pack : packs){
                switch(pack.type)
                {
                    case PUBLIC:{
                        System.out.println("Adding Public Pack: " + pack.name);
                        loaded.add(pack);
                        break;
                    }
                    case PRIVATE:{
                        if(pack.isAllowed()){
                            loaded.add(pack);
                        }
                        break;
                    }
                }
            }

            return loaded;
        } catch(Exception ex){
            ex.printStackTrace(System.err);
            return loaded;
        }
    }
}