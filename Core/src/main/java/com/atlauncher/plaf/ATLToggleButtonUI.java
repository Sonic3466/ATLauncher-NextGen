package com.atlauncher.plaf;

import sun.swing.SwingUtilities2;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicToggleButtonUI;

public final class ATLToggleButtonUI
        extends BasicToggleButtonUI{
    protected static final Color HOVER = new Color(45, 150, 190);
    protected static final Color PRESSED = new Color(18, 255, 13);
    protected static final Color UNPRESSED = new Color(55, 55, 55);
    protected static final Color TEXT = new Color(140, 150, 165);

    protected static final ATLToggleButtonUI buttonUI = new ATLToggleButtonUI();

    public static ComponentUI createUI(JComponent comp){
        return buttonUI;
    }

    private Rectangle viewRect = new Rectangle();
    private Rectangle iconRect = new Rectangle();
    private Rectangle textRect = new Rectangle();

    @Override
    public void installUI(JComponent comp){
        super.installUI(comp);

        if(comp instanceof JToggleButton){
            JToggleButton b = (JToggleButton) comp;
            b.setBorderPainted(false);
            b.setFocusPainted(false);
            b.setRolloverEnabled(true);
            b.setForeground(TEXT);
            b.setBackground(UNPRESSED);
        }
    }

    @Override
    public void paint(Graphics g, JComponent c){
        AbstractButton b = (AbstractButton) c;
        Graphics2D g2 = (Graphics2D) g;
        if(b.getModel().isPressed() || b.getModel().isSelected()){
            g2.setColor(PRESSED);
            g2.fillRect(0, 0, b.getWidth(), b.getHeight());
        } else if(b.getModel().isRollover()){
            g2.setColor(HOVER);
            g2.fillRect(0, 0, b.getWidth(), b.getHeight());
        } else{
            g2.setColor(b.getBackground());
            g2.fillRect(0, 0, b.getWidth(), b.getHeight());
        }

        String text = this.layout(b, SwingUtilities2.getFontMetrics(b, g2), b.getWidth(), b.getHeight());

        this.paintIcon(g, c, this.iconRect);
        this.paintText(g, c, this.textRect, text);
    }

    @Override
    protected void paintText(Graphics g, JComponent c, Rectangle textRect, String text){
        Graphics2D g2 = (Graphics2D) g;
        AbstractButton b = (AbstractButton) c;
        if(b.getModel().isPressed() || b.getModel().isSelected()){
            g2.setColor(Color.WHITE);
        } else if(b.getModel().isRollover()){
            g2.setColor(Color.BLACK);
        } else{
            g2.setColor(b.getForeground());
        }

        UIUtils.antialiasOn(g);
        g2.drawString(text, textRect.x, textRect.y);
        UIUtils.antialiasOff(g);
    }

    private String layout(AbstractButton b, FontMetrics fm,
                          int width, int height) {
        Insets i = b.getInsets();
        viewRect.x = i.left;
        viewRect.y = i.top;
        viewRect.width = width - (i.right + viewRect.x);
        viewRect.height = height - (i.bottom + viewRect.y);

        textRect.x = textRect.y = textRect.width = textRect.height = 0;
        iconRect.x = iconRect.y = iconRect.width = iconRect.height = 0;

        // layout the text and icon
        return SwingUtilities.layoutCompoundLabel(
                b, fm, b.getText(), b.getIcon(),
                b.getVerticalAlignment(), b.getHorizontalAlignment(),
                b.getVerticalTextPosition(), b.getHorizontalTextPosition(),
                viewRect, iconRect, textRect,
                b.getText() == null ? 0 : b.getIconTextGap());
    }
}