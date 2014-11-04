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
package com.atlauncher.ui.panel;

import com.atlauncher.plaf.UIUtils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

//TODO: Rewrite & Optimize
public final class BackPanel
extends JPanel{
    public BackPanel(){
        super(new BorderLayout());
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(UIUtils.GRAY);
        g2.fillRect(0, 0, this.getWidth() - 100, this.getHeight() - 26);
        g2.setColor(Color.white);
        g2.fillRect(this.getWidth() - 108, 0, 108, this.getHeight());
        g2.setColor(UIUtils.GRAY);
        g2.fillRect(0, this.getHeight() - 26, this.getWidth(), 26);
    }
}