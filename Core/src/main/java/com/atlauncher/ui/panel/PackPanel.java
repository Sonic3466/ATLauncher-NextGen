package com.atlauncher.ui.panel;

import com.atlauncher.obj.Pack;
import com.atlauncher.plaf.UIUtils;
import com.atlauncher.ui.comp.Card;
import com.atlauncher.utils.Strings;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
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

    public final Pack pack;

    public PackPanel(Pack pack){
        this.pack = pack;
        this.setOpaque(false);
        this.setMinimumSize(new Dimension(347, 182));
        this.setMaximumSize(new Dimension(347, 182));
    }

    @Override
    public String id(){
        return this.pack.getSafeName();
    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        // Draw Image
        BufferedImage background = this.pack.getImage();
        int x = ((this.getWidth() - background.getWidth()) / 2) - 20;
        int y = ((this.getHeight() - background.getHeight()) / 2) - 80;
        g2.setColor(BACK);
        g2.fillRect(x, y, 347, 182);
        g2.drawImage(background, x, y, 347, 182, null);

        // Draw Title
        g2.setColor(Color.white);
        g2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        int curr = ((PackDisplayPanel) this.getParent().getParent()).curr() + 1;
        int max = ((PackDisplayPanel) this.getParent().getParent()).max();
        String disp = this.pack.name + " " + curr + "/" + max;
        g2.drawString(disp, x + ((347 - g2.getFontMetrics().stringWidth(disp)) / 2), y + 210);

        // Draw Description
        int wrap = 65;
        String wrapped = Strings.wrap(this.pack.description, wrap);
        int dX = 25;
        int dY = y + 235;
        Composite comp = g2.getComposite();
        g2.setColor(Color.darkGray);
        g2.setComposite(UIUtils.alpha(0.25F));
        g2.fillRect(0, dY - 5, this.getWidth(), this.getHeight());
        g2.setColor(Color.white);
        g2.setComposite(comp);
        for(String str : wrapped.split("\n")){
            g2.drawString(str, dX, dY += g2.getFontMetrics().getHeight());
        }
        g2.setStroke(new BasicStroke(5));
        g2.drawRect(x - 2, y - 2, 349, 184);

        // Draw Version
        g2.setColor(Color.green);
        Rectangle rect;
        try{
            rect = new Rectangle(x - 25, y + 15, g2.getFontMetrics().stringWidth(this.pack.getLatest().version) + 4,
                    g2.getFontMetrics().getHeight() + 4);
        } catch(NullPointerException e){
            rect = new Rectangle(x - 25, y + 15, g2.getFontMetrics().stringWidth("0.0.0"), g2.getFontMetrics().getHeight() + 4);
        }
        g2.fill(rect);
        g2.setColor(Color.white);
        try{
            g2.drawString(this.pack.getLatest().version, x - 23, y + 13 + g2.getFontMetrics().getHeight());
        } catch(NullPointerException e){
            g2.drawString("0.0.0", x - 24, y + 13 + g2.getFontMetrics().getHeight());
        }
    }
}