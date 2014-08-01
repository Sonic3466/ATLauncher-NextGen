package com.atlauncher.ui.comp;

import com.atlauncher.Resources;

import java.awt.Cursor;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public final class ExitButton
extends JButton{
    public ExitButton(){
        super(new ImageIcon(Resources.makeImage("icons/button_close")));
        this.setContentAreaFilled(false);
        this.setBorder(null);
        this.setMargin(new Insets(0, 0, 0, 0));
        this.setBorderPainted(false);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.setVerticalAlignment(SwingConstants.TOP);
        this.setFocusPainted(false);
        this.setOpaque(false);
        this.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
    }
}