package com.atlauncher.ui.comp;

import com.atlauncher.ATLauncher;
import com.atlauncher.Resources;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public final class MinimizeButton
extends JButton{
    public MinimizeButton(){
        super(new ImageIcon(Resources.makeImage("icons/button_minimize")));
        this.setContentAreaFilled(false);
        this.setBorder(null);
        this.setOpaque(false);
        this.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                ATLauncher.getFrame().setState(1);
            }
        });
    }
}