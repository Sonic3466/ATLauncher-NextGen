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
package com.atlauncher.plaf;

import com.atlauncher.Resources;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public final class UIUtils{
    private UIUtils(){}

    public static final Color TRANSPARENT = new Color(0, 0, 0, 0);
    public static final Color GRAY = new Color(40, 45, 50);

    public static final Dimension SIZE = Toolkit.getDefaultToolkit().getScreenSize();

    public static final Font DINPRO_BLACK = Resources.makeFont0("dinpro-black");

    private static Map<RenderingHints.Key, Object> hints;
    private static Map<RenderingHints.Key, Object> oldHints;

    public static Image flip(BufferedImage image){
        AffineTransform mirror = AffineTransform.getScaleInstance(-1, 1);
        mirror.translate(-image.getWidth(null), 0);
        AffineTransformOp op = new AffineTransformOp(mirror, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        image = op.filter(image, null);
        return image;
    }

    public static <T extends Image> BufferedImage resize(T img, int len, int height){
        BufferedImage i = new BufferedImage(len, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = i.createGraphics();
        g.drawImage(img, 0, 0, len, height, null);
        g.dispose();
        g.setComposite(AlphaComposite.Src);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        return i;
    }

    public static AlphaComposite alpha(float alpha){
        return AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
    }

    @Deprecated
    public static void drawCrossHair(Graphics g, int x, int y, int len, Color c){
        int subLen = (len / 2) - 1;
        g.setColor(c);
        g.fillRect(x, y, len, 1);
        g.fillRect(x - subLen, y - subLen, 1, len);
    }

    public static BufferedImage createColoredBackground(Color c){
        BufferedImage background = Resources.makeImage("background/scene");
        BufferedImage b = new BufferedImage(background.getWidth(), background.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D) b.getGraphics();
        g.setColor(c);
        g.fillRect(0, 0, b.getWidth(), b.getHeight());
        return b;
    }

    public static void antialiasOn(Graphics g){
        Graphics2D g2 = (Graphics2D) g;

        if(hints == null){
            hints = new HashMap<>();
            hints.put(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
            hints.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            oldHints = new HashMap<>();
            oldHints.put(RenderingHints.KEY_FRACTIONALMETRICS, g2.getRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS));
            oldHints.put(RenderingHints.KEY_TEXT_ANTIALIASING, g2.getRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING));
            oldHints.put(RenderingHints.KEY_ANTIALIASING, g2.getRenderingHint(RenderingHints.KEY_ANTIALIASING));
        }

        g2.addRenderingHints(hints);
    }

    public static void antialiasOff(Graphics g){
        ((Graphics2D) g).addRenderingHints(oldHints);
    }
}