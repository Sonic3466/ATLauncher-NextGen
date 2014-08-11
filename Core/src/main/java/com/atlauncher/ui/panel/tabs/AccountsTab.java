package com.atlauncher.ui.panel.tabs;

import com.atlauncher.ATLauncher;
import com.atlauncher.Accounts;
import com.atlauncher.event.UpdateAccountsEvent;
import com.atlauncher.obj.Account;
import com.atlauncher.ui.comp.Card;
import com.atlauncher.ui.panel.CardDisplayPanel;
import com.atlauncher.ui.panel.UserPanel;

import com.google.common.eventbus.Subscribe;

import java.awt.BorderLayout;
import javax.swing.JPanel;

public final class AccountsTab
extends JPanel
implements Card{
    private final CardDisplayPanel accountsDisplayPanel = new CardDisplayPanel();

    public AccountsTab(){
        this.setLayout(new BorderLayout());
        this.setOpaque(false);
        this.add(this.accountsDisplayPanel, BorderLayout.CENTER);
        this.loadAccounts();
        ATLauncher.EVENT_BUS.register(this);
    }

    @Subscribe
    public void onAccountRegistered(UpdateAccountsEvent e){
        this.loadAccounts();
    }

    private void loadAccounts(){
        this.accountsDisplayPanel.unregisterAll();
        for(Account account : Accounts.instance.all()){
            this.accountsDisplayPanel.register(new UserPanel(account));
        }
        this.repaint();
    }

    @Override
    public String id(){
        return "accounts";
    }
}