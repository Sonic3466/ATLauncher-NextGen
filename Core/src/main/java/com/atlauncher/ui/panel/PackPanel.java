package com.atlauncher.ui.panel;

import com.atlauncher.Resources;
import com.atlauncher.ui.comp.Card;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
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
        this.setOpaque(false);
        this.setMinimumSize(new Dimension(347, 182));
        this.setMaximumSize(new Dimension(347, 182));
    }

    public PackPanel(String name){
        this(Resources.makeImage(name), name);
    }

    @Override
    public String id(){
        return this.id;
    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        int x = ((this.getWidth() - this.background.getWidth()) / 2) - 35;
        int y = ((this.getHeight() - this.background.getHeight()) / 2) - 99;
        g2.setColor(BACK);
        g2.fillRect(x, y, 347, 182);
        g2.drawImage(this.background, x, y, 347, 182, null);
        g2.setColor(Color.WHITE);
        g2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        g2.drawString(this.id, x + 230 - this.id.length(), y + 210);
        g2.setStroke(new BasicStroke(5));
        g2.drawRect(x - 2, y - 2, 349, 184);
    }
}