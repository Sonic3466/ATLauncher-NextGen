package com.atlauncher;

import com.atlauncher.obj.Account;

import com.google.common.collect.ImmutableList;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public enum Accounts{
    instance;

    private final Set<Account> loaded = new HashSet<>(Arrays.asList(Account.DEFAULT));
    private Account current = Account.DEFAULT;

    public Account getCurrent(){
        return this.current;
    }

    public void setCurrent(Account acc){
        if(!this.loaded.contains(acc)){
            this.loaded.add(acc);
        }

        this.current = acc;
    }

    public List<Account> all(){
        return ImmutableList.copyOf(new LinkedList<>(this.loaded));
    }
}