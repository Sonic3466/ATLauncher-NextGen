package com.atlauncher.event;

/**
 * Fired to change the central area:
 *
 * Examples:
 *  ATLauncher.EVENT_BUS.post(new UpdateCentralEvent("Hello World", "news", "gray")); <- Will show the news panel, title hello world, and a gray background
 */
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