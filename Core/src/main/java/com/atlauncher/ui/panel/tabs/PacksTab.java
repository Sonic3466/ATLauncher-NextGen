package com.atlauncher.ui.panel.tabs;

import com.atlauncher.ATLauncher;
import com.atlauncher.Settings;
import com.atlauncher.event.ChangePackUIEvent;
import com.atlauncher.event.UpdatePacksEvent;
import com.atlauncher.thread.CollectPacksWorker;
import com.atlauncher.ui.comp.Card;

import com.google.common.eventbus.Subscribe;

import java.awt.BorderLayout;
import javax.swing.JPanel;

public final class PacksTab
extends JPanel
implements Card{
    private JPanel currentUI = Settings.packUI.panel();

    public PacksTab(){
        super(new BorderLayout());
        this.setOpaque(false);

        ATLauncher.EVENT_BUS.register(this);

        this.add(currentUI, BorderLayout.CENTER);
    }

    @Subscribe
    public void onAccountUpdate(UpdatePacksEvent e){
        ATLauncher.LOGGER.info("Collecting Packs");
        new CollectPacksWorker().execute();
    }

    @Subscribe
    public void changeUI(ChangePackUIEvent e){
        this.remove(currentUI);
        this.currentUI = e.ui.panel();
        this.add(this.currentUI, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }

    @Override
    public String id(){
        return "modpacks";
    }
}