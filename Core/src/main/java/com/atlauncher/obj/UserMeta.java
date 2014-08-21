package com.atlauncher.obj;

import com.atlauncher.annot.Json;


@Json
public final class UserMeta{
    public final String[] allowed_player;
    public final String[] tester;

    public UserMeta(String[] allowed_player, String[] tester){
        this.allowed_player = allowed_player;
        this.tester = tester;
    }
}