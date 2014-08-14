package com.atlauncher.ui.frame;

import com.atlauncher.ui.Screen;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public final class ProgressFrame
extends JFrame{
    public final JLabel title = new JLabel("Downloading");
    public final JProgressBar bar = new JProgressBar(0, 100);
    private final GridBagConstraints gbc = new GridBagConstraints();

    public ProgressFrame(String title){
        super("Doing Stuff");
        this.title.setText(title);
        this.setLayout(new GridBagLayout());
        this.setUndecorated(true);
        this.getContentPane().setBackground(new Color(40, 45, 50));
        this.title.setForeground(Color.white);
        this.title.setFont(this.title.getFont().deriveFont(18.0F));
        this.gbc.gridx = 0;
        this.gbc.gridy = 0;
        this.gbc.insets.set(5, 5, 5, 5);
        this.gbc.anchor = GridBagConstraints.LINE_START;
        this.gbc.fill = GridBagConstraints.BOTH;
        this.add(this.title, this.gbc);
        this.gbc.gridy++;
        this.add(this.bar, this.gbc);
        this.pack();
        this.setLocation((int) (Screen.SIZE.getWidth() - this.getWidth()) / 2, (int) (Screen.SIZE.getHeight() - this.getHeight()) / 2);
    }
}