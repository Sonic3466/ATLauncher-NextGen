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

import com.atlauncher.ATLauncher;
import com.atlauncher.Settings;
import com.atlauncher.utils.Digester;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;
import java.util.zip.GZIPInputStream;

public final class Downloadable implements Runnable {
    public final String endpoint;
    public final String hash;
    public final Path output;
    public final boolean md5;

    private HttpURLConnection conn;

    public Downloadable(String endpoint, Path output) {
        this(endpoint, output, null, true);
    }

    public Downloadable(String endpoint, Path output, String hash, boolean md5) {
        this.endpoint = endpoint;
        this.hash = hash;
        this.md5 = md5;
        this.output = output.resolve(endpoint.substring(endpoint.lastIndexOf('/') + 1));

        try {
            if (!Files.exists(output.getParent())) {
                Files.createDirectories(output.getParent());
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    private boolean isGzip() {
        return (this.getConnection().getContentEncoding() != null && this.getConnection().getContentEncoding()
                .equalsIgnoreCase("gzip"));
    }

    private HttpURLConnection getConnection() {
        if (this.conn == null) {
            try {
                if (this.endpoint.startsWith("http://") || this.endpoint.startsWith("https://")) {
                    // We provided a full URL so don't try to resolve it
                    this.conn = (HttpURLConnection) new URL(this.endpoint).openConnection();
                } else {
                    this.conn = (HttpURLConnection) new URL(Settings.server.getFileURL(this.endpoint)).openConnection();
                }

                this.conn.setUseCaches(false);
                this.conn.setDefaultUseCaches(false);
                this.conn.setRequestProperty("Accept-Encoding", "gzip");
                this.conn.setRequestProperty("User-Agent", Settings.USER_AGENT);
                this.conn.setRequestProperty("Cache-Control", "no-store,max-age=0,no-cache");
                this.conn.setRequestProperty("Expires", "0");
                this.conn.setRequestProperty("Pragma", "no-cache");
                this.conn.connect();
            } catch (Exception ex) {
                ex.printStackTrace(System.err);
            }
        }

        return this.conn;
    }

    public boolean needsUpdate() {
        if (this.hash == null) {
            return true;
        }

        if (Files.exists(this.output)) {
            if (this.md5) {
                return !Digester.getMD5(this.output).equalsIgnoreCase(this.hash);
            } else {
                return !Digester.getSHA1(this.output).equalsIgnoreCase(this.hash);
            }
        }

        return true;
    }

    @Override
    public void run() {
        if (Files.exists(this.output)) {
            if (this.needsUpdate()) {
                ATLauncher.LOGGER.info("Updating File " + this.output);
                try {
                    Files.delete(this.output);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                return;
            }
        }

        try (InputStream stream = this.getStream();
             FileChannel channel = this.getChannel();
             ReadableByteChannel rbc = Channels.newChannel(stream)) {

            channel.transferFrom(rbc, 0, Long.MAX_VALUE);
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }

    private FileChannel getChannel() throws IOException {
        return FileChannel.open(this.output, EnumSet.of(StandardOpenOption.CREATE, StandardOpenOption.WRITE));
    }

    private InputStream getStream() {
        try {
            return (this.isGzip() ? new GZIPInputStream(this.getConnection().getInputStream()) : this.getConnection().getInputStream());
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            return this.getConnection().getErrorStream();
        }
    }
}