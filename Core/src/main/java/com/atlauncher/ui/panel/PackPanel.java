package com.atlauncher.ui.panel;

import com.atlauncher.obj.Pack;
import com.atlauncher.ui.comp.Card;
import com.atlauncher.utils.Strings;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public final class PackPanel
extends JPanel
implements Card{
    private static final Color BACK = new Color(55, 55, 55);

    private final Pack pack;

    private boolean hovering = false;

    public PackPanel(Pack pack){
        this.pack = pack;
        this.setOpaque(false);
        this.setMinimumSize(new Dimension(347, 182));
        this.setMaximumSize(new Dimension(347, 182));
    }

    @Override
    public String id(){
        return this.pack.display_name.toLowerCase().replace(" ", "");
    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        BufferedImage background = this.pack.getImage();
        int x = ((this.getWidth() - background.getWidth()) / 2) - 25;
        int y = ((this.getHeight() - background.getHeight()) / 2) - 75;
        g2.setColor(BACK);
        g2.fillRect(x, y, 347, 182);
        g2.drawImage(background, x, y, 347, 182, null);
        g2.setColor(Color.WHITE);
        g2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        g2.drawString(this.pack.display_name, x + 230 - this.pack.display_name.length(), y + 210);
        int wrap = 50;
        String wrapped = Strings.wrap(this.pack.description, wrap);
        int dX = x;
        int dY = y + 235;
        for(String str : wrapped.split("\n")){
            g2.drawString(str, dX, dY += g2.getFontMetrics().getHeight());
        }
        g2.setStroke(new BasicStroke(5));
        g2.drawRect(x - 2, y - 2, 349, 184);
        g2.setColor(Color.GREEN);
        Rectangle rect = new Rectangle(x - 25, y + 15, g2.getFontMetrics().stringWidth(this.pack.version) + 4,
                g2.getFontMetrics().getHeight() + 4);
        g2.fill(rect);
        g2.setColor(Color.WHITE);
        g2.drawString(this.pack.version, x - 23, y + 13 + g2.getFontMetrics().getHeight());
    }
}