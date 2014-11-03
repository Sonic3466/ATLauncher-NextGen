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

import com.atlauncher.obj.Pack;

import java.util.Comparator;

public final class PacksComparator
implements Comparator<Pack>{
    @Override
    public int compare(Pack o1, Pack o2){
        if(o1.position == o2.position){
            return 0;
        } else if(o1.position > o2.position){
            return 1;
        } else{
            return -1;
        }
    }

    @Override
    public boolean equals(Object obj){
        return false;
    }
}