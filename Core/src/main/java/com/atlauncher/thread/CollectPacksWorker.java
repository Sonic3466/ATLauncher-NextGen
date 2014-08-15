package com.atlauncher.thread;

import com.atlauncher.ATLauncher;
import com.atlauncher.Settings;
import com.atlauncher.event.PackLoadedEvent;
import com.atlauncher.obj.Pack;
import com.atlauncher.obj.PackUser;
import com.atlauncher.ui.diag.LoadingDialog;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

public final class CollectPacksWorker
extends SwingWorker<Void, Void>{
    private final LoadingDialog diag;

    public CollectPacksWorker(LoadingDialog diag){
        this.diag = diag;
    }

    @Override
    public void done(){
        super.done();
        this.diag.dispose();
    }

    @Override
    public Void doInBackground()
    throws Exception{
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                diag.setVisible(true);
            }
        });

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

        for(int i = 0; i < packs.length; i++){
            Pack pack = packs[i];
            switch(pack.type)
            {
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
            this.diag.bar.setValue((i * 100) / packs.length);
        }

        return null;
    }
}