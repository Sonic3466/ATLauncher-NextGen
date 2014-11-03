/*
 * ATLauncher-NextGen - https://github.com/ATLauncher/ATLauncher-NextGen
 * Copyright (C) 2013 ATLauncher
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.atlauncher.ui.panel;

import com.atlauncher.ATLauncher;
import com.atlauncher.event.PackEvent;
import com.atlauncher.obj.Pack;
import com.atlauncher.thread.InstanceInstallWorker;
import com.atlauncher.ui.diag.LoadingDialog;

import com.google.common.eventbus.Subscribe;

import java.awt.Desktop;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.net.URL;

public final class PackDisplayPanel
extends CardDisplayPanel
implements MouseWheelListener{
    public PackDisplayPanel(){
        super();

        ATLauncher.EVENT_BUS.register(this);

        this.addMouseWheelListener(this);
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
            LoadingDialog diag = new LoadingDialog("Installing Pack");
            new InstanceInstallWorker(diag, this.current(), this.current().getLatest()).execute();
        } else if(e.id == PackEvent.INSTALL_SERVER){

        } else{
            throw new IllegalArgumentException("PackEvent#id is invalid");
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e){
        int notches = e.getUnitsToScroll();

        if(notches < 0){
            this.back();
        } else{
            this.next();
        }
    }
}