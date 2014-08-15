package com.atlauncher.event;

import com.atlauncher.obj.Pack;

public final class PackLoadedEvent{
    public final Pack pack;

    public PackLoadedEvent(Pack pack){
        this.pack = pack;
    }
}
