package com.atlauncher.ui.panel.tabs;

import com.atlauncher.ui.comp.Card;
import com.atlauncher.ui.panel.BottomPanel;
import com.atlauncher.ui.panel.CenterBottomPanel;
import com.atlauncher.ui.panel.PackDisplayPanel;

import java.awt.BorderLayout;
import javax.swing.JPanel;

public final class PacksTab
extends JPanel
implements Card{
    private final BottomPanel bottomPanel = new BottomPanel();
    private final PackDisplayPanel packDisplayPanel = new PackDisplayPanel();

    private final JPanel bottom = new JPanel(new BorderLayout());
    {
        this.bottom.add(new CenterBottomPanel(), BorderLayout.NORTH);
        this.bottom.add(this.bottomPanel, BorderLayout.CENTER);
        this.bottom.setOpaque(false);
    }

    public PacksTab(){
        super(new BorderLayout());
        this.setOpaque(false);

        this.add(this.bottom, BorderLayout.SOUTH);
        this.add(this.packDisplayPanel, BorderLayout.CENTER);
    }

    @Override
    public String id(){
        return "modpacks";
    }
}