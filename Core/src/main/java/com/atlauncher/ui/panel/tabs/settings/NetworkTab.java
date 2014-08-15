package com.atlauncher.ui.panel.tabs.settings;

import com.atlauncher.ui.comp.Card;

import javax.swing.JPanel;

public class NetworkTab
extends JPanel
implements Card{
    public NetworkTab(){
        this.setOpaque(false);
    }

    @Override
    public String id(){
        return "network";
    }
}
