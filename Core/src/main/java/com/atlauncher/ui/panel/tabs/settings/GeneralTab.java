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

import com.atlauncher.ATLauncher;
import com.atlauncher.Language;
import com.atlauncher.Settings;
import com.atlauncher.event.ChangePackUIEvent;
import com.atlauncher.plaf.UIUtils;
import com.atlauncher.ui.PackUI;
import com.atlauncher.ui.comp.Card;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public final class GeneralTab
extends JPanel
implements Card{
    private final GridBagConstraints gbc = new GridBagConstraints();
    private final JTextField downloadsField = new JTextField(16);
    private final JButton downloadsButton = new JButton("Change");
    private final JComboBox<String> languageBox = new JComboBox<>(Language.available());
    private final JComboBox<PackUI> packUIBox = new JComboBox<>(PackUI.values());

    public GeneralTab(){
        this.setLayout(new GridBagLayout());
        this.setBackground(UIUtils.GRAY);

        this.downloadsField.setEditable(false);

        this.languageBox.setSelectedItem(Language.current());

        this.gbc.gridx = 0;
        this.gbc.gridy = 0;
        this.gbc.insets.set(5, 5, 5, 5);
        this.gbc.anchor = GridBagConstraints.LINE_END;
        this.add(new JLabel("Downloads: "), this.gbc);
        this.gbc.gridy++;
        this.add(new JLabel("Language: "), this.gbc);
        this.gbc.gridy++;
        this.add(new JLabel("Pack UI: "), this.gbc);

        this.gbc.anchor = GridBagConstraints.CENTER;
        this.gbc.fill = GridBagConstraints.BOTH;
        this.gbc.gridy = 0;
        this.gbc.gridx = 2;
        this.add(this.downloadsButton, this.gbc);

        this.gbc.gridx = 1;
        this.gbc.gridy = 0;
        this.gbc.ipadx = 200;
        this.add(this.downloadsField, this.gbc);
        this.gbc.gridy++;
        this.add(this.languageBox, this.gbc);
        this.gbc.gridy++;
        this.add(this.packUIBox, this.gbc);

        this.addActionListeners();
        this.addItemListeners();

        this.downloadsField.setText(Settings.downloads.toString());
    }

    private void addActionListeners(){
        this.downloadsButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                JFileChooser jfc = new JFileChooser();
                jfc.setDialogTitle("Choose Downloads Directory");
                jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int ret = jfc.showOpenDialog(GeneralTab.this);
                if(ret == JFileChooser.APPROVE_OPTION){
                    downloadsField.setText(jfc.getSelectedFile().toString());
                    Settings.downloads = jfc.getSelectedFile().toPath();
                    Settings.properties.setProperty("downloads", jfc.getSelectedFile().toString());
                }
            }
        });
    }

    private void addItemListeners(){
        this.languageBox.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e){
                try{
                    Language.INSTANCE.load((String) e.getItem());
                } catch(IOException e1){
                    throw new RuntimeException(e1);
                }
            }
        });
        this.packUIBox.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e){
                ATLauncher.EVENT_BUS.post(new ChangePackUIEvent((PackUI) e.getItem()));
            }
        });
    }

    @Override
    public String id(){
        return "general";
    }
}