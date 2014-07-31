package com.atlauncher.ui.comp;

import com.atlauncher.Resources;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public final class ExitButton
extends JButton{
    public ExitButton(){
        super(new ImageIcon(Resources.makeImage("icons/button_close")));
        this.setContentAreaFilled(false);
        this.setBorder(null);
        this.setOpaque(false);
        this.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
    }
}