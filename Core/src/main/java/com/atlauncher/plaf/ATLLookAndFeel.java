package com.atlauncher.plaf;

import javax.swing.UIDefaults;
import javax.swing.plaf.basic.BasicLookAndFeel;

public final class ATLLookAndFeel
extends BasicLookAndFeel{
    @Override
    protected void initClassDefaults(UIDefaults table){
        super.initClassDefaults(table);
        Object[] def = {
                "ButtonUI", get("ATLButtonUI"),
                "ToggleButtonUI", get("ATLToggleButtonUI"),
                "TabbedPaneUI", get("ATLTabbedPaneUI"),
                "LabelUI", get("ATLLabelUI"),
                "ProgressBarUI", this.get("ATLProgressBarUI")
        };
        table.putDefaults(def);
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