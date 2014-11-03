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

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.StringTokenizer;

public enum OS {
    WINDOWS("win"),
    MAC("mac"),
    UNIX("nix:nux:aix"),
    SOLARIS("sunos");

    public static final String STRING = System.getProperty("os.name").toLowerCase();

    private final String key;

    private OS(String key) {
        this.key = key;
    }

    public static OS getCurrent() {
        for (OS os : OS.values()) {
            if (os.isCurrent()) {
                return os;
            }
        }

        return null;
    }

    public static Path getStorageLocation() {
        switch (getCurrent()) {
            case WINDOWS:
                return Paths.get(System.getenv("APPDATA"), ".atlauncher");
            case MAC:
                return Paths.get(System.getProperty("user.home"), "/Library/Application Support/.atlauncher");
            case SOLARIS:
            case UNIX:
            default:
                return Paths.get(System.getProperty("user.home"), ".atlauncher");
        }
    }

    public String[] getKeys() {
        return this.key.split(":");
    }

    public boolean isCurrent() {
        for (String key : this.key.split(":")) {
            if (OS.STRING.contains(key)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        StringTokenizer t = new StringTokenizer(this.name());
        StringBuilder builder = new StringBuilder();

        while (t.hasMoreTokens()) {
            String token = t.nextToken();
            builder.append(token.toUpperCase().charAt(0))
                    .append(token.toLowerCase().substring(1));
            if (t.hasMoreTokens()) {
                builder.append(" ");
            }
        }

        return builder.toString();
    }
}