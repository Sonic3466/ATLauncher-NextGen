package com.atlauncher.ui.panel.tabs.settings;

import com.atlauncher.ui.comp.Card;

import javax.swing.JPanel;

public final class LoggingTab
extends JPanel
implements Card{
    public LoggingTab(){
        this.setOpaque(false);
    }

    @Override
    public String id(){
        return "logging";
    }
}