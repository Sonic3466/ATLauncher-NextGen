package com.atlauncher.ui.frame;

import com.atlauncher.plaf.UIUtils;
import com.atlauncher.ui.Blurable;
import com.atlauncher.ui.layer.BlurLayer;
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
import javax.swing.JFrame;
import javax.swing.JLayer;
import javax.swing.WindowConstants;

public final class ATLauncherFrame
extends JFrame
implements MouseListener,
           MouseMotionListener,
           Blurable{

    private int dGX;
    private int dGY;

    private final BlurLayer blur = new BlurLayer();

    public ATLauncherFrame(){
        super("ATLauncher");

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setUndecorated(true);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.setLayout(new BorderLayout());
        this.setMinimumSize(new Dimension(830, 500));

        BackPanel backPanel = new BackPanel();
        this.add(new JLayer<>(backPanel, this.blur));
        backPanel.add(new RightPanel(), BorderLayout.EAST);
        backPanel.add(new TopPanel(), BorderLayout.NORTH);
        backPanel.add(new CenterPanel(), BorderLayout.CENTER);

        this.setLocation((int) (UIUtils.SIZE.getWidth() - this.getWidth()) / 2, (int) (UIUtils.SIZE.getHeight() - this.getHeight()) / 2);
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

    @Override
    public void blur(){
        this.blur.setBlur(true);
    }

    @Override
    public void clear(){
        this.blur.setBlur(false);
    }
}