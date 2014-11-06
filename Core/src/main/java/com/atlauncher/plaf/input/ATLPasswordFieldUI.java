package com.atlauncher.plaf.input;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicPasswordFieldUI;

public class ATLPasswordFieldUI
extends BasicPasswordFieldUI{
    public static ComponentUI createUI(JComponent comp){
        return new ATLPasswordFieldUI();
    }

    @Override
    public void installUI(JComponent comp){
        super.installUI(comp);
        comp.setBorder(BorderFactory.createLineBorder(Color.black, 1));
    }
}