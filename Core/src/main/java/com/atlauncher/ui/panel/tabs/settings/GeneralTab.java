package com.atlauncher.ui.panel.tabs.settings;

import com.atlauncher.ui.comp.Card;

import javax.swing.JPanel;

public final class GeneralTab
extends JPanel
implements Card{
    public GeneralTab(){
        this.setOpaque(false);
    }

    @Override
    public String id(){
        return "general";
    }
}