package com.atlauncher.ui.panel.tabs;

import com.atlauncher.ui.comp.Card;
import com.atlauncher.ui.panel.CardDisplayPanel;

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
    }

    @Override
    public String id(){
        return "accounts";
    }
}