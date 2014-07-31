package com.atlauncher.ui.panel;

import java.awt.BorderLayout;
import javax.swing.JPanel;

public final class CenterPanel
extends JPanel{
    private final BottomPanel bottomPanel = new BottomPanel();
    private final PackDisplayPanel packDisplayPanel = new PackDisplayPanel();
    {
        this.packDisplayPanel.register(new PackPanel("beyondreality"));
        this.packDisplayPanel.register(new PackPanel("businesselite"));
    }

    private final JPanel bottom = new JPanel(new BorderLayout());
    {
        this.bottom.add(new CenterBottomPanel(), BorderLayout.NORTH);
        this.bottom.add(this.bottomPanel, BorderLayout.CENTER);
        this.bottom.setOpaque(false);
    }

    public CenterPanel(){
        super(new BorderLayout());
        this.setOpaque(false);

        this.add(this.bottom, BorderLayout.SOUTH);
        this.add(this.packDisplayPanel, BorderLayout.CENTER);
    }
}