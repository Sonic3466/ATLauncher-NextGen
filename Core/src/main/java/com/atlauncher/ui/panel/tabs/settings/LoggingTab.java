package com.atlauncher.ui.panel.tabs.settings;

import com.atlauncher.plaf.UIUtils;
import com.atlauncher.ui.comp.Card;

import javax.swing.JPanel;

public final class LoggingTab
extends JPanel
implements Card{
    public LoggingTab(){
        this.setBackground(UIUtils.GRAY);
    }

    @Override
    public String id(){
        return "logging";
    }
}