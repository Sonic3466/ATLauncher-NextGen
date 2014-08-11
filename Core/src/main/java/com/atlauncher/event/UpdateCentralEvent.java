package com.atlauncher.event;

public final class UpdateCentralEvent{
    public final String title;
    public final String panel;
    public final String background;

    public UpdateCentralEvent(String title, String panel, String background){
        this.title = title;
        this.panel = panel;
        this.background = background;
    }
}