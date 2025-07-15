package net.engineeringdigest.journalApp.entities;
//POJO: Plain Old Java Object
public class JournalEntry {
    Long id;
    String title;
    String content;

    public JournalEntry(Long id, String title, String content) {
        this.id=id;
        this.title=title;
        this.content=content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
