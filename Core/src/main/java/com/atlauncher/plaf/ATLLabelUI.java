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

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicLabelUI;

public final class ATLLabelUI
extends BasicLabelUI{
    private static final ATLLabelUI instance = new ATLLabelUI();

    public static ComponentUI createUI(JComponent comp){
        return instance;
    }

    @Override
    public void installUI(JComponent comp){
        super.installUI(comp);
        comp.setForeground(Color.white);
    }

    @Override
    protected void paintEnabledText(JLabel label, Graphics g, String s,int x, int y){
        Graphics2D g2 = (Graphics2D) g;
        UIUtils.antialiasOn(g2);
        super.paintEnabledText(label, g, s, x, y);
        UIUtils.antialiasOff(g2);
    }
}