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

/**
 * Fired to change the central area:
 *
 * Examples:
 *  ATLauncher.EVENT_BUS.post(new UpdateCentralEvent("Hello World", "news", "gray")); <- Will show the news panel, title hello world, and a gray background
 */
public final class UpdateCentralEvent{
    public final String title;
    public final String panel;
    public final String background;

    public UpdateCentralEvent(String title, String panel, String background){
        this.title = title;
        this.panel = panel;
        this.background = background;
    }
}