package com.atlauncher.ui.panel;

import com.atlauncher.obj.Account;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.JPanel;

public final class HeadPanel
extends JPanel{
    private final Image head;
    private final Account account;
    private final int scale = 64;

    public HeadPanel(Account account){
        this.account = account;
        this.head = account.getHead();
        this.setPreferredSize(new Dimension(this.scale, this.scale));
        this.setToolTipText(account.name);
    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        int x = ((this.getWidth() - this.scale) / 2);
        int y = ((this.getHeight() - this.scale) / 2) - 15;
        g2.drawImage(this.head, x, y, this.scale, this.scale, null);
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(2));
        Rectangle border = new Rectangle(x, y, this.scale, this.scale);
        g2.draw(border);
        g2.drawString(this.account.name, x - 5, y + scale + 5 + g2.getFontMetrics().getHeight());
    }
}