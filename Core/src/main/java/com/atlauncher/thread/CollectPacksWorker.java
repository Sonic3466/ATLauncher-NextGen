package com.atlauncher.thread;

import com.atlauncher.Settings;
import com.atlauncher.obj.Pack;
import com.atlauncher.obj.PackUser;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.Callable;

public final class CollectPacksWorker
implements Callable<Set<Pack>>{
    @Override
    public Set<Pack> call()
    throws Exception{
        Set<Pack> loaded = new TreeSet<>();

        InputStream in = new FileInputStream(Settings.JSON.resolve("packs.json").toFile());
        Pack[] packs = Settings.GSON.fromJson(new InputStreamReader(in), Pack[].class);
        in.close();
        in = new FileInputStream(Settings.JSON.resolve("users.json").toFile());
        PackUser[] users = Settings.GSON.fromJson(new InputStreamReader(in), PackUser[].class);
        in.close();

        for(PackUser user : users){
            for(Pack pack : packs){
                if(pack.type == Pack.Type.PRIVATE){
                    if(user.pack_name.equalsIgnoreCase(pack.name)){
                        if(pack.allowed_players != null && user.allowed_players != null && user.allowed_players.length > 0){
                            pack.allowed_players.addAll(Arrays.asList(user.allowed_players));
                        }

                        if(pack.testers != null && user.testers != null && user.testers.length > 0){
                            pack.testers.addAll(Arrays.asList(user.testers));
                        }
                    }
                }
            }
        }

        for(Pack pack : packs){
            switch(pack.type)
            {
                case PUBLIC:{
                    System.out.println("Adding Public Pack: " + pack.name);
                    loaded.add(pack);
                    break;
                }
                case PRIVATE:{
                    System.out.println("Adding Private Pack: " + pack.name);
                    if(pack.isAllowed()){
                        loaded.add(pack);
                    }
                    break;
                }
                default:{
                    System.out.println("Invalid Pack: " + pack.name);
                    break;
                }
            }
        }

        return loaded;
    }
}