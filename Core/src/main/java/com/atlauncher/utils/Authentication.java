/*
 * ATLauncher-NextGen - https://github.com/ATLauncher/ATLauncher-NextGen
 * Copyright (C) 2013 ATLauncher
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
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