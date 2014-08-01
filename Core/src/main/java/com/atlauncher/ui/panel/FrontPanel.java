package com.atlauncher.ui.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

//TODO: Tap into glass pane functionality and draw header on it
public class FrontPanel
extends JComponent{
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.white);
        g2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        g2.drawString("ATLauncher", 0, 0);
    }
}
