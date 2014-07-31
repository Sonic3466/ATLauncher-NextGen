package com.atlauncher.ui.comp;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicButtonUI;

public final class TwoStateButton
extends JButton{
    public TwoStateButton(BufferedImage pressed, BufferedImage unpressed){
        super();
        this.setUI(new TwoStateButtonUI(unpressed, pressed));
    }

    private final class TwoStateButtonUI
    extends BasicButtonUI{
        private final BufferedImage unpressed;
        private final BufferedImage pressed;

        private TwoStateButtonUI(BufferedImage unpressed, BufferedImage pressed){
            this.unpressed = unpressed;
            this.pressed = pressed;
        }

        @Override
        public void installUI(JComponent comp){
            super.installUI(comp);

            if(comp instanceof JButton){
                JButton button = (JButton) comp;
                button.setFocusPainted(false);
                button.setBorderPainted(false);
            }
        }

        @Override
        public void paint(Graphics g, JComponent c){
            AbstractButton b = (AbstractButton) c;
            Graphics2D g2 = (Graphics2D) g;
            if(b.getModel().isPressed()){
                g2.drawImage(this.pressed, 0, 0, b.getWidth(), b.getHeight(), null);
            } else{
                g2.drawImage(this.unpressed, 0, 0, b.getWidth(), b.getHeight(), null);
            }
        }
    }
}