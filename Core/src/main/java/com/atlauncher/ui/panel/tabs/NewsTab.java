package com.atlauncher.ui.panel.tabs;

import com.atlauncher.ui.comp.Card;
import com.atlauncher.ui.panel.NewsPanel;

import java.awt.BorderLayout;
import javax.swing.JPanel;

public final class NewsTab
extends JPanel
implements Card{
    public NewsTab(){
        super(new BorderLayout());
        this.add(new NewsPanel(), BorderLayout.CENTER);
    }

    @Override
    public String id(){
        return "news";
    }
}