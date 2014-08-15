package com.atlauncher.plaf;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicTextFieldUI;

public final class ATLTextFieldUI
extends BasicTextFieldUI{
    public static ComponentUI createUI(JComponent comp){
        return new ATLTextFieldUI();
    }

    @Override
    public void installUI(JComponent comp){
        super.installUI(comp);
        comp.setBorder(BorderFactory.createEmptyBorder());
    }
}