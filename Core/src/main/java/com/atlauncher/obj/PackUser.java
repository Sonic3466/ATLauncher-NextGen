package com.atlauncher.obj;

public final class PackUser{
    public final String pack_name;
    public final String[] testers;
    public final String[] allowed_players;

    public PackUser(String pack_name, String[] testers, String[] allowed_players){
        this.pack_name = pack_name;
        this.testers = testers;
        this.allowed_players = allowed_players;
    }
}