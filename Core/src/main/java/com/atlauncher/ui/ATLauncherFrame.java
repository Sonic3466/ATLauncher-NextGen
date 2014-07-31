package com.atlauncher.ui;

import com.atlauncher.Resources;
import com.atlauncher.ui.panel.BackPanel;
import com.atlauncher.ui.panel.CenterPanel;
import com.atlauncher.ui.panel.RightPanel;
import com.atlauncher.ui.panel.TopPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public final class ATLauncherFrame
extends JFrame
implements MouseListener, MouseMotionListener{
    private final BufferedImage background = Resources.makeImage("background");
    private final RightPanel rightPanel = new RightPanel();
    private final CenterPanel centerPanel = new CenterPanel();
    private final TopPanel topPanel = new TopPanel();

    private int dGX;
    private int dGY;

    public ATLauncherFrame(){
        super("ATLauncher");

        this.setUndecorated(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.setMinimumSize(new Dimension(830, 510));
        this.addMouseListener(this);
        this.setContentPane(new BackPanel());
        this.addMouseMotionListener(this);
        this.getContentPane().add(this.rightPanel, BorderLayout.EAST);
        this.getContentPane().add(this.centerPanel, BorderLayout.CENTER);
        this.getContentPane().add(this.topPanel, BorderLayout.NORTH);
    }

    @Override
    public void mousePressed(MouseEvent e){
        if(e.getButton() == MouseEvent.BUTTON1){
            this.dGX = e.getX();
            this.dGY = e.getY();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e){
        if((e.getModifiersEx() & InputEvent.BUTTON1_DOWN_MASK) != 0){
            this.setLocation(e.getXOnScreen() - this.dGX, e.getYOnScreen() - this.dGY);
        }
    }

    @Override public void mouseMoved(MouseEvent e){}
    @Override public void mouseReleased(MouseEvent e){}
    @Override public void mouseEntered(MouseEvent e){}
    @Override public void mouseExited(MouseEvent e){}
    @Override public void mouseClicked(MouseEvent e){}
}