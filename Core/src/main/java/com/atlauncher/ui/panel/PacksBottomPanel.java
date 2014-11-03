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

import com.atlauncher.ATLauncher;
import com.atlauncher.event.PackEvent;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public final class PacksBottomPanel
extends JPanel{
    private final JButton newInstanceButton = new JButton("New Instance");
    private final JButton newServerButton = new JButton("New Server");
    private final JButton supportButton = new JButton("Support");
    private final JButton websiteButton = new JButton("Website");

    public PacksBottomPanel(){
        super(new GridBagLayout());
        this.setOpaque(false);

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        c.insets.set(0, 0, 0, 3);
        this.add(this.newInstanceButton, c);
        c.gridx++;
        this.add(this.newServerButton, c);
        c.gridx++;
        this.add(this.supportButton, c);
        c.gridx++;
        c.insets.set(0, 0, 0, 0);
        this.add(this.websiteButton, c);

        this.addActionListeners();
    }

    private void addActionListeners(){
        this.supportButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                ATLauncher.EVENT_BUS.post(new PackEvent(PackEvent.OPEN_SUPPORT));
            }
        });
        this.websiteButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                ATLauncher.EVENT_BUS.post(new PackEvent(PackEvent.OPEN_WEBSITE));
            }
        });
        this.newInstanceButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                ATLauncher.EVENT_BUS.post(new PackEvent(PackEvent.INSTALL_CLIENT));
            }
        });
        this.newServerButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                ATLauncher.EVENT_BUS.post(new PackEvent(PackEvent.INSTALL_SERVER));
            }
        });
    }
}