package com.atlauncher.ui.panel;

import com.atlauncher.ui.comp.ToggleButtonGroup;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public final class BottomPanel
extends JPanel{
    private final JToggleButton openDirButton = new JToggleButton("Open Dir");
    private final JToggleButton texturesButton = new JToggleButton("Textures");
    private final JToggleButton modsButton = new JToggleButton("Mods");
    private final JToggleButton launchButton = new JToggleButton("Launch");

    private final ToggleButtonGroup tbg = new ToggleButtonGroup();
    {
        this.tbg.add(this.openDirButton);
        this.tbg.add(this.texturesButton);
        this.tbg.add(this.modsButton);
        this.tbg.add(this.launchButton);
    }

    public BottomPanel(){
        super(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        c.insets.set(0, 0, 0, 3);
        this.add(this.openDirButton, c);
        c.gridx++;
        this.add(this.texturesButton, c);
        c.gridx++;
        this.add(this.modsButton, c);
        c.gridx++;
        c.insets.set(0, 0, 0, 0);
        this.add(this.launchButton, c);
    }
}