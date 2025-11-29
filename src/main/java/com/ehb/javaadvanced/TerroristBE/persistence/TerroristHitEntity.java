package com.ehb.javaadvanced.TerroristBE.persistence;

import jakarta.persistence.*;

import java.util.Date;


/***********************************
 * Persistent Entity of the TerroristHit - Mapping to database table
 */

@Entity
@Table(name = "terroristhits")
public class TerroristHitEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    private Date hit_date;
    private String hit_criteria;
    private String hit_content;

    public void setId(Long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public void setHit_date(Date hit_date) {
        this.hit_date = hit_date;
    }

    public Date getHit_date() {
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
