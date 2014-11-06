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
import com.atlauncher.plaf.container.ATLSplitPaneUI;
import com.atlauncher.plaf.container.ATLTabbedPaneUI;
import com.atlauncher.plaf.input.ATLPasswordFieldUI;
import com.atlauncher.plaf.input.ATLTextFieldUI;
import com.atlauncher.plaf.output.ATLLabelUI;

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

        // Container
        table.put("TabbedPaneUI", ATLTabbedPaneUI.class.getCanonicalName());
        table.put("SplitPaneUI", ATLSplitPaneUI.class.getCanonicalName());

        // Output
        table.put("LabelUI", ATLLabelUI.class.getCanonicalName());

        // Input
        table.put("TextFieldUI", ATLTextFieldUI.class.getCanonicalName());
        table.put("PasswordFieldUI", ATLPasswordFieldUI.class.getCanonicalName());
    }

    @Override
    protected void initComponentDefaults(UIDefaults table){
        super.initComponentDefaults(table);
        table.put("Label.font", UIUtils.DINPRO_BLACK.deriveFont(12.0F));
        table.put("ComboBox.selectionForeground", Color.black);
        table.put("ComboBox.selectionBackground", Color.green);
        table.put("ComboBox.foreground", Color.black);
        table.put("ComboBox.background", Color.white);
        table.put("Panel.background", UIUtils.GRAY);
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