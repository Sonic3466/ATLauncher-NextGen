package com.atlauncher.plaf;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicComboBoxUI;

final class ATLComboBoxUI
extends BasicComboBoxUI{
    private static ATLComboBoxUI instance = new ATLComboBoxUI();

    public static ComponentUI createUI(JComponent comp){
        return instance;
    }

    @Override
    public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean focus){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.white);
        g2.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
    }
}
