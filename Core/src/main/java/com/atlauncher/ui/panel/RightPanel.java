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
import com.atlauncher.Accounts;
import com.atlauncher.Language;
import com.atlauncher.event.UpdateCentralEvent;
import com.atlauncher.event.UpdateI18NEvent;
import com.atlauncher.ui.comp.ToggleButtonGroup;

import com.google.common.eventbus.Subscribe;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public final class RightPanel
extends JPanel{
    private final JToggleButton newsButton = new JToggleButton("NEWS", true);
    private final JToggleButton modpacksButton = new JToggleButton("PACKS");
    private final JToggleButton instancesButton = new JToggleButton("INSTANCES");
    private final JToggleButton accountsButton = new JToggleButton("ACCOUNTS");
    private final JToggleButton settingsButton = new JToggleButton("SETTINGS");

    private final ToggleButtonGroup tbg = new ToggleButtonGroup();
    {
        this.tbg.add(this.newsButton);
        this.tbg.add(this.modpacksButton);
        this.tbg.add(this.instancesButton);
        this.tbg.add(this.settingsButton);
        this.tbg.add(this.accountsButton);
    }

    public RightPanel(){
        super(new GridBagLayout());
        this.setOpaque(false);

        ATLauncher.EVENT_BUS.register(this);

        this.newsButton.setBackground(Color.WHITE);
        this.settingsButton.setBackground(Color.WHITE);
        this.modpacksButton.setBackground(Color.WHITE);
        this.instancesButton.setBackground(Color.WHITE);
        this.accountsButton.setBackground(Color.WHITE);

        this.newsButton.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e){
                if(newsButton.isSelected()){
                    ATLauncher.EVENT_BUS.post(new UpdateCentralEvent("News", "news", "gray"));
                }
            }
        });
        this.settingsButton.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e){
                if(settingsButton.isSelected()){
                    ATLauncher.EVENT_BUS.post(new UpdateCentralEvent("Settings", "settings", "gray"));
                }
            }
        });
        this.instancesButton.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e){
                if(instancesButton.isSelected()){
                    ATLauncher.EVENT_BUS.post(new UpdateCentralEvent("Instances", "instances", "scene"));
                }
            }
        });
        this.modpacksButton.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e){
                if(modpacksButton.isSelected()){
                    ATLauncher.EVENT_BUS.post(new UpdateCentralEvent("Modpacks", "modpacks", "scene"));
                }
            }
        });
        this.accountsButton.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e){
                if(accountsButton.isSelected()){
                    ATLauncher.EVENT_BUS.post(new UpdateCentralEvent("Accounts", "accounts", "gray"));
                }
            }
        });

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weighty = 0.1;
        c.gridx = 0;
        c.gridy = 1;
        this.add(new HeadPanel(Accounts.instance.getCurrent()), c);
        c.gridy++;
        this.add(this.newsButton, c);
        c.gridy++;
        this.add(this.modpacksButton, c);
        c.gridy++;
        this.add(this.instancesButton, c);
        c.gridy++;
        this.add(this.accountsButton, c);
        c.gridy++;
        this.add(this.settingsButton, c);
    }

    @Subscribe
    public void onI18NUpdate(UpdateI18NEvent e){
        this.newsButton.setText(Language.tr("tabs.news"));
        this.modpacksButton.setText(Language.tr("tabs.packs"));
        this.instancesButton.setText(Language.tr("tabs.instances"));
        this.settingsButton.setText(Language.tr("tabs.settings"));
        this.accountsButton.setText(Language.tr("tabs.account"));
    }
}