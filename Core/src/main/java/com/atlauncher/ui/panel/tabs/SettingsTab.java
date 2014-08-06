package com.atlauncher.ui.panel.tabs;

import com.atlauncher.ui.comp.Card;

import java.awt.BorderLayout;
import javax.swing.JPanel;

public final class SettingsTab
extends JPanel
implements Card{
    public SettingsTab(){
        super(new BorderLayout());
        this.setOpaque(false);
    }

    @Override
    public String id(){
        return "settings";
    }
}