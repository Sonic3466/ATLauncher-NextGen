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

import java.util.LinkedList;
import java.util.List;

public final class Version{
    public final String version;
    public final String minecraft;
    public final int memory;
    public final int permGen;
    public final List<Library> libraries;
    public final List<Mod> mods;

    public Version(String version, String minecraft, int memory, int permGen, List<Library> libraries, List<Mod> mods){
        this.version = version;
        this.minecraft = minecraft;
        this.memory = memory;
        this.permGen = permGen;
        this.libraries = libraries;
        this.mods = mods;
    }

    public List<Mod> getClientMods(){
        List<Mod> mods = new LinkedList<>();
        for(Mod mod : this.mods){
            if(mod.client){
                mods.add(mod);
            }
        }
        return mods;
    }
}