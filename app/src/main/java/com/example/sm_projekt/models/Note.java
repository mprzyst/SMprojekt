package com.example.sm_projekt.models;

import java.io.Serializable;

public class Note implements Serializable {
    private Integer id;
    private String title;
    private String content;
    private Long timestamp;

    public Note(){
        this.title = null;
        this.content = null;
    }

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Note(Integer id, String title, String content, Long timestamp) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.timestamp = timestamp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
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
