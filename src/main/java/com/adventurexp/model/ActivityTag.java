package com.adventurexp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "activity_tag")
public class ActivityTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long tagId;

    @Column(name = "tag_name")
    private String tagName;

    public ActivityTag(String tagName) {
        this.tagName = tagName;
    }

    public ActivityTag() {
    }

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
