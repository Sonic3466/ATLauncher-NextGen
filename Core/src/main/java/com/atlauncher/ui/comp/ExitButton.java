package com.atlauncher.ui.comp;

import java.awt.Color;
import javax.swing.JButton;

public final class ExitButton
extends JButton{
    public ExitButton(){
        super("X");
        this.setForeground(Color.WHITE);
        this.setBackground(Color.RED);
    }
}