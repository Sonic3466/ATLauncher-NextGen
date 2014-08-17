package com.atlauncher.ui;

import java.awt.Dimension;
import java.awt.Toolkit;

//TODO: Move to UIUtils
public final class Screen{
    private Screen(){}

    public static final Dimension SIZE = Toolkit.getDefaultToolkit().getScreenSize();
}