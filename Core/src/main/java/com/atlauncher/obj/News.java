package com.atlauncher.obj;

public final class News{
    public final String title;
    public final String link;
    public final String content;
    public final int comments;

    private News(String title, String link, String content, int comments){
        this.title = title;
        this.link = link;
        this.content = content;
        this.comments = comments;
    }

    @Override
    public String toString(){
        return "<p id=\"newsHeader\">- <a href=\"" + this.link + "\">" + this.title + "</a> (" +
                this.comments + " " + (this.comments == 1 ? "comment" : "comments") + ")</p>" +
                "<p id=\"newsBody\">" + this.content + "</p><br/>";
    }
}