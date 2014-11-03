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
package com.atlauncher.plaf;

import com.atlauncher.Resources;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;
import javax.swing.JSeparator;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicSeparatorUI;

public final class ATLSeparatorUI
extends BasicSeparatorUI{
    private static final BufferedImage VERTICAL = Resources.makeImage("icons/dash");
    private static final ATLSeparatorUI instance = new ATLSeparatorUI();

    public static ComponentUI createUI(JComponent comp){
        return instance;
    }

    @Override
    public void paint(Graphics g, JComponent comp){
        Graphics2D g2 = (Graphics2D) g;
        JSeparator separator = (JSeparator) comp;
        switch(separator.getOrientation())
        {
            case JSeparator.VERTICAL:{
                g2.drawImage(VERTICAL, 0, 0, VERTICAL.getWidth(), VERTICAL.getHeight(), null);
                break;
            }
            default:{
                break;
            }
        }
    }
}