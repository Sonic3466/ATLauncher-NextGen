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