package com.atlauncher.ui.panel;

import com.atlauncher.obj.Account;
import com.atlauncher.ui.comp.Card;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.JPanel;

public final class UserPanel
extends JPanel
implements Card{
    private final Account account;

    public UserPanel(Account account){
        this.account = account;
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
}