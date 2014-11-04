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
import com.atlauncher.Language;
import com.atlauncher.event.UpdateCentralEvent;
import com.atlauncher.event.UpdateI18NEvent;
import com.atlauncher.event.UpdateSettingsEvent;
import com.atlauncher.ui.comp.Card;
import com.atlauncher.ui.comp.ToggleButtonGroup;
import com.atlauncher.ui.panel.tabs.settings.CreditsTab;
import com.atlauncher.ui.panel.tabs.settings.GeneralTab;
import com.atlauncher.ui.panel.tabs.settings.JavaTab;
import com.atlauncher.ui.panel.tabs.settings.LoggingTab;
import com.atlauncher.ui.panel.tabs.settings.NetworkTab;

import com.google.common.eventbus.Subscribe;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

//TODO: Generate beta settings
public final class SettingsTab
extends JPanel
implements Card{
    private final JPanel center = new JPanel(new CardLayout());
    {
        this.register(new GeneralTab());
        this.register(new JavaTab());
        this.register(new LoggingTab());
        this.register(new NetworkTab());
        this.register(new CreditsTab());
    }

    private final JToggleButton generalButton = new JToggleButton("GENERAL", true);
    private final JToggleButton javaButton = new JToggleButton("JAVA");
    private final JToggleButton loggingButton = new JToggleButton("LOGGING");
    private final JToggleButton networkButton = new JToggleButton("NETWORK");
    private final JToggleButton creditsButton = new JToggleButton("CREDITS");

    private final ToggleButtonGroup tbg = new ToggleButtonGroup();
    {
        this.tbg.add(this.generalButton);
        this.tbg.add(this.javaButton);
        this.tbg.add(this.loggingButton);
        this.tbg.add(this.networkButton);
        this.tbg.add(this.creditsButton);
    }

    private final JPanel top = new JPanel(new GridBagLayout());
    {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.5;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets.set(5, 5, 5, 5);
        this.top.add(this.generalButton, gbc);
        gbc.gridx++;
        this.top.add(this.javaButton, gbc);
        gbc.gridx++;
        this.top.add(this.networkButton, gbc);
        gbc.gridx++;
        this.top.add(this.loggingButton, gbc);
        gbc.gridx++;
        this.top.add(this.creditsButton, gbc);
    }

    public SettingsTab(){
        super(new BorderLayout());
        this.setOpaque(false);
        this.center.setOpaque(false);
        this.top.setOpaque(false);

        this.generalButton.setBackground(Color.white);
        this.javaButton.setBackground(Color.white);
        this.networkButton.setBackground(Color.white);
        this.loggingButton.setBackground(Color.white);
        this.creditsButton.setBackground(Color.white);

        this.generalButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                ATLauncher.EVENT_BUS.post(new UpdateSettingsEvent("general", "General"));
            }
        });
        this.javaButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                ATLauncher.EVENT_BUS.post(new UpdateSettingsEvent("java", "Java"));
            }
        });
        this.networkButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                ATLauncher.EVENT_BUS.post(new UpdateSettingsEvent("network", "Network"));
            }
        });
        this.loggingButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                ATLauncher.EVENT_BUS.post(new UpdateSettingsEvent("logging", "Logging"));
            }
        });
        this.creditsButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                ATLauncher.EVENT_BUS.post(new UpdateSettingsEvent("credits", "Credits"));
            }
        });

        ATLauncher.EVENT_BUS.register(this);

        this.add(this.center, BorderLayout.CENTER);
        this.add(this.top, BorderLayout.NORTH);
    }

    @Subscribe
    public void onTitleChange(UpdateCentralEvent e){
        if(e.title.equalsIgnoreCase("settings")){
            this.tbg.setSelected(this.generalButton.getModel(), true);
            ATLauncher.EVENT_BUS.post(new UpdateSettingsEvent("general", "General"));
        }
    }

    @Subscribe
    public void onUpdateSettings(UpdateSettingsEvent e){
        ((CardLayout) this.center.getLayout()).show(this.center, e.id);
    }

    @Subscribe
    public void onUpdateI18N(UpdateI18NEvent e){
        this.generalButton.setText(Language.tr("settings.generaltab").toUpperCase());
        this.javaButton.setText(Language.tr("settings.javatab").toUpperCase());
        this.loggingButton.setText(Language.tr("settings.loggingtab").toUpperCase());
        this.networkButton.setText(Language.tr("settings.networktab").toUpperCase());
    }

    public <T extends JPanel & Card> void register(T card){
        this.center.add(card, card.id());
    }

    @Override
    public String id(){
        return "settings";
    }
}