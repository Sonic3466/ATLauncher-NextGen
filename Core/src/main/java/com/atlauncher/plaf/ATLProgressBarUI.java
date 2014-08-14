package com.atlauncher.plaf;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicProgressBarUI;

public final class ATLProgressBarUI
extends BasicProgressBarUI{
    public static final Color ACTIVE = new Color(18, 255, 13);

    public static ComponentUI createUI(JComponent comp){
        return new ATLProgressBarUI();
    }

    @Override
    public void installUI(JComponent comp){
        super.installUI(comp);
        comp.setForeground(ACTIVE);
        comp.setBackground(Color.gray);
        comp.setBorder(BorderFactory.createEmptyBorder());
    }

    @Override
    public Dimension getPreferredSize(JComponent comp){
        return new Dimension(super.getPreferredSize(comp).width, 20);
    }
}