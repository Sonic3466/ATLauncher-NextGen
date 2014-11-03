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
package com.atlauncher.ui.frame;

import com.atlauncher.ATLauncher;
import com.atlauncher.plaf.UIUtils;
import com.atlauncher.ui.Blurable;
import com.atlauncher.ui.layer.BlurLayer;
import com.atlauncher.ui.layer.WaitLayer;
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

    private final WaitLayer wait = new WaitLayer();
    private final BlurLayer blur = new BlurLayer();

    public ATLauncherFrame(){
        super("ATLauncher");

        ATLauncher.EVENT_BUS.register(this);

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