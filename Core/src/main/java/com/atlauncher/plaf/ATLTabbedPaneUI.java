package com.atlauncher.plaf;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

public final class ATLTabbedPaneUI
extends BasicTabbedPaneUI{
    protected static final Color ACTIVE = new Color(45, 150, 190);
    protected static final Color NON_ACTIVE = Color.white;

    public static ComponentUI createUI(JComponent comp){
        return new ATLTabbedPaneUI();
    }

    @Override
    public void installUI(JComponent comp){
        super.installUI(comp);
        this.contentBorderInsets.set(0, 0, 0, 0);
    }

    @Override
    protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(isSelected ? ACTIVE : NON_ACTIVE);
        g2.fillRect(x, y, w, h);
    }

    @Override
    protected int calculateTabWidth(int tabPlacement, int tabIndex, FontMetrics metrics) {
        int w = super.calculateTabWidth(tabPlacement, tabIndex, metrics);
        int wid = metrics.charWidth('M');
        w += wid * 2;
        return w;
    }

    @Override
    protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected ) {

    }

    @Override
    protected void paintContentBorder(Graphics g, int tabPlacement, int selectedIndex) {

    }

    @Override
    protected int calculateTabHeight(int tabPlacement, int tabIndex, int fontHeight) {
        return 75;
    }

    @Override
    protected int calculateMaxTabHeight(int tabPlacement) {
        return 75;
    }

    @Override protected void paintFocusIndicator(Graphics g, int tabPlacement, Rectangle[] rects, int tabIndex, Rectangle iconRect, Rectangle textRect, boolean isSelected) {}
}