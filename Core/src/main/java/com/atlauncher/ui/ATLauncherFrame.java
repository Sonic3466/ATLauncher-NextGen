package com.atlauncher.ui;

import com.atlauncher.ui.panel.NewsPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;

public final class ATLauncherFrame
extends JFrame{
    private final NewsPanel newsPanel = new NewsPanel();
    private final JTabbedPane tabPane = new JTabbedPane(JTabbedPane.RIGHT);

    public ATLauncherFrame(){
        super("ATLauncher");

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.setMinimumSize(new Dimension(1000, 640));

        this.tabPane.addTab("News", this.newsPanel);

        this.add(this.tabPane, BorderLayout.CENTER);
    }
}