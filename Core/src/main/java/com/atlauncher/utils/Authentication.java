package com.atlauncher.utils;

import com.atlauncher.obj.Account;
import com.mojang.authlib.Agent;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;

import java.net.Proxy;
import java.util.UUID;

public final class Authentication{
    private static final UUID client_id = UUID.randomUUID();
    private static final YggdrasilAuthenticationService y = new YggdrasilAuthenticationService(Proxy.NO_PROXY, null);
    private static final YggdrasilUserAuthentication user_auth = (YggdrasilUserAuthentication) new YggdrasilAuthenticationService(Proxy.NO_PROXY, "1").createUserAuthentication(Agent.MINECRAFT);

    private Authentication(){}

    public static boolean login(String username, String password){
        user_auth.logOut();
        user_auth.setUsername(username);
        user_auth.setPassword(password);
        if(user_auth.canLogIn()){
            try{
                user_auth.logIn();
                return true;
            } catch(AuthenticationException e){
                e.printStackTrace(System.err);
                return false;
            }
        } else{
            System.err.println("Cannot login");
            return false;
        }
    }

    public static String getDisplayName(){
        return user_auth.getSelectedProfile().getName();
    }

    public static Account get(String username, String password){
        return (login(username, password) ? new Account(getDisplayName()).updateSkin() : null);
    }
}