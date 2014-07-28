package com.atlauncher.utils;

import java.util.HashMap;
import java.util.Map;

public final class CLIParser{
    public final Map<String, String> args = new HashMap<>();

    public CLIParser(String... args){
        if((args.length % 2) != 0){
            throw new IllegalArgumentException("Args need an even number of parameters");
        }

        for(int i = 0, j = 1; j < args.length; i += 2, j += 2){
            if(!args[i].startsWith("--")){
                throw new IllegalArgumentException("Arg " + args[i] + " is invalid [needs --]");
            }

            this.args.put(args[i].substring(2), args[j]);
        }
    }

    public String get(String key){
        return this.args.get(key);
    }
}