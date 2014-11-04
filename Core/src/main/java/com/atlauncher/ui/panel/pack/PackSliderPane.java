package com.atlauncher.ui.panel.pack;

import com.atlauncher.Resources;
import com.atlauncher.event.PackLoadedEvent;
import com.atlauncher.obj.Pack;

import com.google.common.eventbus.Subscribe;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JPanel;

/**
 * Stack based slider panel, similar to TechnicLauncher's UI
 */
public final class PackSliderPane
extends JPanel
implements MouseWheelListener{
    private final List<Pack> packs = new LinkedList<>();
    private int ptr = 0;

    public PackSliderPane(){
        this.addMouseWheelListener(this);
    }

    public Pack current(){
        if(this.packs.isEmpty()){
            return null;
        } else{
            return this.packs.get(this.ptr);
        }
    }

    public Pack lastPack(){
        if(this.ptr <= 0){
            return null;
        } else{
            return this.packs.get(this.ptr - 1);
        }
    }

    public Pack nextPack(){
        if(this.ptr >= this.packs.size() - 1){
            return null;
        } else{
            return this.packs.get(this.ptr + 1);
        }
    }

    @Subscribe
    @SuppressWarnings("unused")
    public void onPackLoad(PackLoadedEvent e){
        this.packs.add(e.pack);
        this.repaint();
        this.revalidate();
    }

    @Override
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;

        Pack pack = this.current();
        int y = 0;
        if(pack != null){
            // Draw Image
            BufferedImage background = pack.getImage();
            int x = ((this.getWidth() - background.getWidth()) / 2) - 20;
            y = ((this.getHeight() - background.getHeight()) / 2) - 80;
            g2.drawImage(background, x, y, 347, 182, null);

            // Draw Name
            g2.setFont(Resources.makeFont0("dinpro-black").deriveFont(32.0F));
            g2.setColor(Color.white);
            g2.drawString(pack.name, x + ((347 - g2.getFontMetrics().stringWidth(pack.name)) / 2), y + 182 + g2.getFontMetrics().getHeight() * 2);

            try{
                // Draw Version
                String version = pack.getLatest().version;
                g2.drawString(version, x + ((347 - g2.getFontMetrics().stringWidth(version)) / 2), y + 182 + g2.getFontMetrics().getHeight() * 3);
            } catch(Exception e){
                // Fallthrough
            }
        }

        pack = this.lastPack();
        if(pack != null){
            // Draw Last Pack Image
            Image image = pack.getImage();
            g2.drawImage(pack.getImage(), 0 - image.getWidth(null) / 2 - 50, y, 347, 182, null);
        }

        pack = this.nextPack();
        if(pack != null){
            // Draw Next Pack Image
            Image image = pack.getImage();
            g2.drawImage(image, this.getWidth() - image.getWidth(null) / 2, y, 347, 182, null);
        }
    }

    public void next(){
        if(this.ptr++ >= this.packs.size() - 1){
            this.ptr = this.packs.size() - 1;
        }
    }

    public void back(){
        if(this.ptr-- <= 0){
            this.ptr = 0;
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e){
        int notches = e.getUnitsToScroll();

        if(notches < 0){
            this.back();
        } else{
            this.next();
        }

        this.repaint();
    }
}