package com.atlauncher.ui.comp;

import com.atlauncher.Resources;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;

public final class ExitButton
extends IconicButton{
    public ExitButton(){
        super(Resources.makeImage("icons/button_close"));
        this.setBorder(BorderFactory.createEmptyBorder(0, 14, 0, 0));
        this.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
    }
}