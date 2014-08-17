package com.atlauncher.ui.panel;

import com.atlauncher.ATLauncher;
import com.atlauncher.Resources;
import com.atlauncher.event.UpdateCentralEvent;
import com.atlauncher.plaf.UIUtils;

import com.google.common.eventbus.Subscribe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

//TODO: Rewrite & Optimize
public final class BackPanel
extends JPanel{
    private final Map<String, BufferedImage> backgrounds = new HashMap<>();
    {
        this.backgrounds.put("scene", Resources.makeImage("background/scene"));
        this.backgrounds.put("white", UIUtils.createColoredBackground(Color.WHITE));
        this.backgrounds.put("gray", UIUtils.createColoredBackground(UIUtils.GRAY));
    }

    private volatile BufferedImage b = UIUtils.createColoredBackground(UIUtils.GRAY);

    public BackPanel(){
        super(new BorderLayout());
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));

        ATLauncher.EVENT_BUS.register(this);
    }

    @Subscribe
    public void onBackgroundChange(final UpdateCentralEvent event){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                b = backgrounds.get(event.background);
                repaint();
            }
        });
    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(this.b, 0, 0, this.getWidth() - 98, this.getHeight() - 26, null);
        g2.setColor(Color.white);
        g2.fillRect(this.getWidth() - 99, 0, 99, this.getHeight());
        g2.fillRect(0, this.getHeight() - 26, this.getWidth(), 26);
    }
}