package com.atlauncher.ui.panel;

import com.atlauncher.ui.comp.ExitButton;
import com.atlauncher.ui.comp.MinimizeButton;

import java.awt.FlowLayout;
import javax.swing.JPanel;

public final class TopPanel
extends JPanel{
    public TopPanel(){
        super(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        this.setOpaque(false);
        this.add(new MinimizeButton());
        this.add(new ExitButton());
    }
}