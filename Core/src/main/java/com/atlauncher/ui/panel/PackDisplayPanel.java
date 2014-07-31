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

public final class PackDisplayPanel
extends JPanel{
    private final List<Card> cards = new LinkedList<>();
    private int ptr = 0;

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

    public PackDisplayPanel(){
        super(new BorderLayout());
        this.setOpaque(false);

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
        this.add(this.displayPanel, BorderLayout.CENTER);
        this.add(this.nextButton, BorderLayout.EAST);
    }

    private void next(){
        if(this.ptr++ >= this.cards.size() - 1){
            this.ptr = this.cards.size() - 1;
        }

        CardLayout layout = (CardLayout) this.displayPanel.getLayout();
        layout.show(this.displayPanel, this.cards.get(this.ptr).id());
    }

    private void back(){
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