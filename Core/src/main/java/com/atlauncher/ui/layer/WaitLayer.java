package com.atlauncher.ui.layer;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import javax.swing.JComponent;
import javax.swing.JLayer;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.plaf.LayerUI;

public final class WaitLayer
extends LayerUI<JPanel>
implements ActionListener{
    private final Timer timer = new Timer(1000 / 30, this);
    private final int FADE_LIMIT = 15;

    private boolean isFading = false;
    private boolean running = false;
    private int angle;
    private int fadeCount;

    @Override
    public void paint(Graphics g, JComponent comp){
        Graphics2D g2 = (Graphics2D) g;
        super.paint(g2, comp);

        if(!running){
            return;
        }

        float fade = (float) fadeCount / FADE_LIMIT;
        Composite c = g2.getComposite();
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5F * fade));
        g2.fillRect(0, 0, comp.getWidth(), comp.getHeight());
        g2.setComposite(c);

        int s = Math.min(comp.getWidth(), comp.getHeight()) / 5;
        int dX = comp.getWidth() / 2;
        int dY = comp.getHeight() / 2;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(s / 4, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.setColor(Color.white);
        g2.rotate(Math.PI * angle / 180, dX, dY);
        for(int i = 0; i < 12; i++){
            float scale = (11.0F - i) / 11.0F;
            g2.drawLine(dX + s, dY, dX + s * 2, dY);
            g2.rotate(-Math.PI / 6, dX, dY);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, scale * fade));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(running){
            firePropertyChange("tick", 0, 1);
            angle += 3;
            if(angle >= 360){
                angle = 0;
            }
            if(isFading){
                if(--fadeCount == 0){
                    running = false;
                    timer.stop();
                }
            } else if(fadeCount < FADE_LIMIT){
                fadeCount++;
            }
        }
    }

    public void start(){
        if(running){
            return;
        }

        running = true;
        isFading = false;
        fadeCount = 0;
        timer.start();
    }

    public void stop(){
        isFading = true;
    }

    @Override
    public void applyPropertyChange(PropertyChangeEvent pce, JLayer layer){
        if(pce.getPropertyName().equalsIgnoreCase("tick")){
            layer.repaint();
        }
    }
}