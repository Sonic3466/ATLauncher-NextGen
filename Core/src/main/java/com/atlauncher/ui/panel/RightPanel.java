package com.atlauncher.ui.panel;

import com.atlauncher.Resources;
import com.atlauncher.ui.comp.ToggleButtonGroup;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public final class RightPanel
extends JPanel{
    private static final Color GRAY = new Color(140, 150, 165);

    private final JToggleButton modpacksButton = new JToggleButton("MODPACKS");
    private final JToggleButton instancesButton = new JToggleButton("INSTANCES");
    private final JToggleButton settingsButton = new JToggleButton("SETTINGS", new ImageIcon(Resources.makeImage("icons/settings_off")));
    {
        this.settingsButton.setForeground(GRAY);
        this.settingsButton.setBackground(Color.WHITE);
    }

    private final ToggleButtonGroup tbg = new ToggleButtonGroup();
    {
        this.tbg.add(this.modpacksButton);
        this.tbg.add(this.instancesButton);
        this.tbg.add(this.settingsButton);
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
        this.add(this.settingsButton, c);
    }
}