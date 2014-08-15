package com.atlauncher.plaf;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicButtonUI;

public final class ATLButtonUI
extends BasicButtonUI{
    protected static final Color PRESSED = new Color(45, 150, 190);
    protected static final Color UNPRESSED = new Color(55, 55, 55);
    protected static final Color TEXT = Color.WHITE;
    protected static final ATLButtonUI buttonUI = new ATLButtonUI();

    public static ComponentUI createUI(JComponent comp){
        return buttonUI;
    }

    private Rectangle viewRect = new Rectangle();
    private Rectangle iconRect = new Rectangle();
    private Rectangle textRect = new Rectangle();

    @Override
    public void installUI(JComponent comp){
        super.installUI(comp);

        if(comp instanceof JButton){
            JButton button = (JButton) comp;
            button.setFocusPainted(false);
            button.setBorderPainted(false);
            button.setForeground(TEXT);
            button.setBackground(UNPRESSED);
        }
    }

    @Override
    public void paint(Graphics g, JComponent c){
        AbstractButton b = (AbstractButton) c;
        Graphics2D g2 = (Graphics2D) g;
        if(b.getModel().isPressed()){
            if(b.isOpaque()){
                g2.setPaint(PRESSED);
                g2.fillRect(0, 0, b.getWidth(), b.getHeight());
            }
        } else{
            if(b.isOpaque()){
                g2.setColor(b.getBackground());
                g2.fillRect(0, 0, b.getWidth(), b.getHeight());
            }
        }

        String text = this.layout(b, g2.getFontMetrics(), b.getWidth(), b.getHeight());
        this.paintIcon(g, c, this.iconRect);
        this.paintText(g, c, this.textRect, text);
    }

    @Override
    protected void paintText(Graphics g, JComponent comp, Rectangle rect, String text){
        Graphics2D g2 = (Graphics2D) g;
        AbstractButton b = (AbstractButton) comp;

        g2.setColor(b.getModel().isPressed() ? Color.black : Color.white);
        g2.setFont(comp.getFont());
        UIUtils.antialiasOn(g2);
        g2.drawString(text, rect.x, rect.y + g2.getFontMetrics().getAscent());
        UIUtils.antialiasOff(g2);
    }

    private AlphaComposite alpha(float alpha){
        return AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
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