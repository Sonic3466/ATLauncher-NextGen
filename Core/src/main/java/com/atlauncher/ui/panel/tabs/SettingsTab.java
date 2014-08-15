package com.atlauncher.ui.panel.tabs;

import com.atlauncher.ATLauncher;
import com.atlauncher.event.UpdateCentralEvent;
import com.atlauncher.event.UpdateSettingsEvent;
import com.atlauncher.ui.comp.Card;
import com.atlauncher.ui.comp.ToggleButtonGroup;
import com.atlauncher.ui.panel.CardDisplayPanel;
import com.atlauncher.ui.panel.tabs.settings.CreditsTab;
import com.atlauncher.ui.panel.tabs.settings.GeneralTab;
import com.atlauncher.ui.panel.tabs.settings.JavaTab;
import com.atlauncher.ui.panel.tabs.settings.LoggingTab;
import com.atlauncher.ui.panel.tabs.settings.NetworkTab;

import com.google.common.eventbus.Subscribe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public final class SettingsTab
extends JPanel
implements Card{
    private final CardDisplayPanel center = new CardDisplayPanel(false);
    {
        this.center.register(new GeneralTab());
        this.center.register(new JavaTab());
        this.center.register(new NetworkTab());
        this.center.register(new LoggingTab());
        this.center.register(new CreditsTab());
    }

    private final JToggleButton generalButton = new JToggleButton("General", true);
    private final JToggleButton javaButton = new JToggleButton("Java");
    private final JToggleButton loggingButton = new JToggleButton("Logging");
    private final JToggleButton networkButton = new JToggleButton("Network");
    private final JToggleButton creditsButton = new JToggleButton("Credits");

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
        this.top.setOpaque(false);

        this.generalButton.setBackground(Color.white);
        this.javaButton.setBackground(Color.white);
        this.networkButton.setBackground(Color.white);
        this.loggingButton.setBackground(Color.white);
        this.creditsButton.setBackground(Color.white);

        this.generalButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                ATLauncher.EVENT_BUS.post(new UpdateSettingsEvent("General"));
            }
        });
        this.javaButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                ATLauncher.EVENT_BUS.post(new UpdateSettingsEvent("Java"));
            }
        });
        this.networkButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                ATLauncher.EVENT_BUS.post(new UpdateSettingsEvent("Network"));
            }
        });
        this.loggingButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                ATLauncher.EVENT_BUS.post(new UpdateSettingsEvent("Logging"));
            }
        });
        this.creditsButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                ATLauncher.EVENT_BUS.post(new UpdateSettingsEvent("Credits"));
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
            ATLauncher.EVENT_BUS.post(new UpdateSettingsEvent("General"));
        }
    }

    @Override
    public String id(){
        return "settings";
    }
}