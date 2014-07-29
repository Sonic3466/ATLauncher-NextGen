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
    private final HTMLEditorKit NEWS_KIT = new HTMLEditorKit();
    private final JEditorPane NEWS_PANE = new JEditorPane("text/html", "");

    public NewsPanel(){
        super(new BorderLayout());

        this.NEWS_KIT.setStyleSheet(Resources.makeStyleSheet("news"));

        this.NEWS_PANE.setEditable(false);
        this.NEWS_PANE.setEditorKit(this.NEWS_KIT);
        this.reload();

        this.add(new JScrollPane(this.NEWS_PANE), BorderLayout.CENTER);
    }

    public void reload(){
        this.NEWS_PANE.setText("");
        this.NEWS_PANE.setText(this.newsHTML());
        this.NEWS_PANE.setCaretPosition(0);
    }

    private String newsHTML(){
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