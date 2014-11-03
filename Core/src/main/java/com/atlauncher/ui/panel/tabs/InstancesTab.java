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
package com.atlauncher.ui.panel.tabs;

import com.atlauncher.ui.comp.Card;
import com.atlauncher.ui.panel.CardDisplayPanel;
import com.atlauncher.ui.panel.CenterBottomPanel;
import com.atlauncher.ui.panel.PacksBottomPanel;

import java.awt.BorderLayout;
import javax.swing.JPanel;

public final class InstancesTab
extends JPanel
implements Card{

    public InstancesTab(){
        super(new BorderLayout());
        this.setOpaque(false);
        JPanel bottom = new JPanel(new BorderLayout());
        bottom.add(new CenterBottomPanel(), BorderLayout.NORTH);
        PacksBottomPanel bottomPanel = new PacksBottomPanel();
        bottom.add(bottomPanel, BorderLayout.CENTER);
        bottom.setOpaque(false);
        this.add(bottom, BorderLayout.SOUTH);
        CardDisplayPanel packDisplayPanel = new CardDisplayPanel();
        this.add(packDisplayPanel, BorderLayout.CENTER);
    }

    @Override
    public String id(){
        return "instances";
    }
}