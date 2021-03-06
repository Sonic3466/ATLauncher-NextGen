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

import com.atlauncher.obj.Downloadable;
import com.atlauncher.obj.FileHash;
import com.atlauncher.obj.News;
import com.atlauncher.obj.Server;
import com.atlauncher.ui.PackUI;
import com.atlauncher.ui.diag.LoadingDialog;
import com.atlauncher.utils.OS;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;
import javax.swing.SwingUtilities;

public final class Settings {
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public static final Properties properties = new Properties();

    public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.36 (KHTML, like " +
            "Gecko) Chrome/28.0.1500.72 Safari/537.36 ATLauncher/3.2.0.0";

    public static final Path CORE = OS.getStorageLocation();
    public static final Path DATA = CORE.resolve("data");
    public static final Path JSON = DATA.resolve("json");
    public static final Path IMAGES = DATA.resolve("image");
    public static final Path SKINS = DATA.resolve("skin");
    public static final Path LANGUAGES = DATA.resolve("language");
    public static final Path ACCOUNTS = DATA.resolve("accounts");
    public static final Path INSTANCES = CORE.resolve("instances");
    public static final Path RESOURCES = DATA.resolve("resources");
    public static final Path LIBS = DATA.resolve("libs");
    public static final Path TMP;

    static {
        try {
            Path path = CORE.resolve("atlauncher.cfg");

            if (!Files.exists(path)) {
                Files.createFile(path);
            }

            try (InputStream in = new FileInputStream(path.toFile())) {
                properties.load(in);
                Language.INSTANCE.load(Settings.properties.getProperty("language", "English"));
            }
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }

        try{
            TMP = Files.createTempDirectory(Paths.get(System.getProperty("java.io.tmpdir")), "tmp");
            TMP.toFile().deleteOnExit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Path path = CORE.resolve("atlauncher.cfg");
                    ATLauncher.LOGGER.debug("Saving Settings");
                    try (OutputStream out = new FileOutputStream(path.toFile())) {
                        properties.store(out, "Don't Edit This File");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace(System.err);
                }
            }
        }));
    }

    public static Path downloads = Paths.get(Settings.properties.getProperty("downloads", CORE.resolve("downloads")
            .toString()));
    public static PackUI packUI = PackUI.valueOf(Settings.properties.getProperty("packUI", "SLIDER"));
    public static Server[] SERVERS = ATLauncher.getInjector().getInstance(Server[].class);
    public static Server server = find(Settings.properties.getProperty("selectedServer", "Auto"));

    public static Server find(String name) {
        for (Server server : SERVERS) {
            if (server.name.equalsIgnoreCase(name) && server.selectable) {
                return server;
            }
        }

        return SERVERS[0];
    }

    public static Server findMasterServer() {
        for (Server server : SERVERS) {
            if (server.master) {
                return server;
            }
        }

        return SERVERS[0];
    }

    private Settings() {
    }

    private static FileHash[] getFileHashes() {
        Path hashes = DATA.resolve("hashes.json");
        if (!Files.exists(hashes)) {
            new Downloadable("newlauncher/hashes.json", Settings.DATA, null, false).run();
        }

        try {
            InputStream fis = new FileInputStream(hashes.toFile());
            FileHash[] h = GSON.fromJson(new InputStreamReader(fis), FileHash[].class);
            fis.close();
            return h;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static News[] getNews() {
        Path news = JSON.resolve("news.json");
        if (!Files.exists(news)) {
            updateLauncherFiles();
            return getNews();
        }

        try {
            InputStream fis = new FileInputStream(news.toFile());
            News[] n = GSON.fromJson(new InputStreamReader(fis), News[].class);
            fis.close();
            return n;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void findActiveServers() {
        Downloadable download = new Downloadable(findMasterServer().getFileURL("newlauncher/servers.json"), Settings
                .DATA);

        download.run();

        if (Files.exists(Settings.DATA.resolve("servers.json"))) {
            try {
                SERVERS = GSON.fromJson(new FileReader(Settings.DATA.resolve("servers.json").toFile()), Server[].class);
            } catch (Exception ex) {
                SERVERS = ATLauncher.getInjector().getInstance(Server[].class);
                ex.printStackTrace();
            }
        }
    }

    public static void updateLauncherFiles() {
        final LoadingDialog frame = new LoadingDialog("Downloading");
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame.setVisible(true);
            }
        });
        FileHash[] hashes = Settings.getFileHashes();
        for (int i = 0; i < hashes.length; i++) {
            frame.title.setText(hashes[i].name);
            frame.bar.setValue((i * 100) / hashes.length);
            Downloadable dl = hashes[i].getDownload();
            if (dl != null) {
                dl.run();
            }
        }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame.dispose();
            }
        });
    }
}