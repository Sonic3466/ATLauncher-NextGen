package com.atlauncher.ui.panel;

import com.atlauncher.Resources;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public final class BackPanel
extends JPanel{
    private final BufferedImage b = Resources.makeImage("background");

    public BackPanel(){
        super(new BorderLayout());
    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(this.b, 0, 0, this.b.getWidth(), this.b.getHeight(), null);
    }
}