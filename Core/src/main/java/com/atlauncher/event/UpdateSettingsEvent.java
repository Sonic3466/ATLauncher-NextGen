package com.atlauncher.event;

public final class UpdateSettingsEvent{
    public final String id;
    public final String title;

    public UpdateSettingsEvent(String id, String title){
        this.id = id;
        this.title = title;
    }
}