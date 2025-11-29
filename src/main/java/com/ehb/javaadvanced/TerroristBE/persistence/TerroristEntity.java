package com.ehb.javaadvanced.TerroristBE.persistence;

import jakarta.persistence.*;

/***********************************
 * Persistent Entity of the Terrorist - Mapping to database table
 */

@Entity
@Table(name = "terrorists")
public class TerroristEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nrn;
    private String lastname;
    private String firstname;
    private String middlename;
    private String wholename;
    private String gender;
    private String birthdate;
    private String birthplace;
    private String birthcountry;
    private String function;
    private String remark;
    private String type;
    private String regulation;
    private String publicationdate;
    private String embargo;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getEmbargo() {
        return embargo;
    }

    public void setEmbargo(String embargo) {
        this.embargo = embargo;
    }

    public String getNrn() {
        return nrn;
    }

    public void setNrn(String nrn) {
        this.nrn = nrn;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getWholename() {
        return wholename;
    }

    public void setWholename(String wholename) {
        this.wholename = wholename;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public String getBirthcountry() {
        return birthcountry;
    }

    public void setBirthcountry(String birthcountry) {
        this.birthcountry = birthcountry;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRegulation() {
        return regulation;
    }

    public void setRegulation(String regulation) {
        this.regulation = regulation;
    }

    public String getPublicationdate() {
        return publicationdate;
    }

    public void setPublicationdate(String publicationdate) {
        this.publicationdate = publicationdate;
    }

    @Override
    public String toString() {
        return "TerroristEntity{" +
                "type='" + type + '\'' +
                ", id=" + id +
                ", nrn='" + nrn + '\'' +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", middlename='" + middlename + '\'' +
                ", wholename='" + wholename + '\'' +
                ", gender='" + gender + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", birthplace='" + birthplace + '\'' +
                ", birthcountry='" + birthcountry + '\'' +
                ", function='" + function + '\'' +
                ", remark='" + remark + '\'' +
                ", regulation='" + regulation + '\'' +
                ", publicationdate='" + publicationdate + '\'' +
                ", embargo='" + embargo + '\'' +
                '}';
    }
}
