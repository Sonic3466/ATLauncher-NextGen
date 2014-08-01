package com.atlauncher.ui.panel;

import com.atlauncher.obj.Account;
import com.atlauncher.ui.frame.LoginFrame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public final class HeadPanel
extends JPanel{
    private final Image head;
    private final Account account;
    private final int scale = 64;

    public HeadPanel(Account account){
        this.account = account;
        this.head = account.getHead();
        this.setPreferredSize(new Dimension(this.scale, this.scale));
        this.setToolTipText(account.name);
        this.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                if(e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() >= 1){
                    SwingUtilities.invokeLater(new Runnable(){
                        @Override
                        public void run(){
                            new LoginFrame().setVisible(true);
                        }
                    });
                }
            }
        });
    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        int x = ((this.getWidth() - this.scale) / 2);
        int y = ((this.getHeight() - this.scale) / 2) - 15;
        g2.drawImage(this.head, x, y, this.scale, this.scale, null);
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(2));
        Rectangle border = new Rectangle(x, y, this.scale, this.scale);
        g2.draw(border);
        g2.drawString(this.account.name, x - 5, y + scale + 5 + g2.getFontMetrics().getHeight());
    }
}