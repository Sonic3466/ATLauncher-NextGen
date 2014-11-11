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

import com.atlauncher.Settings;

public final class FileHash {
    public final String name;
    public final String type;
    public final String md5;
    public final String sha1;
    public final int size;

    public FileHash(String name, String type, String md5, String sha1, int size) {
        this.name = name;
        this.type = type;
        this.md5 = md5;
        this.sha1 = sha1;
        this.size = size;
    }

    public Downloadable getDownload() {
        switch (type) {
            case "library": {
                // Don't download the .sh or .bat files
                return null;
            }
            case "json": {
                return new Downloadable("newlauncher/" + this.name, Settings.DATA.resolve(this.type.toLowerCase()),
                        md5, true);
            }
            default: {
                return new Downloadable("newlauncher/" + this.type + "/" + this.name, Settings.DATA.resolve(this.type
                        .toLowerCase()), md5, true);
            }
        }
    }
}