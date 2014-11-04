package com.atlauncher.event;

/**
 * Fired to change the settings sub menu
 */
public final class UpdateSettingsEvent{
    public final String id;
    public final String title;

    public UpdateSettingsEvent(String id, String title){
        this.id = id;
        this.title = title;
    }
}