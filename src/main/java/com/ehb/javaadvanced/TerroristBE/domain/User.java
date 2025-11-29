package com.ehb.javaadvanced.TerroristBE.domain;

// User Object returned to GraphQL requests
public class User {

    private long id;
    private String username;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
