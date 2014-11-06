package com.atlauncher.ui.layer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.beans.PropertyChangeEvent;
import javax.swing.JComponent;
import javax.swing.JLayer;
import javax.swing.JTextField;
import javax.swing.plaf.LayerUI;

public final class FieldValidationLayer
extends LayerUI<JTextField>{
    private boolean show = false;

    public void setShow(boolean b){
        this.firePropertyChange("show", this.show, b);
    }

    @Override
    public void applyPropertyChange(PropertyChangeEvent pce, JLayer l) {
        if(pce.getPropertyName().equalsIgnoreCase("show")){
            this.show = (Boolean) pce.getNewValue();
            l.repaint();
        }
    }

    @Override
    public void paint(Graphics g, JComponent comp){
        super.paint(g, comp);
        if(this.show){
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int width = comp.getWidth();
            int height = comp.getHeight();
            int scale = 8;
            int pad = 4;
            int x = width - pad - scale;
            int y = (height - scale) / 2;
            g2.setColor(Color.red);
            g2.fillRect(x, y, scale + 1, scale + 1);
            g2.setColor(Color.white);
            g2.drawLine(x, y, x + scale, y + scale);
            g2.drawLine(x, y + scale, x + scale, y);
        }
    }
}