package com.atlauncher.utils;

import com.atlauncher.ATLauncher;
import com.atlauncher.Accounts;
import com.atlauncher.annot.UpdateAccounts;
import com.atlauncher.annot.UpdatePacks;
import com.atlauncher.obj.Account;
import com.atlauncher.ui.diag.LoadingDialog;
import com.mojang.authlib.Agent;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;

import java.net.Proxy;
import java.util.concurrent.Callable;

public final class Authentication{
    private static final YggdrasilUserAuthentication user_auth = (YggdrasilUserAuthentication) new YggdrasilAuthenticationService(Proxy.NO_PROXY, "1").createUserAuthentication(Agent.MINECRAFT);

    private Authentication(){}

    /**
     * Creates an account from the specified username and password.
     * Updates the packs and accounts after method run.
     *
     * @param u <- Username/Email
     * @param p <- Password
     * @throws Exception
     */
    @UpdatePacks
    @UpdateAccounts
    public static void create(final String u, final String p)
    throws Exception{
        try{
            final LoadingDialog diag = new LoadingDialog("Logging In");
            final Account acc = ATLauncher.TASKS.submit(new Callable<Account>(){
                @Override
                public Account call()
                throws Exception{
                    diag.title.setText("Loggin In");
                    diag.bar.setValue(50);
                    return get(u, p);
                }
            }).get();

            if(acc == null){
                System.out.println("Error Logging In");
            }

            diag.bar.setValue(100);
            Accounts.instance.setCurrent(acc);
        } catch(Exception ex){
            ATLauncher.LOGGER.error(ex);
            return;
        }
    }

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
        return (login(username, password) ? new Account(getDisplayName(), username).updateSkin() : null);
    }
}