package com.atlauncher;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public enum Language {
    INSTANCE;

    private final Map<String, Properties> langs = new HashMap<String, Properties>();
    private volatile String current;

    private Language() {
        try {
            this.load("English");
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
    }

    public synchronized void load(String lang) throws IOException {
        if (!this.langs.containsKey(lang)) {
            Properties props = new Properties();
            File langFile = new File(Settings.LANGUAGES.toFile(), lang.toLowerCase() + ".lang");
            if (!langFile.exists()) {
                return; // Silently exit if the file doesn't exist
            }
            props.load(new FileInputStream(langFile));
            this.langs.put(lang, props);
        }

        this.current = lang;
    }

    public synchronized void reload(String lang) throws IOException {
        if (this.langs.containsKey(lang)) {
            this.langs.remove(lang);
        }

        Properties props = new Properties();
        props.load(new FileInputStream(new File(Settings.LANGUAGES.toFile(), lang.toLowerCase()
                + ".lang")));
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