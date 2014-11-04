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
package com.atlauncher.utils;

import java.util.HashMap;
import java.util.Map;

public final class CLIParser{
    public final Map<String, String> args = new HashMap<>();

    public CLIParser(String... args){
        if((args.length % 2) != 0){
            throw new IllegalArgumentException("Args need an even number of parameters");
        }

        for(int i = 0, j = 1; j < args.length; i += 2, j += 2){
            if(!args[i].startsWith("--")){
                throw new IllegalArgumentException("Arg " + args[i] + " is invalid [needs --]");
            }

            this.args.put(args[i].substring(2), args[j]);
        }
    }

    public String get(String key){
        return this.args.get(key);
    }
}