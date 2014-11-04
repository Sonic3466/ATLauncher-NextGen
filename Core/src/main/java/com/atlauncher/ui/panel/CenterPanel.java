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
package com.atlauncher.ui.panel;

import com.atlauncher.ATLauncher;
import com.atlauncher.event.UpdateCentralEvent;
import com.atlauncher.ui.comp.Card;
import com.atlauncher.ui.panel.tabs.AccountsTab;
import com.atlauncher.ui.panel.tabs.InstancesTab;
import com.atlauncher.ui.panel.tabs.NewsTab;
import com.atlauncher.ui.panel.tabs.PacksTab;
import com.atlauncher.ui.panel.tabs.SettingsTab;

import com.google.common.eventbus.Subscribe;

import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public final class CenterPanel
extends JPanel{
    private final PacksTab packs = new PacksTab();
    private final NewsTab news = new NewsTab();
    private final AccountsTab accounts = new AccountsTab();
    private final InstancesTab instances = new InstancesTab();
    private final SettingsTab settings = new SettingsTab();

    public CenterPanel(){
        super(new CardLayout());
        this.setOpaque(false);

        ATLauncher.EVENT_BUS.register(this);

        this.register(this.news);
        this.register(this.packs);
        this.register(this.accounts);
        this.register(this.instances);
        this.register(this.settings);
    }

    @Subscribe
    public void onShow(final UpdateCentralEvent event){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                ((CardLayout) getLayout()).show(CenterPanel.this, event.panel);
                revalidate();
            }
        });
    }

    public <T extends JPanel & Card> void register(T card){
        this.add(card, card.id());
    }
}