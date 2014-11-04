package com.atlauncher.event;

/**
 * Fired when the specified button is clicked
 *
 * Examples:
 *  ATLauncher.EVENT_BUS.post(new PackEvent(PackEvent.INSTALL_CLIENT)); <- Will run the install client operation
 *  ATLauncher.EVENT_BUS.post(new PackEvent(PackEvent.INSTALL_SERVER)); <- Will run the install server operation
 *  ATLauncher.EVENT_BUS.post(new PackEvent(PackEvent.OPEN_SUPPORT)); <- Will open the support website
 *  ATLauncher.EVENT_BUS.post(new PackEvent(PackEvent.OPEN_WEBSITE)); <- Will open the website
 */
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