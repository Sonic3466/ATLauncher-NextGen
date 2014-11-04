/*
 * ATLauncher-NextGen - https://github.com/ATLauncher/ATLauncher-NextGen
 * Copyright (C) 2013 ATLauncher
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.atlauncher.ui.comp;

import com.atlauncher.Resources;

import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.JButton;

public enum SocialMedia{
    GITHUB("https://github.com/ATLauncher/ATLauncher-NextGen"),
    CREEPERHOST("http://billing.creeperhost.net/link.php?id=7"),
    FACEBOOK("http://www.facebook.com/ATLauncher"),
    TWITTER("http://www.twitter.com/ATLauncher"),
    REDDIT("http://www.reddit.com/r/ATLauncher");

    private final String url;

    private SocialMedia(String url){
        this.url = url;
    }

    public JButton button(){
        JButton button =  new SocialMediaButton(Resources.makeImage("icons/sm/" + this.name().toLowerCase(), 16));
        button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try{
                    Desktop.getDesktop().browse(new URL(url).toURI());
                } catch(Exception ex){
                    ex.printStackTrace(System.err);
                }
            }
        });
        return button;
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