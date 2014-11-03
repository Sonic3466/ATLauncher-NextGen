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

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class IconicButton
extends JButton{
    public IconicButton(Image image){
        super(new ImageIcon(image));
        this.setContentAreaFilled(false);
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.setMargin(new Insets(0, 0, 0, 0));
        this.setBorderPainted(false);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.setVerticalAlignment(SwingConstants.TOP);
        this.setFocusPainted(false);
        this.setOpaque(false);
    }
}
