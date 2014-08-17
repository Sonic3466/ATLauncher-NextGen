package com.atlauncher.ui.panel.tabs;

import com.atlauncher.ATLauncher;
import com.atlauncher.event.UpdateCentralEvent;
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
    public void onUpdateSettins(UpdateSettingsEvent e){
        ((CardLayout) this.center.getLayout()).show(this.center, e.id);
    }

    public <T extends JPanel & Card> void register(T card){
        this.center.add(card, card.id());
    }

    @Override
    public String id(){
        return "settings";
    }
}