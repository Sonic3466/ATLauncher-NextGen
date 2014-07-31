package com.atlauncher.ui.panel;

import com.atlauncher.Resources;
import com.atlauncher.ui.comp.Card;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public final class PackPanel
extends JPanel
implements Card{
    private static final Color BACK = new Color(55, 55, 55);

    private final BufferedImage background;
    private final String id;

    public PackPanel(BufferedImage image, String name){
        this.background = image;
        this.id = name;
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
    }

    public PackPanel(String name){
        this(Resources.makeImage(name), name);
    }

    @Override
    public String id(){
        return this.id;
    }

    @Override
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        int x = (this.getWidth() - this.background.getWidth()) / 2;
        int y = (this.getHeight() - this.background.getHeight()) / 2;
        g2.setColor(BACK);
        g2.fillRect(x, y, 347, 182);
        g2.drawImage(this.background, x, y, 347, 182, null);
    }
}