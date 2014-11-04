package com.atlauncher.ui.panel.tabs;

import com.atlauncher.ui.comp.Card;
import com.atlauncher.ui.panel.CardDisplayPanel;

import java.awt.BorderLayout;
import javax.swing.JPanel;

public final class InstancesTab
extends JPanel
implements Card{

    public InstancesTab(){
        super(new BorderLayout());
        this.setOpaque(false);
        JPanel bottom = new JPanel(new BorderLayout());
        bottom.setOpaque(false);
        this.add(bottom, BorderLayout.SOUTH);
        CardDisplayPanel packDisplayPanel = new CardDisplayPanel();
        this.add(packDisplayPanel, BorderLayout.CENTER);
    }

    @Override
    public String id(){
        return "instances";
    }
}