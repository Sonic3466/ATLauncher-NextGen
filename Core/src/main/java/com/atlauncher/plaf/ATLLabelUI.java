package com.atlauncher.plaf;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicLabelUI;

public final class ATLLabelUI
extends BasicLabelUI{
    private static final ATLLabelUI instance = new ATLLabelUI();

    public static ComponentUI createUI(JComponent comp){
        return instance;
    }

    @Override
    protected void paintEnabledText(JLabel label, Graphics g, String s,int x, int y){
        Graphics2D g2 = (Graphics2D) g;
        UIUtils.antialiasOn(g2);
        super.paintEnabledText(label, g, s, x, y);
        UIUtils.antialiasOff(g2);
    }
}