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
import com.atlauncher.ui.frame.ATLauncherFrame;
import com.atlauncher.utils.Authentication;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
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
        this.setContentPane(new BackPanel());
        this.getContentPane().add(new TopPanel(), BorderLayout.NORTH);
        this.getContentPane().add(new BottomPanel(), BorderLayout.SOUTH);
        this.getContentPane().add(this.center_panel, BorderLayout.CENTER);
        this.center_panel.uField.setText(username);
        this.center_panel.pField.requestFocus();
        this.pack();
        ATLauncherFrame parent = ATLauncher.getFrame();
        parent.blur();
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
            super(new FlowLayout(FlowLayout.LEFT));
            this.setOpaque(false);
            JLabel label = new JLabel("Login To Minecraft");
            label.setForeground(Color.white);
            label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
            this.add(label);
        }
    }

    private static final class BackPanel
    extends JPanel{
        public BackPanel(){
            super(new BorderLayout());
            this.setBackground(new Color(40, 45, 50));
            this.setBorder(BorderFactory.createLineBorder(Color.black, 5));
        }
    }

    private final class CenterPanel
    extends JPanel{
        private final JTextField uField = new JTextField(16);
        private final JPasswordField pField = new JPasswordField(16);

        public CenterPanel(){
            super();
            this.setOpaque(false);
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            JLabel uLabel = new JLabel("Email: ", JLabel.LEFT);
            uLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            uLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            uLabel.setForeground(Color.white);
            this.add(uLabel);
            this.uField.setBorder(BorderFactory.createLineBorder(Color.black, 1));
            this.uField.setAlignmentX(Component.LEFT_ALIGNMENT);
            this.add(this.uField);
            JLabel pLabel = new JLabel("Password: ", JLabel.LEFT);
            pLabel.setForeground(Color.white);
            pLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            pLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            this.add(pLabel);
            this.pField.setBorder(BorderFactory.createLineBorder(Color.black, 1));
            this.pField.setAlignmentX(Component.LEFT_ALIGNMENT);
            this.pField.addKeyListener(new KeyAdapter(){
                @Override
                public void keyPressed(KeyEvent e){
                    if(e.getKeyCode() == KeyEvent.VK_ENTER){
                        dispose();
                        try{
                            Authentication.create(center_panel.uField.getText(), new String(center_panel.pField.getPassword()));
                        } catch(Exception e1){
                            e1.printStackTrace();
                        }
                    }
                }
            });
            this.add(this.pField);
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
                    dispose();
                    try{
                        Authentication.create(center_panel.uField.getText(), new String(center_panel.pField.getPassword()));
                    } catch(Exception e1){
                        e1.printStackTrace();
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