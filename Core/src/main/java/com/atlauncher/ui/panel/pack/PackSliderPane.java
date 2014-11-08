package com.atlauncher.ui.panel.pack;

import com.atlauncher.Resources;
import com.atlauncher.event.PackLoadedEvent;
import com.atlauncher.event.PacksDoneLoadingEvent;
import com.atlauncher.obj.Pack;
import com.atlauncher.obj.Pack.Version;
import com.atlauncher.plaf.UIUtils;

import com.google.common.eventbus.Subscribe;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Stack based slider panel, similar to TechnicLauncher's UI
 */
public final class PackSliderPane
extends JPanel
implements MouseWheelListener{
    private final List<Pack> master = new LinkedList<>();
    private final List<Pack> packs = new LinkedList<>();
    private int ptr = 0;

    private final JButton installClientButton = new JButton("Install Client");
    private final JButton installServerButton = new JButton("Install Server");
    private final JButton websiteButton = new JButton("Website");
    private final JButton supportButton = new JButton("Support");
    private final JButton clearButton = new JButton("Clear");
    private final JComboBox<Version> versionBox = new JComboBox<>();

    private final JTextField searchField = new JTextField(16);

    public PackSliderPane(){
        super(new BorderLayout());
        this.addMouseWheelListener(this);
        this.addActionListeners();
        this.addKeyListeners();

        JPanel bottom = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.0;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets.set(2, 2, 2, 2);
        bottom.add(new JLabel("Search: ", SwingConstants.RIGHT), gbc);

        gbc.gridx++;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        bottom.add(this.searchField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets.set(0, 0, 0, 2);
        bottom.add(this.clearButton, gbc);
        gbc.gridx++;
        bottom.add(this.installClientButton, gbc);
        gbc.gridx++;
        bottom.add(this.installServerButton, gbc);
        gbc.gridx++;
        bottom.add(this.supportButton, gbc);
        gbc.gridx++;
        gbc.insets.set(0, 0, 0, 0);
        bottom.add(this.websiteButton, gbc);

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(this.versionBox);

        this.add(top, BorderLayout.NORTH);
        this.add(bottom, BorderLayout.SOUTH);
    }

    private void addKeyListeners(){
        this.searchField.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e){
                search();
            }
        });
    }

    private void search(){
        ptr = 0;
        packs.clear();
        repaint();
        boolean show;
        for(Pack pack : master){
            show = true;
            if(!this.searchField.getText().isEmpty()){
                if(!Pattern.compile(Pattern.quote(this.searchField.getText()), Pattern.CASE_INSENSITIVE).matcher(pack.name).find()){
                    show = false;
                }
            }

            if(show){
                this.packs.add(pack);
            }

            repaint();
        }
        this.versionBox.setModel(new DefaultComboBoxModel<>(current().versions));
    }

    private void addActionListeners(){

    }

    public Pack current(){
        if(this.packs.isEmpty()){
            return null;
        } else{
            return this.packs.get(this.ptr);
        }
    }

    public Pack lastPack(){
        if(this.ptr <= 0){
            return null;
        } else{
            return this.packs.get(this.ptr - 1);
        }
    }

    public Pack nextPack(){
        if(this.ptr >= this.packs.size() - 1){
            return null;
        } else{
            return this.packs.get(this.ptr + 1);
        }
    }

    @Subscribe
    @SuppressWarnings("unused")
    public void onPackLoad(PackLoadedEvent e){
        this.packs.add(e.pack);
        this.master.add(e.pack);
        this.repaint();
        this.revalidate();
    }

    @Subscribe
    @SuppressWarnings("unused")
    public void onPacksDoneLoading(PacksDoneLoadingEvent e){
        this.versionBox.setModel(new DefaultComboBoxModel<>(current().versions));
    }

    @Override
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        super.paint(g2);
        UIUtils.antialiasOn(g2);

        g2.setStroke(new BasicStroke(5));

        Pack pack = this.current();
        int y = 0;
        if(pack != null){
            // Draw Image
            BufferedImage background = pack.getImage();
            int x = ((this.getWidth() - background.getWidth()) / 2) - 20;
            y = ((this.getHeight() - background.getHeight()) / 2) - 80;
            g2.drawImage(background, x, y, 347, 182, null);
            g2.setColor(Color.black);
            g2.drawRect(x, y, 347, 182);

            // Draw Name
            g2.setFont(Resources.makeFont0("dinpro-black").deriveFont(32.0F));
            g2.setColor(Color.white);
            g2.drawString(pack.name, x + ((347 - g2.getFontMetrics().stringWidth(pack.name)) / 2), y + 182 + g2.getFontMetrics().getHeight());

            try{
                // Draw Version
                String version = pack.getLatest().version;
                g2.drawString(version, x + ((347 - g2.getFontMetrics().stringWidth(version)) / 2), y + 182 + g2.getFontMetrics().getHeight() * 2);
            } catch(Exception e){
                // Fallthrough
            }

            // Draw Position
            String position = this.ptr + 1 + "/" + this.packs.size();
            g2.drawString(position, ((this.getWidth() - g2.getFontMetrics().stringWidth(position)) / 2), y - 7);
        }

        pack = this.lastPack();
        if(pack != null){
            // Draw Last Pack Image
            Image image = pack.getImage();
            g2.drawImage(pack.getImage(), 0 - image.getWidth(null) / 2 - 50, y, 347, 182, null);
            g2.setColor(Color.black);
            g2.drawRect(0 - image.getWidth(null) / 2 - 50, y, 347, 182);
        }

        pack = this.nextPack();
        if(pack != null){
            // Draw Next Pack Image
            Image image = pack.getImage();
            g2.drawImage(image, this.getWidth() - image.getWidth(null) / 2, y, 347, 182, null);
            g2.setColor(Color.black);
            g2.drawRect(this.getWidth() - image.getWidth(null) / 2, y, 347, 182);
        }

        UIUtils.antialiasOff(g2);
    }

    public void next(){
        if(this.ptr++ >= this.packs.size() - 1){
            this.ptr = this.packs.size() - 1;
        }
    }

    public void back(){
        if(this.ptr-- <= 0){
            this.ptr = 0;
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e){
        int notches = e.getUnitsToScroll();

        if(notches < 0){
            this.back();
        } else{
            this.next();
        }

        this.versionBox.setModel(new DefaultComboBoxModel<>(current().versions));
        this.repaint();
    }
}