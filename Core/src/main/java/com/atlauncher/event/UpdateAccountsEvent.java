package com.atlauncher.event;

import com.atlauncher.obj.Account;

public final class UpdateAccountsEvent{
    public final Account account;

    public UpdateAccountsEvent(Account account){
        this.account = account;
    }
}