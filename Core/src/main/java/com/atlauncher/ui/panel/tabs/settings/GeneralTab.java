package com.atlauncher.ui.panel.tabs.settings;

import com.atlauncher.Settings;
import com.atlauncher.ui.comp.Card;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public final class GeneralTab
extends JPanel
implements Card{
    private final GridBagConstraints gbc = new GridBagConstraints();
    private final JTextField downloadsField = new JTextField(16);
    private final JButton downloadsButton = new JButton("Change");

    public GeneralTab(){
        this.setLayout(new GridBagLayout());
        this.setOpaque(false);

        this.downloadsField.setEditable(false);

        this.gbc.gridx = 0;
        this.gbc.gridy = 0;
        this.gbc.insets.set(5, 5, 5, 5);
        this.gbc.anchor = GridBagConstraints.LINE_END;
        this.add(new JLabel("Downloads: "), this.gbc);

        this.gbc.anchor = GridBagConstraints.CENTER;
        this.gbc.fill = GridBagConstraints.BOTH;
        this.gbc.gridy = 0;
        this.gbc.gridx = 2;
        this.add(this.downloadsButton, this.gbc);

        this.gbc.gridx = 1;
        this.gbc.gridy = 0;
        this.gbc.ipadx = 200;
        this.add(this.downloadsField, this.gbc);

        this.addActionListeners();

        this.downloadsField.setText(Settings.downloads.toString());
    }

    private void addActionListeners(){
        this.downloadsButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                JFileChooser jfc = new JFileChooser();
                jfc.setDialogTitle("Choose Downloads Directory");
                int ret = jfc.showOpenDialog(GeneralTab.this);
                if(ret == JFileChooser.APPROVE_OPTION){
                    downloadsField.setText(jfc.getSelectedFile().toString());
                    Settings.downloads = jfc.getSelectedFile().toPath();
                }
            }
        });
    }

    @Override
    public String id(){
        return "general";
    }
}