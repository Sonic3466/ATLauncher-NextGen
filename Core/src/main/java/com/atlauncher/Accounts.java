package com.atlauncher;

import com.atlauncher.obj.Account;

import java.util.Arrays;
import java.util.List;

public enum Accounts{
    instance;

    private final List<Account> loaded = Arrays.asList(Account.DEFAULT);
    private Account current = Account.DEFAULT;

    public Account getCurrent(){
        return this.current;
    }

    public void signIn(String email, String password){
        
    }
}