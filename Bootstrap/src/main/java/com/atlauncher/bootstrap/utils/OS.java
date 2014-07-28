package com.atlauncher.bootstrap.utils;

import java.util.StringTokenizer;

public enum OS{
    WINDOWS("win"),
    MAC("mac"),
    UNIX("nix:nux:aix"),
    SOLARIS("sunos");

    public static final String STRING = System.getProperty("os.name").toLowerCase();

    private final String key;

    private OS(String key){
        this.key = key;
    }

    public String[] getKeys(){
        return this.key.split(":");
    }

    public boolean isCurrent(){
        for(String key : this.key.split(":")){
            if(OS.STRING.contains(key)){
                return true;
            }
        }

        return false;
    }

    public static OS getCurrent(){
        for(OS os : OS.values()){
            if(os.isCurrent()){
                return os;
            }
        }

        return null;
    }

    @Override
    public String toString(){
        StringTokenizer t = new StringTokenizer(this.name());
        StringBuilder builder = new StringBuilder();

        while(t.hasMoreTokens()){
            String token = t.nextToken();
            builder.append(token.toUpperCase().charAt(0))
                    .append(token.toLowerCase().substring(1));
            if(t.hasMoreTokens()){
                builder.append(" ");
            }
        }

        return builder.toString();
    }
}