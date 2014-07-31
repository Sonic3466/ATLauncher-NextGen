package com.atlauncher.ui.panel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public final class PacksBottomPanel
extends JPanel{
    private final JButton openDirButton = new JButton("Open Dir");
    private final JButton texturesButton = new JButton("Textures");
    private final JButton modsButton = new JButton("Mods");
    private final JButton launchButton = new JButton("Launch");

    public PacksBottomPanel(){
        super(new GridBagLayout());
        this.setOpaque(false);

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