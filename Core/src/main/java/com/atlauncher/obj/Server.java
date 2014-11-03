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
package com.atlauncher.obj;

import java.util.List;

public final class Server{
    public final String name;
    public final String url;
    public final boolean selectable;
    public final boolean master;

    public Server(String name, String url, boolean selectable, boolean master){
        this.name = name;
        this.url = url;
        this.selectable = selectable;
        this.master = master;
    }

    public String getFileURL(String endpoint){
        return "http://" + this.url + "/" + endpoint;
    }

    @Override
    public String toString(){
        return this.name;
    }

    public static interface Servers
    extends List<Server>{}
}