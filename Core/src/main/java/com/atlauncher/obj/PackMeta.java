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

import java.util.Map;

public final class PackMeta{
    public final String version;
    public final String minecraft;
    public final Map<String, String> mainClass;
    public final Map<String, String> extraArguments;
    public final Library[] libraries;
    public final Mod[] mods;

    public PackMeta(String version, String minecraft, Map<String, String> mainClass, Map<String, String> extraArguments, Library[] libraries, Mod[] mods){
        this.version = version;
        this.minecraft = minecraft;
        this.mainClass = mainClass;
        this.extraArguments = extraArguments;
        this.libraries = libraries;
        this.mods = mods;
    }
}