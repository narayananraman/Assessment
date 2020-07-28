package com.tds.assessment.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Employees {
    private int id;
    private String firstname;
    private String lastname;
    private int badge_number;
    private String country_code;
    private int jon_title_code;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date start_date;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date leave_date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getBadge_number() {
        return badge_number;
    }

    public void setBadge_number(int badge_number) {
        this.badge_number = badge_number;
    }

    public String getCountry_Code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public int getJon_title_code() {
        return jon_title_code;
    }

    public void setJon_title_code(int jon_title_code) {
        this.jon_title_code = jon_title_code;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getLeave_date() {
        return leave_date;
    }

    public void setLeave_date(Date leave_date) {
        this.leave_date = leave_date;
    }

}
