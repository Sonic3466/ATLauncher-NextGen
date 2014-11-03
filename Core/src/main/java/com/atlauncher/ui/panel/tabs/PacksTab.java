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
import com.atlauncher.event.PackLoadedEvent;
import com.atlauncher.event.UpdatePacksEvent;
import com.atlauncher.thread.CollectPacksRunnable;
import com.atlauncher.ui.comp.Card;
import com.atlauncher.ui.panel.CenterBottomPanel;
import com.atlauncher.ui.panel.PackDisplayPanel;
import com.atlauncher.ui.panel.PackPanel;
import com.atlauncher.ui.panel.PacksBottomPanel;

import com.google.common.eventbus.Subscribe;

import java.awt.BorderLayout;
import javax.swing.JPanel;

public final class PacksTab
extends JPanel
implements Card{
    private final PacksBottomPanel bottomPanel = new PacksBottomPanel();
    private final PackDisplayPanel packDisplayPanel = new PackDisplayPanel();

    private final JPanel bottom = new JPanel(new BorderLayout());
    {
        this.bottom.add(new CenterBottomPanel(), BorderLayout.NORTH);
        this.bottom.add(this.bottomPanel, BorderLayout.CENTER);
        this.bottom.setOpaque(false);
    }

    public PacksTab(){
        super(new BorderLayout());
        this.setOpaque(false);

        ATLauncher.EVENT_BUS.register(this);

        this.add(this.bottom, BorderLayout.SOUTH);
        this.add(this.packDisplayPanel, BorderLayout.CENTER);
    }

    @Subscribe
    public void onAccountUpdate(UpdatePacksEvent e){
        this.packDisplayPanel.unregisterAll();
        new CollectPacksRunnable().execute();
    }

    @Subscribe
    public void onPackLoaded(PackLoadedEvent e){
        this.packDisplayPanel.register(new PackPanel(e.pack));
        this.packDisplayPanel.repaint();
        this.packDisplayPanel.revalidate();
    }

    @Override
    public String id(){
        return "modpacks";
    }
}