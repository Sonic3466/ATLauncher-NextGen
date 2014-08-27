package com.atlauncher;

import com.atlauncher.obj.Account;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Set;

public enum Accounts{
    instance;

    private final Set<Account> loaded = new HashSet<>();
    private volatile Account current = Account.DEFAULT;

    private Accounts(){
        this.loaded.add(Account.DEFAULT);
        ATLauncher.TASKS.execute(new Runnable(){
            @Override
            public void run(){
                try{
                    if(!Files.exists(Settings.ACCOUNTS)){
                        Files.createDirectories(Settings.ACCOUNTS);
                    }

                    for(File file : Settings.ACCOUNTS.toFile().listFiles(filter())){
                        try(InputStream in = new FileInputStream(file)){
                            Account acc = Settings.GSON.fromJson(new InputStreamReader(in), Account.class);

                            if(acc == null){
                                System.out.println("null account");
                            } else{
                                loaded.add(acc);
                            }
                        }
                    }
                } catch(Exception ex){
                    ex.printStackTrace(System.err);
                }
            }
        });

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable(){
            @Override
            public void run(){
                try{
                    for(Account acc : loaded){
                        if(acc != null && acc != Account.DEFAULT){
                            try(OutputStream out = new FileOutputStream(Settings.ACCOUNTS.resolve(acc.name + ".json").toFile())){
                                out.write(Settings.GSON.toJson(acc).getBytes(StandardCharsets.UTF_8));
                                out.flush();
                            } catch(Exception ex){
                                ex.printStackTrace(System.err);
                            }
                        }
                    }
                } catch(Exception ex){
                    ex.printStackTrace(System.err);
                }
            }
        }));
    }

    private FilenameFilter filter(){
        return new FilenameFilter(){
            @Override
            public boolean accept(File dir, String name){
                return name.endsWith(".json");
            }
        };
    }

    public synchronized Account getCurrent(){
        return this.current;
    }

    public synchronized void setCurrent(Account acc){
        if(!this.loaded.contains(acc)){
            this.loaded.add(acc);
        }

        this.current = acc;
    }

    public Set<Account> all(){
        return this.loaded;
    }
}