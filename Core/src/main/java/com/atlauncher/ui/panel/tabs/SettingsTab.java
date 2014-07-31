package com.atlauncher.ui.panel.tabs;

import com.atlauncher.ui.comp.Card;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public final class SettingsTab
extends JPanel
implements Card{
    private final JTabbedPane tabs = new JTabbedPane();
    {
        this.tabs.setOpaque(false);
        this.tabs.setBorder(null);
        this.tabs.addTab("Hello World", new JPanel());
        this.tabs.addTab("Test", new JPanel());
    }

    public SettingsTab(){
        super(new BorderLayout());
        this.setOpaque(false);
        this.add(this.tabs, BorderLayout.CENTER);
    }

    @Override
    public String id(){
        return "settings";
    }
}