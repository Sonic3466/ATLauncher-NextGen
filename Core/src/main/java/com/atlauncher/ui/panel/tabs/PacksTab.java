package com.atlauncher.ui.panel.tabs;

import com.atlauncher.ATLauncher;
import com.atlauncher.Packs;
import com.atlauncher.event.PackLoadedEvent;
import com.atlauncher.event.UpdatePacksEvent;
import com.atlauncher.ui.comp.Card;
import com.atlauncher.ui.panel.CenterBottomPanel;
import com.atlauncher.ui.panel.PackDisplayPanel;
import com.atlauncher.ui.panel.PackPanel;
import com.atlauncher.ui.panel.PacksBottomPanel;

import com.google.common.eventbus.Subscribe;

import java.awt.BorderLayout;
import javax.swing.JPanel;

public final class PacksTab
extends JPanel
implements Card{
    private final PacksBottomPanel bottomPanel = new PacksBottomPanel();
    private final PackDisplayPanel packDisplayPanel = new PackDisplayPanel();

    private final JPanel bottom = new JPanel(new BorderLayout());
    {
        this.bottom.add(new CenterBottomPanel(), BorderLayout.NORTH);
        this.bottom.add(this.bottomPanel, BorderLayout.CENTER);
        this.bottom.setOpaque(false);
    }

    public PacksTab(){
        super(new BorderLayout());
        this.setOpaque(false);

        ATLauncher.EVENT_BUS.register(this);

        this.add(this.bottom, BorderLayout.SOUTH);
        this.add(this.packDisplayPanel, BorderLayout.CENTER);
    }

    @Subscribe
    public void onAccountUpdate(UpdatePacksEvent e){
        Packs.load();
    }

    @Subscribe
    public void onPackLoaded(PackLoadedEvent e){
        this.packDisplayPanel.register(new PackPanel(e.pack));
        this.packDisplayPanel.repaint();
        this.packDisplayPanel.revalidate();
    }

    @Override
    public String id(){
        return "modpacks";
    }
}