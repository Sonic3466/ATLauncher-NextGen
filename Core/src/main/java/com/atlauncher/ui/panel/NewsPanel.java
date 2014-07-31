package com.atlauncher.ui.panel;

import com.atlauncher.Resources;
import com.atlauncher.Settings;
import com.atlauncher.obj.News;

import java.awt.BorderLayout;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.text.html.HTMLEditorKit;

public final class NewsPanel
extends JPanel{
    private final HTMLEditorKit HTML_KIT = new HTMLEditorKit(){{
        this.setStyleSheet(Resources.makeStyleSheet("news"));
    }};
    private final JEditorPane NEWS_PANE = new JEditorPane("text/html", ""){{
        this.setOpaque(false);
        this.setEditable(false);
        this.setEditorKit(HTML_KIT);
    }};

    public NewsPanel(){
        super(new BorderLayout());
        this.setOpaque(false);
        this.add(new JScrollPane(this.NEWS_PANE), BorderLayout.CENTER);
        this.reload();
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