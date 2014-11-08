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
package com.atlauncher.ui.diag;

import com.atlauncher.ATLauncher;
import com.atlauncher.Settings;
import com.atlauncher.plaf.UIUtils;
import com.atlauncher.ui.frame.ATLauncherFrame;
import com.atlauncher.ui.layer.FieldValidationLayer;
import com.atlauncher.ui.panel.DialogBackPanel;
import com.atlauncher.utils.Authentication;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JLayer;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public final class LoginDialog
extends JDialog{
    private final CenterPanel center_panel = new CenterPanel();

    public LoginDialog(){
        this(Settings.properties.getProperty("lastAccount", ""));
    }

    public LoginDialog(String username){
        super(ATLauncher.getFrame(), username, ModalityType.APPLICATION_MODAL);
        this.setUndecorated(true);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setContentPane(new DialogBackPanel());
        this.getContentPane().add(new TopPanel(), BorderLayout.NORTH);
        this.getContentPane().add(new BottomPanel(), BorderLayout.SOUTH);
        this.getContentPane().add(this.center_panel, BorderLayout.CENTER);
        this.center_panel.uField.setText(username);
        this.center_panel.pField.requestFocus();
        this.pack();
        ATLauncherFrame parent = ATLauncher.getFrame();
        parent.blur();
        this.setSize(new Dimension(parent.getWidth() / 2, parent.getHeight() / 2));
        this.setLocation(parent.getX() + ((parent.getWidth() - this.getWidth()) / 2), parent.getY() + ((parent.getHeight() - this.getHeight()) / 2));
    }

    @Override
    public void dispose(){
        ATLauncher.getFrame().clear();
        super.dispose();
    }

    private static final class TopPanel
    extends JPanel{
        public TopPanel(){
            super(new FlowLayout(FlowLayout.CENTER));
            this.setOpaque(false);
            JLabel label = new JLabel("Login To Minecraft");
            label.setForeground(Color.white);
            label.setFont(UIUtils.DINPRO_BLACK.deriveFont(24.0F));
            this.add(label);
        }
    }

    private final class CenterPanel
    extends JPanel{
        private final JTextField uField = new JTextField(16);
        private final JPasswordField pField = new JPasswordField(16);
        private final FieldValidationLayer fieldValidityLayer = new FieldValidationLayer();
        private final JLayer<JTextField> pFieldWrapper = new JLayer<>(pField, fieldValidityLayer);

        public CenterPanel(){
            super(new GridBagLayout());
            this.setOpaque(false);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 0.0;
            gbc.fill = GridBagConstraints.NONE;
            gbc.insets.set(2, 2, 2, 2);
            gbc.anchor = GridBagConstraints.LINE_START;
            this.add(new JLabel("Username/Email: "), gbc);
            gbc.gridy++;
            this.add(new JLabel("Password: "), gbc);

            gbc.gridy = 0;
            gbc.gridx++;
            gbc.weightx = 1.0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.anchor = GridBagConstraints.CENTER;
            this.add(this.uField, gbc);
            gbc.gridy++;
            this.add(pFieldWrapper, gbc);

            this.pField.addKeyListener(new KeyAdapter(){
                @Override
                public void keyPressed(KeyEvent e){
                    if(e.getKeyCode() == KeyEvent.VK_ENTER){
                        try{
                            Authentication.create(center_panel.uField.getText(), new String(center_panel.pField.getPassword()));
                            dispose();
                        } catch(Exception e1){
                            ATLauncher.LOGGER.error("Error Logging In", e1);
                            fieldValidityLayer.setShow(true);
                        }
                    }
                }
            });
        }
    }

    private final class BottomPanel
    extends JPanel{
        private final JButton loginButton = new JButton("Login");
        private final JButton cancelButton = new JButton("Cancel");

        public BottomPanel(){
            super(new FlowLayout(FlowLayout.CENTER));
            this.setOpaque(false);
            this.loginButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    try{
                        Authentication.create(center_panel.uField.getText(), new String(center_panel.pField.getPassword()));
                        dispose();
                    } catch(Exception e1){
                        ATLauncher.LOGGER.error(e1);
                        center_panel.fieldValidityLayer.setShow(true);
                    }
                }
            });
            this.cancelButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    dispose();
                }
            });
            this.add(this.cancelButton);
            this.add(this.loginButton);
        }
    }
}