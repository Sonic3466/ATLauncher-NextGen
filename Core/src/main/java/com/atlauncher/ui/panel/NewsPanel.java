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