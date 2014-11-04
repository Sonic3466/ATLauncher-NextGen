package com.atlauncher.event;

import com.atlauncher.ui.PackUI;

public final class ChangePackUIEvent{
    public final PackUI ui;

    public ChangePackUIEvent(PackUI ui){
        this.ui = ui;
    }
}