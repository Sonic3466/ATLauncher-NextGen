package com.atlauncher.ui;

import com.atlauncher.ATLauncher;
import com.atlauncher.ui.panel.pack.PackCardPane;
import com.atlauncher.ui.panel.pack.PackSliderPane;
import com.atlauncher.ui.panel.pack.PackTilePane;

import javax.swing.JPanel;

public enum PackUI{
    CARD(new PackCardPane()),
    TILE(new PackTilePane()),
    SLIDER(new PackSliderPane());

    private final JPanel panel;

    private PackUI(JPanel panel){
        this.panel = panel;
        ATLauncher.EVENT_BUS.register(panel);
    }

    public JPanel panel(){
        return this.panel;
    }
}