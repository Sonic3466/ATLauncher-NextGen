/*
 * ATLauncher-NextGen - https://github.com/ATLauncher/ATLauncher-NextGen
 * Copyright (C) 2013 ATLauncher
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.atlauncher.ui.panel;

import com.atlauncher.obj.Account;
import com.atlauncher.plaf.UIUtils;
import com.atlauncher.ui.comp.Card;
import com.atlauncher.ui.diag.LoginDialog;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

//TODO: Add improvements
public final class UserPanel
extends JPanel
implements Card, MouseListener{
    private final Account account;
    private final ContextMenu menu = new ContextMenu();

    public UserPanel(Account account){
        this.account = account;
        this.addMouseListener(this);
        this.setBackground(UIUtils.GRAY);
    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        Image skin = this.account.getBody();
        int x = ((this.getWidth() - skin.getWidth(null)) / 2);
        int y = ((this.getHeight() - skin.getHeight(null)) / 2);
        g2.drawImage(skin, x, y, skin.getWidth(null), skin.getHeight(null), null);
        g2.setColor(Color.WHITE);
        g2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 32));
        int sX = (this.getWidth() - g2.getFontMetrics().stringWidth(this.account.name)) / 2;
        g2.drawString(this.account.name, sX, y - g2.getFontMetrics().getHeight() - 5);
    }

    @Override
    public String id(){
        return this.account.name;
    }

    @Override
    public void mouseClicked(MouseEvent e){
        if(e.getButton() == MouseEvent.BUTTON3){
            if(this.account != Account.DEFAULT){
                this.menu.show(this, e.getX(), e.getY());
            }
        } else if(e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() >= 2){
            try{
                SwingUtilities.invokeLater(new Runnable(){
                    @Override
                    public void run(){
                        new LoginDialog(account.username).setVisible(true);
                    }
                });
            } catch(Exception ex){
                ex.printStackTrace(System.err);
            }
        }
    }

    @Override public void mousePressed(MouseEvent e){}
    @Override public void mouseReleased(MouseEvent e){}
    @Override public void mouseEntered(MouseEvent e){}
    @Override public void mouseExited(MouseEvent e){}

    private final class ContextMenu
    extends JPopupMenu{
        private final JMenuItem reloadItem = new JMenuItem("Reload Skin");
        private final JMenuItem loginAsItem = new JMenuItem("Login As");

        private ContextMenu(){
            this.reloadItem.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    UserPanel.this.account.updateSkin();
                }
            });
            this.loginAsItem.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    try{
                        SwingUtilities.invokeLater(new Runnable(){
                            @Override
                            public void run(){
                                new LoginDialog(account.username).setVisible(true);
                            }
                        });
                    } catch(Exception ex){
                        ex.printStackTrace(System.err);
                    }
                }
            });

            this.add(this.reloadItem);
            this.add(this.loginAsItem);
        }
    }
}