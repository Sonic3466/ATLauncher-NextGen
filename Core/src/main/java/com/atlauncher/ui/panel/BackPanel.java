package com.atlauncher.ui.panel;

import com.atlauncher.ATLauncher;
import com.atlauncher.plaf.UIUtils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

//TODO: Rewrite & Optimize
public final class BackPanel
extends JPanel{
    private volatile BufferedImage b = UIUtils.createColoredBackground(UIUtils.GRAY);

    public BackPanel(){
        super(new BorderLayout());
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));

        ATLauncher.EVENT_BUS.register(this);
    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(this.b, 0, 0, this.getWidth() - 100, this.getHeight() - 26, null);
        g2.setColor(Color.white);
        g2.fillRect(this.getWidth() - 108, 0, 108, this.getHeight());
        g2.setColor(UIUtils.GRAY);
        g2.fillRect(0, this.getHeight() - 26, this.getWidth(), 26);
    }
}