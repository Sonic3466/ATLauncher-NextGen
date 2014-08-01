package com.atlauncher.plaf;

import com.atlauncher.Resources;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;
import javax.swing.JSeparator;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicSeparatorUI;

public final class ATLSeparatorUI
extends BasicSeparatorUI{
    private static final BufferedImage VERTICAL = Resources.makeImage("icons/dash");
    private static final ATLSeparatorUI instance = new ATLSeparatorUI();

    public static ComponentUI createUI(JComponent comp){
        return instance;
    }

    @Override
    public void paint(Graphics g, JComponent comp){
        Graphics2D g2 = (Graphics2D) g;
        JSeparator separator = (JSeparator) comp;
        switch(separator.getOrientation())
        {
            case JSeparator.VERTICAL:{
                g2.drawImage(VERTICAL, 0, 0, VERTICAL.getWidth(), VERTICAL.getHeight(), null);
                break;
            }
            default:{
                break;
            }
        }
    }
}