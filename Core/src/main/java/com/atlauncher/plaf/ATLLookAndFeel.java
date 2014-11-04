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

import com.atlauncher.plaf.button.ATLButtonUI;
import com.atlauncher.plaf.button.ATLToggleButtonUI;

import java.awt.Color;
import javax.swing.UIDefaults;
import javax.swing.plaf.metal.MetalLookAndFeel;

public final class ATLLookAndFeel
extends MetalLookAndFeel{
    @Override
    protected void initClassDefaults(UIDefaults table){
        super.initClassDefaults(table);
        // Button
        table.put("ButtonUI", ATLButtonUI.class.getCanonicalName());
        table.put("ToggleButtonUI", ATLToggleButtonUI.class.getCanonicalName());
        Object[] def = {
                "TabbedPaneUI", get("ATLTabbedPaneUI"),
                "LabelUI", get("ATLLabelUI"),
                "TextFieldUI", this.get("ATLTextFieldUI"),
                "ProgressBarUI", this.get("ATLProgressBarUI")
        };
        table.putDefaults(def);
    }

    @Override
    protected void initComponentDefaults(UIDefaults table){
        super.initComponentDefaults(table);
        Object[] defs = {
                "Label.font", UIUtils.DINPRO_BLACK.deriveFont(12.0F),
                "ComboBox.selectionForeground", Color.black,
                "ComboBox.selectionBackground", Color.green,
                "ComboBox.foreground", Color.black,
                "ComboBox.background", Color.white,
                "Panel.background", UIUtils.GRAY
        };
        table.putDefaults(defs);
    }

    private String get(String s){
        return "com.atlauncher.plaf." + s;
    }

    @Override
    public String getName(){
        return "ATLLookAndFeel";
    }

    @Override
    public String getID(){
        return "com.atlauncher.plaf.ATLLookAndFeel";
    }

    @Override
    public String getDescription(){
        return "ATLauncher's native look and feel";
    }

    @Override
    public boolean isNativeLookAndFeel(){
        return false;
    }

    @Override
    public boolean isSupportedLookAndFeel(){
        return true;
    }
}