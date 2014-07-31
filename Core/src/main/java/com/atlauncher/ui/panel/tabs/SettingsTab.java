package com.atlauncher.ui.panel.tabs;

import com.atlauncher.ui.comp.Card;

import javax.swing.JPanel;

public final class SettingsTab
extends JPanel
implements Card{
    public SettingsTab(){
        this.setOpaque(false);
    }

    @Override
    public String id(){
        return "settings";
    }
}