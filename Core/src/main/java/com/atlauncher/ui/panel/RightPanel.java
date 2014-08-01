package com.atlauncher.ui.panel;

import com.atlauncher.ATLauncher;
import com.atlauncher.Accounts;
import com.atlauncher.event.BackgroundChangeEvent;
import com.atlauncher.event.ShowEvent;
import com.atlauncher.ui.comp.ToggleButtonGroup;

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
    private final JToggleButton modpacksButton = new JToggleButton("MODPACKS");
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

        this.newsButton.setBackground(Color.WHITE);
        this.settingsButton.setBackground(Color.WHITE);
        this.modpacksButton.setBackground(Color.WHITE);
        this.instancesButton.setBackground(Color.WHITE);
        this.accountsButton.setBackground(Color.WHITE);

        this.newsButton.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e){
                if(newsButton.isSelected()){
                    ATLauncher.EVENT_BUS.post(new ShowEvent("news"));
                    ATLauncher.EVENT_BUS.post(new BackgroundChangeEvent("gray"));
                }
            }
        });
        this.settingsButton.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e){
                if(settingsButton.isSelected()){
                    ATLauncher.EVENT_BUS.post(new ShowEvent("settings"));
                    ATLauncher.EVENT_BUS.post(new BackgroundChangeEvent("gray"));
                }
            }
        });
        this.instancesButton.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e){
                if(instancesButton.isSelected()){
                    ATLauncher.EVENT_BUS.post(new ShowEvent("instances"));
                    ATLauncher.EVENT_BUS.post(new BackgroundChangeEvent("scene"));
                }
            }
        });
        this.modpacksButton.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e){
                if(modpacksButton.isSelected()){
                    ATLauncher.EVENT_BUS.post(new ShowEvent("modpacks"));
                    ATLauncher.EVENT_BUS.post(new BackgroundChangeEvent("scene"));
                }
            }
        });
        this.accountsButton.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e){
                if(accountsButton.isSelected()){
                    ATLauncher.EVENT_BUS.post(new ShowEvent("accounts"));
                    ATLauncher.EVENT_BUS.post(new BackgroundChangeEvent("gray"));
                }
            }
        });

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weighty = 0.5;
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
}