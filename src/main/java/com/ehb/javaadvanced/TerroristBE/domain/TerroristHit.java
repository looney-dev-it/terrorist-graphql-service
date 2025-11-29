package com.ehb.javaadvanced.TerroristBE.domain;

import java.util.Date;

public class TerroristHit {
    public long id;
    public long user_id;
    public Date hit_date;
    public String hit_reason;
    public String hit_content;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setHit_date(Date hit_date) {
        this.hit_date = hit_date;
    }

    public Date getHit_date() {
        return hit_date;
    }

    public void setHit_reason(String hit_reason) {
        this.hit_reason = hit_reason;
    }

    public String getHit_reason() {
        return hit_reason;
    }

    public void setHit_content(String hit_content) {
        this.hit_content = hit_content;
    }

    public String getHit_content() {
        return hit_content;
    }
}
