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

@Json
public final class News{
    public final String title;
    public final String link;
    public final String content;
    public final int comments;

    private News(String title, String link, String content, int comments){
        this.title = title;
        this.link = link;
        this.content = content;
        this.comments = comments;
    }

    @Override
    public String toString(){
        return "<p id=\"newsHeader\">- <a href=\"" + this.link + "\">" + this.title + "</a> (" +
                this.comments + " " + (this.comments == 1 ? "comment" : "comments") + ")</p>" +
                "<p id=\"newsBody\">" + this.content + "</p><br/>";
    }
}