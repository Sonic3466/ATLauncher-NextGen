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