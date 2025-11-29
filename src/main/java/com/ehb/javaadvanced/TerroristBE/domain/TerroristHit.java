package com.ehb.javaadvanced.TerroristBE.domain;

// TerroristHit Object returned to GraphQL requests
public class TerroristHit {
    public long id;
    public User user;
    public String hit_date;
    public String hit_criteria;
    public String hit_content;


    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setHit_date(String hit_date) {
        this.hit_date = hit_date;
    }

    public String getHit_date() {
        return hit_date;
    }

    public void setHit_criteria(String hit_criteria) {
        this.hit_criteria = hit_criteria;
    }

    public String getHit_criteria() {
        return hit_criteria;
    }

    public void setHit_content(String hit_content) {
        this.hit_content = hit_content;
    }

    public String getHit_content() {
        return hit_content;
    }
}
