package com.atlauncher.event;

import com.atlauncher.obj.Pack;

/**
 * Fires when a pack is loaded into the launcher, used to append the packs to the UI
 */
public final class PackLoadedEvent{
    public final Pack pack;

    public PackLoadedEvent(Pack pack){
        this.pack = pack;
    }
}
