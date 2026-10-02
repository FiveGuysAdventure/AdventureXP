package com.adventurexp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "activity_tag")
public class ActivityTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;

    private String tagName;

    public ActivityTag(String tagName) {
        this.tagName = tagName;
    }
    public ActivityTag(){}

    public Long getTagId() {
        return tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
