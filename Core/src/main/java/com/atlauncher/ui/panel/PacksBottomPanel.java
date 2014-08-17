package com.atlauncher.ui.panel;

import com.atlauncher.ATLauncher;
import com.atlauncher.event.CurrentPackInstallEvent;
import com.atlauncher.event.CurrentPackSupportEvent;
import com.atlauncher.event.CurrentPackWebsiteEvent;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public final class PacksBottomPanel
extends JPanel{
    private final JButton newInstanceButton = new JButton("New Instance");
    private final JButton newServerButton = new JButton("New Server");
    private final JButton supportButton = new JButton("Support");
    private final JButton websiteButton = new JButton("Website");

    public PacksBottomPanel(){
        super(new GridBagLayout());
        this.setOpaque(false);

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        c.insets.set(0, 0, 0, 3);
        this.add(this.newInstanceButton, c);
        c.gridx++;
        this.add(this.newServerButton, c);
        c.gridx++;
        this.add(this.supportButton, c);
        c.gridx++;
        c.insets.set(0, 0, 0, 0);
        this.add(this.websiteButton, c);

        this.addActionListeners();
    }

    private void addActionListeners(){
        this.supportButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                ATLauncher.EVENT_BUS.post(new CurrentPackSupportEvent());
            }
        });
        this.websiteButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                ATLauncher.EVENT_BUS.post(new CurrentPackWebsiteEvent());
            }
        });
        this.newInstanceButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                ATLauncher.EVENT_BUS.post(new CurrentPackInstallEvent());
            }
        });
    }
}