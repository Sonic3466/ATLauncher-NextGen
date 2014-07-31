package com.atlauncher.ui.panel;

import com.atlauncher.ATLauncher;
import com.atlauncher.event.ShowEvent;
import com.atlauncher.ui.comp.Card;
import com.atlauncher.ui.panel.tabs.AccountsTab;
import com.atlauncher.ui.panel.tabs.InstancesTab;
import com.atlauncher.ui.panel.tabs.NewsTab;
import com.atlauncher.ui.panel.tabs.PacksTab;
import com.atlauncher.ui.panel.tabs.SettingsTab;

import com.google.common.eventbus.Subscribe;

import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public final class CenterPanel
extends JPanel{
    private final PacksTab packs = new PacksTab();
    private final NewsTab news = new NewsTab();
    private final AccountsTab accounts = new AccountsTab();
    private final InstancesTab instances = new InstancesTab();
    private final SettingsTab settings = new SettingsTab();

    public CenterPanel(){
        super(new CardLayout());
        this.setOpaque(false);
        ATLauncher.EVENT_BUS.register(this);

        this.register(this.news);
        this.register(this.packs);
        this.register(this.accounts);
        this.register(this.instances);
        this.register(this.settings);
    }

    @Subscribe
    public void onShow(final ShowEvent event){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                ((CardLayout) getLayout()).show(CenterPanel.this, event.id);
                revalidate();
            }
        });
    }

    public <T extends JPanel & Card> void register(T card){
        this.add(card, card.id());
    }
}