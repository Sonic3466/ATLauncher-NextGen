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

import com.atlauncher.Resources;
import com.atlauncher.ui.comp.Card;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class CardDisplayPanel
extends JPanel{
    protected final List<Card> cards = new LinkedList<>();
    protected int ptr = 0;

    private final JButton nextButton = new JButton(new ImageIcon(Resources.makeImage("icons/arrow_right")));
    {
        this.nextButton.setContentAreaFilled(false);
        this.nextButton.setOpaque(false);
    }
    private final JButton backButton = new JButton(new ImageIcon(Resources.makeImage("icons/arrow_left")));
    {
        this.backButton.setContentAreaFilled(false);
        this.backButton.setOpaque(false);
    }
    private final JPanel displayPanel = new JPanel(new CardLayout());
    {
        this.displayPanel.setOpaque(false);
    }

    public CardDisplayPanel(){
        this(true);
    }

    public CardDisplayPanel(boolean buttons){
        super(new BorderLayout());
        this.setOpaque(false);

        if(buttons){
            this.nextButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    next();
                }
            });
            this.backButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    back();
                }
            });
            this.add(this.backButton, BorderLayout.WEST);
            this.add(this.nextButton, BorderLayout.EAST);
        }

        this.add(this.displayPanel, BorderLayout.CENTER);
    }

    public int max(){
        return this.cards.size();
    }

    public int curr(){
        return this.ptr;
    }

    public void unregisterAll(){
        this.displayPanel.removeAll();
        this.displayPanel.revalidate();
        this.cards.clear();
    }

    protected void next(){
        if(this.ptr++ >= this.cards.size() - 1){
            this.ptr = this.cards.size() - 1;
        }

        CardLayout layout = (CardLayout) this.displayPanel.getLayout();
        layout.show(this.displayPanel, this.cards.get(this.ptr).id());
    }

    protected void back(){
        if(this.ptr-- <= 0){
            this.ptr = 0;
        }

        CardLayout layout = (CardLayout) this.displayPanel.getLayout();
        layout.show(this.displayPanel, this.cards.get(this.ptr).id());
    }

    public <T extends JPanel & Card> void register(T card){
        this.cards.add(card);
        this.displayPanel.add(card, card.id());
    }
}