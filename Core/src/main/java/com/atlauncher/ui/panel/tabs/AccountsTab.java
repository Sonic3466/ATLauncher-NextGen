package com.atlauncher.ui.panel.tabs;

import com.atlauncher.ui.comp.Card;

import javax.swing.JPanel;

public final class AccountsTab
extends JPanel
implements Card{
    public AccountsTab(){
        this.setOpaque(false);
    }

    @Override
    public String id(){
        return "accounts";
    }
}