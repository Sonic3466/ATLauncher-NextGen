package com.atlauncher.ui.diag;

import com.atlauncher.ATLauncher;
import com.atlauncher.Settings;
import com.atlauncher.utils.Authentication;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

//TODO: Optimize
public final class LoginDialog
extends JDialog{
    private final CenterPanel center_panel = new CenterPanel();

    public LoginDialog(){
        this(Settings.properties.getProperty("lastAccount", ""));
    }

    public LoginDialog(String username){
        super(ATLauncher.getFrame(), username, ModalityType.APPLICATION_MODAL);
        this.setUndecorated(true);
        this.setLocationRelativeTo(ATLauncher.getFrame());
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setContentPane(new BackPanel());
        this.getContentPane().add(new TopPanel(), BorderLayout.NORTH);
        this.getContentPane().add(new BottomPanel(), BorderLayout.SOUTH);
        this.getContentPane().add(this.center_panel, BorderLayout.CENTER);
        this.pack();
        this.center_panel.uField.setText(username);
        this.center_panel.pField.requestFocus();
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

    private static final class CenterPanel
    extends JPanel{
        private final JTextField uField = new JTextField(16);
        private final JPasswordField pField = new JPasswordField(16);

        public CenterPanel(){
            super();
            this.setOpaque(false);
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            JLabel uLabel = new JLabel("Username / Email: ", JLabel.LEFT);
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
                    try{
                        Authentication.create(center_panel.uField.getText(), new String(center_panel.pField.getPassword()));
                    } catch(Exception e1){
                        e1.printStackTrace();
                    }
                    dispose();
                }
            });
            this.cancelButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    LoginDialog.this.dispose();
                }
            });
            this.add(this.cancelButton);
            this.add(this.loginButton);
        }
    }
}