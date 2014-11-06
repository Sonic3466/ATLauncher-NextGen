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
package com.atlauncher.plaf.output;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicProgressBarUI;

public final class ATLProgressBarUI
extends BasicProgressBarUI{
    public static final Color ACTIVE = new Color(18, 255, 13);

    public static ComponentUI createUI(JComponent comp){
        return new ATLProgressBarUI();
    }

    @Override
    public void installUI(JComponent comp){
        super.installUI(comp);
        comp.setForeground(ACTIVE);
        comp.setBackground(Color.gray);
        comp.setBorder(BorderFactory.createEmptyBorder());
    }

    @Override
    public Dimension getPreferredSize(JComponent comp){
        return new Dimension(super.getPreferredSize(comp).width, 20);
    }
}