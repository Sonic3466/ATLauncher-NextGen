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
import com.atlauncher.Settings;
import com.atlauncher.event.ChangePackUIEvent;
import com.atlauncher.event.UpdatePacksEvent;
import com.atlauncher.thread.CollectPacksWorker;
import com.atlauncher.ui.comp.Card;

import com.google.common.eventbus.Subscribe;

import java.awt.BorderLayout;
import javax.swing.JPanel;

public final class PacksTab
extends JPanel
implements Card{
    private JPanel currentUI = Settings.packUI.panel();

    public PacksTab(){
        super(new BorderLayout());
        this.setOpaque(false);

        ATLauncher.EVENT_BUS.register(this);

        this.add(currentUI, BorderLayout.CENTER);
    }

    @Subscribe
    public void onAccountUpdate(UpdatePacksEvent e){
        ATLauncher.LOGGER.info("Collecting Packs");
        new CollectPacksWorker().execute();
    }

    @Subscribe
    public void changeUI(ChangePackUIEvent e){
        this.remove(currentUI);
        this.currentUI = e.ui.panel();
        this.add(this.currentUI, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }

    @Override
    public String id(){
        return "modpacks";
    }
}