package com.atlauncher.ui.panel;

import com.atlauncher.ATLauncher;
import com.atlauncher.event.PackEvent;
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
    public void onPack(PackEvent e){
        if(e.id == PackEvent.OPEN_SUPPORT){
            try{
                Desktop.getDesktop().browse(new URL(this.current().support_url).toURI());
            } catch(Exception ex){
                ex.printStackTrace(System.err);
            }
        } else if(e.id == PackEvent.OPEN_WEBSITE){
            try{
                Desktop.getDesktop().browse(new URL(this.current().website_url).toURI());
            } catch(Exception ex){
                ex.printStackTrace(System.err);
            }
        } else if(e.id == PackEvent.INSTALL_CLIENT){
            System.out.println(this.current().getJson(this.current().getLatest().version));
        } else if(e.id == PackEvent.INSTALL_SERVER){

        } else{
            throw new IllegalArgumentException("PackEvent#id is invalid");
        }
    }
}