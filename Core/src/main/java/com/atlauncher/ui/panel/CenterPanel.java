package com.atlauncher.ui.panel;

import java.awt.BorderLayout;
import javax.swing.JPanel;

public final class CenterPanel
extends JPanel{
    private final BottomPanel bottom = new BottomPanel();
    private final PackDisplayPanel packDisplayPanel = new PackDisplayPanel();
    {
        this.packDisplayPanel.register(new PackPanel("beyondreality"));
        this.packDisplayPanel.register(new PackPanel("businesselite"));
    }

    public CenterPanel(){
        super(new BorderLayout());

        this.add(this.bottom, BorderLayout.SOUTH);
        this.add(this.packDisplayPanel, BorderLayout.CENTER);
    }
}