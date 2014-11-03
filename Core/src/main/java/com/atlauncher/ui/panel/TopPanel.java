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

import com.atlauncher.ui.comp.ExitButton;
import com.atlauncher.ui.comp.MinimizeButton;
import com.atlauncher.ui.comp.SocialMedia;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public final class TopPanel
extends JPanel{
    private final GridBagConstraints gbc = new GridBagConstraints();
    private final JLabel header =  new JLabel("ATLauncher - News", JLabel.LEFT);

    public TopPanel(){
        super(new GridBagLayout());
        this.setOpaque(false);

        this.header.setFont(this.header.getFont().deriveFont(24.0F));
        this.header.setBorder(BorderFactory.createEmptyBorder(15, 15, 5, 5));

        this.gbc.gridx = 0;
        this.gbc.gridy = 0;
        this.gbc.weightx = 0.1;
        this.gbc.weighty = 0.1;
        this.gbc.anchor = GridBagConstraints.LINE_START;
        this.add(this.header, this.gbc);
        this.gbc.anchor = GridBagConstraints.FIRST_LINE_END;
        this.gbc.gridx++;
        this.add(Box.createRigidArea(new Dimension(410, 0)), this.gbc);
        this.gbc.gridx++;
        this.add(new MinimizeButton(), this.gbc);
        this.gbc.gridx++;
        this.add(new ExitButton(), this.gbc);

        this.gbc.gridx = 0;
        this.gbc.fill = GridBagConstraints.VERTICAL;
        this.gbc.gridy++;
        this.gbc.anchor = GridBagConstraints.LINE_START;
        this.add(new BottomPanel(), this.gbc);
    }

    private final class BottomPanel
    extends JPanel{
        public BottomPanel(){
            super(new FlowLayout(FlowLayout.LEFT));
            this.setOpaque(false);
            for(SocialMedia sm : SocialMedia.values()){
                JButton button = sm.button();
                button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 5));
                this.add(button);
            }
        }
    }
}