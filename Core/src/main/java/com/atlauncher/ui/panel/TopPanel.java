package com.atlauncher.ui.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public final class TopPanel
extends JPanel{
    public TopPanel(){
        super(new GridBagLayout());
        this.setOpaque(false);

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        c.insets.set(25, 25, 0, 15);
        this.add(new JLabel("ATLauncher"){
            {
                this.setFont(new Font(getFont().getName(), Font.BOLD, getFont().getSize()));
                this.setForeground(Color.WHITE);
            }
        }, c);
        c.gridy++;
        c.insets.set(10, 25, 25, 15);
        this.add(new JLabel("ATLauncher"){
            {
                this.setForeground(Color.WHITE);
            }
        }, c);
    }
}