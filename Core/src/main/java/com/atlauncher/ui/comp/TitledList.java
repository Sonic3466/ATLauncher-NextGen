package com.atlauncher.ui.comp;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

public final class TitledList<T>
extends JPanel{
    public final JList<T> list;
    public final JLabel title;

    public TitledList(String title){
        super(new BorderLayout());
        this.title = new JLabel(title);
        this.list = new JList<>();
        this.add(this.title, BorderLayout.NORTH);
        this.add(new LightBarScrollPane(list), BorderLayout.CENTER);
    }
}