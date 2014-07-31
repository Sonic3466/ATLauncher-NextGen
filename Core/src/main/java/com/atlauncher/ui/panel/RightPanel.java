package com.atlauncher.ui.panel;

import com.atlauncher.ui.comp.ToggleButtonGroup;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public final class RightPanel
extends JPanel{
    private static final Color GRAY = new Color(140, 150, 165);

    private final JToggleButton modpacksButton = new JToggleButton("MODPACKS");
    private final JToggleButton instancesButton = new JToggleButton("INSTANCES");
    private final JToggleButton accountsButton = new JToggleButton("ACCOUNTS");
    private final JToggleButton settingsButton = new JToggleButton("SETTINGS");
    {
        this.settingsButton.setForeground(GRAY);
        this.settingsButton.setBackground(Color.WHITE);
        this.modpacksButton.setForeground(GRAY);
        this.modpacksButton.setBackground(Color.WHITE);
        this.instancesButton.setForeground(GRAY);
        this.instancesButton.setBackground(Color.WHITE);
        this.accountsButton.setForeground(GRAY);
        this.accountsButton.setBackground(Color.WHITE);
    }

    private final ToggleButtonGroup tbg = new ToggleButtonGroup();
    {
        this.tbg.add(this.modpacksButton);
        this.tbg.add(this.instancesButton);
        this.tbg.add(this.settingsButton);
        this.tbg.add(this.accountsButton);
    }

    public RightPanel(){
        super(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weighty = 0.5;
        c.gridx = 0;
        c.gridy = 1;
        this.add(this.modpacksButton, c);
        c.gridy++;
        this.add(this.instancesButton, c);
        c.gridy++;
        this.add(this.accountsButton, c);
        c.gridy++;
        this.add(this.settingsButton, c);
    }
}