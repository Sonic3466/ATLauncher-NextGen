package com.atlauncher.ui.panel.tabs.settings;

import com.atlauncher.plaf.UIUtils;
import com.atlauncher.ui.comp.Card;

import javax.swing.JPanel;

public class NetworkTab
extends JPanel
implements Card{
    public NetworkTab(){
        this.setBackground(UIUtils.GRAY);
    }

    @Override
    public String id(){
        return "network";
    }
}
