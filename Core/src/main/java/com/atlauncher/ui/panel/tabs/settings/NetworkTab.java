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
package com.atlauncher.ui.panel.tabs.settings;

import com.atlauncher.Settings;
import com.atlauncher.obj.Server;
import com.atlauncher.plaf.UIUtils;
import com.atlauncher.ui.comp.Card;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NetworkTab
extends JPanel
implements Card{
    private final GridBagConstraints gbc = new GridBagConstraints();
    private final JComboBox<Server> serverCBox = new JComboBox<>();

    public NetworkTab(){
        this.setLayout(new GridBagLayout());
        this.setBackground(UIUtils.GRAY);

        for(Server server : Settings.SERVERS){
            if(server.selectable){
                this.serverCBox.addItem(server);
            }
        }

        this.serverCBox.setSelectedItem(Settings.server);

        this.gbc.gridx = 0;
        this.gbc.gridy = 0;
        this.gbc.insets.set(5, 5, 5, 5);
        this.gbc.anchor = GridBagConstraints.LINE_END;
        this.add(new JLabel("Download Server: "), this.gbc);

        this.gbc.anchor = GridBagConstraints.CENTER;
        this.gbc.fill = GridBagConstraints.BOTH;
        this.gbc.gridy = 0;
        this.gbc.gridx = 2;

        this.gbc.gridx = 1;
        this.gbc.gridy = 0;
        this.gbc.ipadx = 200;
        this.add(this.serverCBox, this.gbc);

        this.addItemListeners();
    }

    private void addItemListeners(){
        this.serverCBox.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e){
                Settings.server = (Server) e.getItem();
                Settings.properties.setProperty("selectedServer", ((Server) e.getItem()).name);
            }
        });
    }

    @Override
    public String id(){
        return "network";
    }
}
