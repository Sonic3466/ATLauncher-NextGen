package com.atlauncher.ui.comp;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class IconicButton
extends JButton{
    public IconicButton(Image image){
        super(new ImageIcon(image));
        this.setContentAreaFilled(false);
        this.setBorder(BorderFactory.createEmptyBorder(0, 12, 0, 0));
        this.setMargin(new Insets(0, 0, 0, 0));
        this.setBorderPainted(false);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.setVerticalAlignment(SwingConstants.TOP);
        this.setFocusPainted(false);
        this.setOpaque(false);
    }
}
