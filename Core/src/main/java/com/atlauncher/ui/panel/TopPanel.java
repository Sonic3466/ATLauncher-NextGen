package com.atlauncher.ui.panel;

import com.atlauncher.ui.comp.ExitButton;
import com.atlauncher.ui.comp.MinimizeButton;

import java.awt.FlowLayout;
import javax.swing.JPanel;

public final class TopPanel
extends JPanel{
    public TopPanel(){
        super(new FlowLayout(FlowLayout.LEFT));
        this.setOpaque(false);
        this.add(new ExitButton());
        this.add(new MinimizeButton());
    }
}