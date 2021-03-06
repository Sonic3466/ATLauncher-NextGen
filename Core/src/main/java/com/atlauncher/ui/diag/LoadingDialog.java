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
package com.atlauncher.ui.diag;

import com.atlauncher.plaf.UIUtils;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public final class LoadingDialog
extends JDialog{
    public final JLabel title = new JLabel("Loading");
    public final JProgressBar bar = new JProgressBar(0, 100);
    private final GridBagConstraints gbc = new GridBagConstraints();

    public LoadingDialog(String title){
        super(null, title, ModalityType.APPLICATION_MODAL);
        this.title.setText(title);
        this.setLayout(new GridBagLayout());
        this.setUndecorated(true);
        this.getContentPane().setBackground(new Color(40, 45, 50));
        this.title.setForeground(Color.white);
        this.title.setFont(this.title.getFont().deriveFont(18.0F));
        this.gbc.gridx = 0;
        this.gbc.gridy = 0;
        this.gbc.insets.set(5, 5, 5, 5);
        this.gbc.anchor = GridBagConstraints.LINE_START;
        this.gbc.fill = GridBagConstraints.BOTH;
        this.add(this.title, this.gbc);
        this.gbc.gridy++;
        this.add(this.bar, this.gbc);
        this.pack();
        this.setLocation((int) (UIUtils.SIZE.getWidth() - this.getWidth()) / 2, (int) (UIUtils.SIZE.getHeight() - this.getHeight()) / 2);
    }
}