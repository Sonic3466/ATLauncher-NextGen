package com.atlauncher.ui.panel;

import com.atlauncher.ATLauncher;
import com.atlauncher.event.UpdateAccountsEvent;
import com.atlauncher.obj.Account;
import com.atlauncher.plaf.UIUtils;
import com.atlauncher.ui.diag.LoginDialog;

import com.google.common.eventbus.Subscribe;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
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
    private static final int scale = 64;
    private boolean hovering = false;
    private Image head;
    private Account account;

    public HeadPanel(Account account){
        this.account = account;
        this.head = account.getHead();
        this.setPreferredSize(new Dimension(scale, scale));
        this.setToolTipText(account.name);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                if(e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() >= 1){
                    SwingUtilities.invokeLater(new Runnable(){
                        @Override
                        public void run(){
                            new LoginDialog().setVisible(true);
                        }
                    });
                }
            }

            @Override
            public void mouseEntered(MouseEvent e){
                hovering = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e){
                hovering = false;
                repaint();
            }
        });
        ATLauncher.EVENT_BUS.register(this);
        this.setToolTipText("Login to Minecraft.net");
    }

    @Subscribe
    public void updateHead(UpdateAccountsEvent event){
        this.account = event.account;
        this.head = this.account.getHead();
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(Color.white);
        g2.fillRect(0, 0, this.getWidth(), this.getHeight());
        int x = ((this.getWidth() - scale) / 2);
        int y = ((this.getHeight() - scale) / 2) - 15;
        g2.drawImage(this.head, x, y, scale, scale, null);
        g2.setColor(Color.black);
        g2.setStroke(new BasicStroke(2));
        Rectangle border = new Rectangle(x, y, scale, scale);
        g2.draw(border);
        int sX = (this.getWidth() - g2.getFontMetrics().stringWidth(this.account.name)) / 2;
        g2.drawString(this.account.name, sX, y + scale + 5 + g2.getFontMetrics().getHeight());

        if(this.hovering){
            g2.setComposite(UIUtils.alpha(0.50F));
            g2.setColor(new Color(45, 150, 190));
            g2.fillRect(x, y, scale, scale);
        }
    }
}