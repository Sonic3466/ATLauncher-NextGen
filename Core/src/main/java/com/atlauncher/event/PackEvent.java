package com.atlauncher.event;

public final class PackEvent{
    public static final byte INSTALL_CLIENT = 0x0;
    public static final byte INSTALL_SERVER = 0x1;
    public static final byte OPEN_SUPPORT = 0x2;
    public static final byte OPEN_WEBSITE = 0x3;

    public final int id;

    public PackEvent(int id){
        this.id = id;
    }
}