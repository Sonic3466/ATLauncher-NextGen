package com.atlauncher.ui.frame;

import com.atlauncher.ui.panel.BackPanel;
import com.atlauncher.ui.panel.CenterPanel;
import com.atlauncher.ui.panel.RightPanel;
import com.atlauncher.ui.panel.TopPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.WindowConstants;

public final class ATLauncherFrame
extends DraggableFrame{
    private final RightPanel rightPanel = new RightPanel();
    private final CenterPanel centerPanel = new CenterPanel();
    private final TopPanel topPanel = new TopPanel();

    public ATLauncherFrame(){
        super("ATLauncher");

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.setMinimumSize(new Dimension(830, 510));
        this.setContentPane(new BackPanel());
        this.getContentPane().add(this.rightPanel, BorderLayout.EAST);
        this.getContentPane().add(this.centerPanel, BorderLayout.CENTER);
        this.getContentPane().add(this.topPanel, BorderLayout.NORTH);
    }
}