package com.atlauncher.ui.comp;

import com.atlauncher.Resources;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class ExitButton
extends IconicButton{
    public ExitButton(){
        super(Resources.makeImage("icons/button_close"));
        this.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
    }
}