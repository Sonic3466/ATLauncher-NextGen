package com.atlauncher.ui.panel;

import com.atlauncher.ATLauncher;
import com.atlauncher.event.CurrentPackInstallEvent;
import com.atlauncher.event.CurrentPackInstallServerEvent;
import com.atlauncher.event.CurrentPackSupportEvent;
import com.atlauncher.event.CurrentPackWebsiteEvent;
import com.atlauncher.obj.Pack;

import com.google.common.eventbus.Subscribe;

import java.awt.Desktop;
import java.net.URL;

public final class PackDisplayPanel
extends CardDisplayPanel{
    public PackDisplayPanel(){
        super();
        ATLauncher.EVENT_BUS.register(this);
    }

    public Pack current(){
        return (((PackPanel) this.cards.get(this.ptr)).pack);
    }

    @Subscribe
    public void onPackSupport(CurrentPackSupportEvent e){
        try{
            Desktop.getDesktop().browse(new URL(this.current().support_url).toURI());
        } catch(Exception ex){
            ex.printStackTrace(System.err);
        }
    }

    @Subscribe
    public void onPackWebsite(CurrentPackWebsiteEvent e){
        try{
            Desktop.getDesktop().browse(new URL(this.current().website_url).toURI());
        } catch(Exception ex){
            ex.printStackTrace(System.err);
        }
    }

    @Subscribe
    public void onPackInstall(CurrentPackInstallEvent e){
        System.out.println(this.current().getJson(this.current().getLatest().version));
    }

    @Subscribe
    public void onPackInstallServer(CurrentPackInstallServerEvent e){

    }
}