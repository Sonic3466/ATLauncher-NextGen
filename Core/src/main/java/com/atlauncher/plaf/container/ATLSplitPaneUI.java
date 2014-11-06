package com.atlauncher.plaf.container;

import com.atlauncher.plaf.UIUtils;

import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;

public class ATLSplitPaneUI
extends BasicSplitPaneUI{
    public static ATLSplitPaneUI createUI(JComponent comp){
        return new ATLSplitPaneUI();
    }

    @Override
    public void installUI(JComponent comp){
        super.installUI(comp);
        comp.setBorder(BorderFactory.createEmptyBorder());
    }

    @Override
    public BasicSplitPaneDivider createDefaultDivider(){
        return new ATLSplitPaneDivider(this);
    }

    private static final class ATLSplitPaneDivider
    extends BasicSplitPaneDivider{
        public ATLSplitPaneDivider(BasicSplitPaneUI ui){
            super(ui);
        }

        @Override
        public void paint(Graphics g){
            g.setColor(UIUtils.GRAY);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
        }
    }
}