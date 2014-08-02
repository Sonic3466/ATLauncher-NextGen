package com.atlauncher.ui.comp;

import com.atlauncher.ATLauncher;
import com.atlauncher.Resources;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class MinimizeButton
extends IconicButton{
    public MinimizeButton(){
        super(Resources.makeImage("icons/button_minimize"));
        this.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                ATLauncher.getFrame().setState(1);
            }
        });
    }
}