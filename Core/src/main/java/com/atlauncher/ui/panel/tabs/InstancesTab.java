package com.atlauncher.ui.panel.tabs;

import com.atlauncher.ui.comp.Card;
import com.atlauncher.ui.panel.PacksBottomPanel;
import com.atlauncher.ui.panel.CardDisplayPanel;
import com.atlauncher.ui.panel.CenterBottomPanel;

import java.awt.BorderLayout;
import javax.swing.JPanel;

public final class InstancesTab
extends JPanel
implements Card{
    private final PacksBottomPanel bottomPanel = new PacksBottomPanel();
    private final CardDisplayPanel packDisplayPanel = new CardDisplayPanel();

    private final JPanel bottom = new JPanel(new BorderLayout());
    {
        this.bottom.add(new CenterBottomPanel(), BorderLayout.NORTH);
        this.bottom.add(this.bottomPanel, BorderLayout.CENTER);
        this.bottom.setOpaque(false);
    }

    public InstancesTab(){
        super(new BorderLayout());
        this.setOpaque(false);

        this.add(this.bottom, BorderLayout.SOUTH);
        this.add(this.packDisplayPanel, BorderLayout.CENTER);
    }

    @Override
    public String id(){
        return "instances";
    }
}