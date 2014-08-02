package com.atlauncher.ui.comp;

import com.atlauncher.Resources;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.JButton;

public enum SocialMedia{
    GITHUB, CREEPERHOST, FACEBOOK, TWITTER, REDDIT;

    public JButton button(){
        return new SocialMediaButton(Resources.makeImage("icons/sm/" + this.name().toLowerCase(), 16));
    }

    private static final class SocialMediaButton
    extends IconicButton{
        public SocialMediaButton(Image image){
            super(image);
        }

        @Override
        public void paintComponent(Graphics g){
            Graphics2D g2 = (Graphics2D) g;
            int x = (this.getWidth() - 16) / 2;
            int y = (this.getHeight() - 16) / 2;
            this.getIcon().paintIcon(this, g2, x, y);
        }
    }
}