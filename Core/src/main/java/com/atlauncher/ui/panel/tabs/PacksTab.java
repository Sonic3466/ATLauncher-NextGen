package com.atlauncher.ui.panel.tabs;

import com.atlauncher.obj.Pack;
import com.atlauncher.ui.comp.Card;
import com.atlauncher.ui.panel.BottomPanel;
import com.atlauncher.ui.panel.CenterBottomPanel;
import com.atlauncher.ui.panel.PackDisplayPanel;
import com.atlauncher.ui.panel.PackPanel;

import java.awt.BorderLayout;
import javax.swing.JPanel;

public final class PacksTab
extends JPanel
implements Card{
    private final BottomPanel bottomPanel = new BottomPanel();
    private final PackDisplayPanel packDisplayPanel = new PackDisplayPanel();
    {
        this.packDisplayPanel.register(new PackPanel(new Pack("The CrackPack", "Mindblown")));
        this.packDisplayPanel.register(new PackPanel(new Pack("MoonQuest", "Welcome to this Yogscast Inspired Unofficial Minecraft Modpack! If you haven't seen their new Minecraft series yet this modpack has all those mods and now you can go ahead and play along too! You should really go watch it!")));
    }

    private final JPanel bottom = new JPanel(new BorderLayout());
    {
        this.bottom.add(new CenterBottomPanel(), BorderLayout.NORTH);
        this.bottom.add(this.bottomPanel, BorderLayout.CENTER);
        this.bottom.setOpaque(false);
    }

    public PacksTab(){
        super(new BorderLayout());
        this.setOpaque(false);

        this.add(this.bottom, BorderLayout.SOUTH);
        this.add(this.packDisplayPanel, BorderLayout.CENTER);
    }

    @Override
    public String id(){
        return "modpacks";
    }
}