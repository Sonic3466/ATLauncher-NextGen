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
