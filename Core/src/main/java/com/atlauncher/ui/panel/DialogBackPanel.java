package com.atlauncher.ui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public final class DialogBackPanel
extends JPanel{
    public DialogBackPanel(){
        super(new BorderLayout());
        this.setBackground(new Color(40, 45, 50));
        this.setBorder(BorderFactory.createLineBorder(Color.black, 5));
    }
}
