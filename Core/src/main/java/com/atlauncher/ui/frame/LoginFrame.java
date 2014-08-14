package com.atlauncher.ui.frame;

import com.atlauncher.ATLauncher;
import com.atlauncher.Accounts;
import com.atlauncher.Settings;
import com.atlauncher.event.UpdateAccountsEvent;
import com.atlauncher.obj.Account;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.text.Document;

public final class LoginFrame
extends DraggableFrame{
    private final CenterPanel center_panel = new CenterPanel();

    public LoginFrame(){
        this(Settings.properties.getProperty("lastAccount", ""));
    }

    public LoginFrame(String username){
        super("Login To Minecraft");
        this.setLocationRelativeTo(ATLauncher.getFrame());
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setContentPane(new BackPanel());
        this.getContentPane().add(new TopPanel(), BorderLayout.NORTH);
        this.getContentPane().add(new BottomPanel(), BorderLayout.SOUTH);
        this.getContentPane().add(this.center_panel, BorderLayout.CENTER);
        this.pack();
        this.center_panel.uField.setText(username);
    }

    private final class TopPanel
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

    private final class BackPanel
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
            JLabel uLabel = new JLabel("Username / Email: ", JLabel.LEFT);
            uLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            uLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            uLabel.setForeground(Color.white);
            this.add(uLabel);
            this.uField.setBorder(BorderFactory.createLineBorder(Color.black, 1));
            this.uField.setAlignmentX(Component.LEFT_ALIGNMENT);
            this.uField.addKeyListener(new KeyAdapter(){
                @Override
                public void keyPressed(KeyEvent e){
                    if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
                        Document doc = uField.getDocument();
                        try{
                            if(doc.getLength() > 0){
                                doc.remove(doc.getLength() - 1, 1);
                            }
                        } catch(Exception ex){
                            ex.printStackTrace(System.err);
                        }
                    }
                }
            });
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
                    if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
                        Document doc = pField.getDocument();
                        try{
                            if(doc.getLength() > 0){
                                doc.remove(doc.getLength() - 1, 1);
                            }
                        } catch(Exception ex){
                            ex.printStackTrace(System.err);
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
                    ATLauncher.TASKS.execute(new Runnable(){
                        @Override
                        public void run(){
                            Account acc = Authentication.get(center_panel.uField.getText(), new String(center_panel.pField.getPassword()));

                            if(acc == null){
                                throw new RuntimeException("Cannot Login");
                            }

                            Accounts.instance.setCurrent(acc);
                            ATLauncher.EVENT_BUS.post(new UpdateAccountsEvent(acc));
                            Settings.properties.setProperty("lastAccount", center_panel.uField.getText());
                        }
                    });
                    dispose();
                }
            });
            this.cancelButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    LoginFrame.this.dispose();
                }
            });
            this.add(this.cancelButton);
            this.add(this.loginButton);
        }
    }
}