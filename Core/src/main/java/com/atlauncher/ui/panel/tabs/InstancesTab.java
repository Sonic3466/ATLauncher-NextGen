package com.atlauncher.ui.panel.tabs;

import com.atlauncher.ui.comp.Card;

import javax.swing.JPanel;

public final class InstancesTab
extends JPanel
implements Card{
    public InstancesTab(){
        this.setOpaque(false);
    }

    @Override
    public String id(){
        return "instances";
    }
}