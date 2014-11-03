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

import com.atlauncher.plaf.UIUtils;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.text.html.StyleSheet;

public final class Resources{
    public static final EnumSet<StandardOpenOption> WRITE = EnumSet.of(StandardOpenOption.CREATE, StandardOpenOption.WRITE);
    public static final EnumSet<StandardOpenOption> READ = EnumSet.of(StandardOpenOption.READ);

    private static final Map<String, Object> resources = new HashMap<>();
    private static final String[] FONT_FAMILIES = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

    private Resources(){}

    public static boolean isSystemFont(String name){
        for(String str : FONT_FAMILIES){
            if(str.equalsIgnoreCase(name)){
                return true;
            }
        }

        return false;
    }

    public static StyleSheet makeStyleSheet(String name){
        try{
            if(resources.containsKey(name)){
                Object obj = resources.get(name);
                if(!(obj instanceof StyleSheet)){
                    throw new RuntimeException("Reference for " + name + " ended up with a bad value, suggested=" + StyleSheet.class.getName() + "; got=" + obj.getClass().getName());
                } else{
                    return (StyleSheet) obj;
                }
            } else{
                StyleSheet sheet = new StyleSheet();
                Reader reader = new InputStreamReader(System.class.getResourceAsStream("/assets/css/" + name + ".css"));
                sheet.loadRules(reader, null);
                reader.close();

                resources.put(name, sheet);
                return sheet;
            }
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    public static BufferedImage makeImage(String name, int scale){
        return UIUtils.resize(makeImage(name), scale, scale);
    }

    public static BufferedImage makeImage(String name){
        try{
            if(resources.containsKey(name)){
                Object obj = resources.get(name);
                if(!(obj instanceof BufferedImage)){
                    throw new RuntimeException("Reference for " + name + " ended up with a bad value, suggested=" + Font.class.getName() + "; got=" + obj.getClass().getName());
                } else{
                    return (BufferedImage) obj;
                }
            } else{
                BufferedImage image = ImageIO.read(System.class.getResource("/assets/images/" + name + ".png"));
                resources.put(name, image);
                return image;
            }
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    public static Font makeFont(String name){
        try{
            if(resources.containsKey(name)){
                Object obj = resources.get(name);
                if(!(obj instanceof Font)){
                    throw new RuntimeException("Reference for " + name + " ended up with a bad value, suggested=" + Font.class.getName() + "; got=" + obj.getClass().getName());
                } else{
                    return (Font) obj;
                }
            } else{
                if(isSystemFont(name)){
                    Font f = new Font(name, Font.PLAIN, 0);
                    resources.put(name, f);
                    return f;
                } else{
                    URL url = System.class.getResource("/assets/font/" + name + ".ttf");
                    if(url == null){
                        throw new NullPointerException("Cannot find font " + name);
                    } else{
                        Font f = Font.createFont(Font.TRUETYPE_FONT, url.openStream());
                        resources.put(name, f);
                        return f;
                    }
                }
            }
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    public static Font makeFont0(String name){
        try{
            URL url = System.class.getResource("/assets/font/" + name + ".ttf");
            if(url == null){
                throw new NullPointerException("Cannot find font " + name);
            } else{
                Font f = Font.createFont(Font.TRUETYPE_FONT, url.openStream());
                resources.put(name, f);
                return f;
            }
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
}