package com.atlauncher.thread;

import com.atlauncher.ATLauncher;
import com.atlauncher.Accounts;
import com.atlauncher.Settings;
import com.atlauncher.event.PackLoadedEvent;
import com.atlauncher.obj.Account;
import com.atlauncher.obj.Pack;
import com.atlauncher.obj.UserMeta;
import com.atlauncher.utils.PacksComparator;

import com.google.gson.reflect.TypeToken;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import javax.swing.SwingWorker;

public final class CollectPacksWorker
extends SwingWorker<Void, Void>{
    @Override
    public Void doInBackground()
    throws Exception{
        try{
            if(Accounts.instance.getCurrent() == null || Accounts.instance.getCurrent() == Account.DEFAULT){
                return null;
            }

            InputStream in = new FileInputStream(Settings.JSON.resolve("packs.json").toFile());
            List<Pack> packs = Settings.GSON.fromJson(new InputStreamReader(in), new TypeToken<List<Pack>> (){}.getType());
            in.close();
            URL url = new URL(Accounts.instance.getCurrent().getUserMetaURL());
            UserMeta meta = null;
            try{
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                if(conn.getResponseCode() != 404){
                    try(InputStream stream = conn.getInputStream()){
                        meta = Settings.GSON.fromJson(new InputStreamReader(stream), UserMeta.class);
                    }
                }
            } catch(Exception ex){
                ex.printStackTrace(System.err);
            }

            ATLauncher.LOGGER.debug("Sorting Packs");
            Collections.sort(packs, new PacksComparator());

            for(Pack pack : packs){
                if(meta != null){
                    for(String str : meta.allowed_player){
                        if(str.equalsIgnoreCase(pack.name)){
                            pack.allow(Accounts.instance.getCurrent().name);
                            break;
                        }
                    }

                    for(String str : meta.tester){
                        if(str.equalsIgnoreCase(pack.name)){
                            pack.allowTester(Accounts.instance.getCurrent().name);
                            break;
                        }
                    }
                }

                switch(pack.type){
                    case PUBLIC:{
                        ATLauncher.EVENT_BUS.post(new PackLoadedEvent(pack));
                        break;
                    }
                    case PRIVATE:{
                        if(pack.isAllowed()){
                            ATLauncher.EVENT_BUS.post(new PackLoadedEvent(pack));
                        }
                        break;
                    }
                    default:{
                        break;
                    }
                }
            }

            return null;
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
}