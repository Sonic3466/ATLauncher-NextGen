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

import com.atlauncher.annot.Json;

import java.nio.file.Path;

@Json
public final class Library{
    public final String url;
    public final String file;
    public final String server;
    public final String md5;
    public final String download;

    public Library(String url, String file, String server, String md5, String download){
        this.url = url;
        this.file = file;
        this.server = server;
        this.md5 = md5;
        this.download = download;
    }

    public Downloadable getDownload(Path dir){
        return new Downloadable(this.url, dir, this.md5, true);
    }
}