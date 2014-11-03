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
package com.atlauncher.ui.panel;

import com.atlauncher.Resources;
import com.atlauncher.Settings;
import com.atlauncher.obj.News;
import com.atlauncher.ui.comp.LightBarScrollPane;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.text.html.HTMLEditorKit;

public final class NewsPanel
extends JPanel{
    private final HTMLEditorKit HTML_KIT = new HTMLEditorKit(){{
        this.setStyleSheet(Resources.makeStyleSheet("news"));
    }};
    private final JEditorPane NEWS_PANE = new JEditorPane("text/html", ""){{
        this.setEditable(false);
        this.setForeground(Color.white);
        this.setBackground(new Color(40, 45, 50));
        this.setEditorKit(HTML_KIT);
    }};

    public NewsPanel(){
        super(new BorderLayout());
        this.add(new LightBarScrollPane(this.NEWS_PANE), BorderLayout.CENTER);
        this.reload();
        this.setBorder(null);
    }

    public void reload(){
        this.NEWS_PANE.setText("");
        this.NEWS_PANE.setText(this.getHTML());
        this.NEWS_PANE.setCaretPosition(0);
    }

    private String getHTML(){
        StringBuilder builder = new StringBuilder();
        builder.append("<html>");

        News[] news = Settings.getNews();
        for(int i = 0; i < news.length; i++){
            builder.append(news[i].toString());

            if(i < news.length - 1){
                builder.append("<hr/>");
            }
        }

        builder.append("</html>");
        return builder.toString();
    }
}