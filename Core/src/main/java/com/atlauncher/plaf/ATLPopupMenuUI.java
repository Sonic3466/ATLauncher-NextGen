package com.atlauncher.plaf;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicPopupMenuUI;

public final class ATLPopupMenuUI
extends BasicPopupMenuUI{
    public static ComponentUI createUI(JComponent comp){
        return new ATLPopupMenuUI();
    }

    @Override
    public void paint(Graphics g, JComponent comp){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.white);
        g2.fillRect(0, 0, this.popupMenu.getWidth(), this.popupMenu.getHeight());
    }
}