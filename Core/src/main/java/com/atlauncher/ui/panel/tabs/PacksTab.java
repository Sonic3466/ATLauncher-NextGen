package com.atlauncher.ui.panel.tabs;

import com.atlauncher.ATLauncher;
import com.atlauncher.Packs;
import com.atlauncher.event.UpdatePacksEvent;
import com.atlauncher.obj.Pack;
import com.atlauncher.ui.comp.Card;
import com.atlauncher.ui.panel.CardDisplayPanel;
import com.atlauncher.ui.panel.CenterBottomPanel;
import com.atlauncher.ui.panel.PackPanel;
import com.atlauncher.ui.panel.PacksBottomPanel;

import com.google.common.eventbus.Subscribe;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public final class PacksTab
extends JPanel
implements Card{
    private final PacksBottomPanel bottomPanel = new PacksBottomPanel();
    private final CardDisplayPanel packDisplayPanel = new CardDisplayPanel();

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
    public void onUpdatePacks(UpdatePacksEvent event){
        this.packDisplayPanel.unregisterAll();
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                for(Pack pack : Packs.instance.all()){
                    packDisplayPanel.register(new PackPanel(pack));
                }
            }
        });
    }

    @Override
    public String id(){
        return "modpacks";
    }
}