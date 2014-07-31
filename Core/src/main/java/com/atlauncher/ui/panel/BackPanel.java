package com.atlauncher.ui.panel;

import com.atlauncher.ATLauncher;
import com.atlauncher.Resources;
import com.atlauncher.event.BackgroundChangeEvent;

import com.google.common.eventbus.Subscribe;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public final class BackPanel
extends JPanel{
    private final Map<String, BufferedImage> backgrounds = new HashMap<>();
    {
        this.backgrounds.put("scene", Resources.makeImage("background/scene"));
    }

    private volatile BufferedImage b = Resources.makeImage("background/scene");

    public BackPanel(){
        super(new BorderLayout());

        ATLauncher.EVENT_BUS.register(this);
    }

    @Subscribe
    public void onBackgroundChange(final BackgroundChangeEvent event){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                b = backgrounds.get(event.id);
                repaint();
            }
        });
    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(this.b, 0, 0, this.b.getWidth(), this.b.getHeight(), null);
    }
}