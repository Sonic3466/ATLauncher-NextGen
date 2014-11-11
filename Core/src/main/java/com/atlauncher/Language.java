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
package com.atlauncher;

import com.atlauncher.annot.UpdateI18N;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

//TODO: Localize
//TODO: Get localizers
public enum Language {
    INSTANCE;

    private final Map<String, Properties> langs = new HashMap<String, Properties>();
    private volatile String current;

    private Language() {
        try {
            this.load("english");
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
    }

    @UpdateI18N
    public synchronized void load(String lang) throws IOException {
        if (!this.langs.containsKey(lang)) {
            Properties props = new Properties();
            File langFile = new File(Settings.LANGUAGES.toFile(), lang.toLowerCase() + ".lang");
            if (!langFile.exists()) {
                return; // Silently exit if the file doesn't exist
            }
            try(InputStream in = new FileInputStream(langFile)){
                props.load(in);
            }
            this.langs.put(lang, props);
        }

        this.current = lang;
    }

    public synchronized void reload(String lang) throws IOException {
        if (this.langs.containsKey(lang)) {
            this.langs.remove(lang);
        }

        Properties props = new Properties();
        try(InputStream in = new FileInputStream(Settings.LANGUAGES.resolve(lang.toLowerCase()).toFile())){
            props.load(in);
        }
        this.langs.put(lang, props);

        this.current = lang;
    }

    public synchronized String localize(String lang, String tag) {
        if (this.langs.containsKey(lang)) {
            Properties props = this.langs.get(lang);
            if (props.containsKey(tag)) {
                return props.getProperty(tag, tag);
            } else {
                if (lang.equalsIgnoreCase("English")) {
                    return "Unknown language key " + tag;
                } else {
                    return this.localize("English", tag);
                }
            }
        } else {
            return this.localize("English", tag);
        }
    }

    public synchronized String localize(String tag) {
        return this.localize(this.current, tag);
    }

    public static synchronized String tr(String tag){
        return INSTANCE.localize(tag);
    }

    public synchronized String getCurrent() {
        return this.current;
    }

    public static String[] available() {
        File[] files = Settings.LANGUAGES.toFile().listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".lang");
            }
        });
        String[] langs = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            langs[i] = files[i].getName().substring(0, 1).toUpperCase()
                    + files[i].getName().substring(1, files[i].getName().lastIndexOf("."));
        }
        return langs;
    }

    public static synchronized String current() {
        return INSTANCE.current;
    }
}