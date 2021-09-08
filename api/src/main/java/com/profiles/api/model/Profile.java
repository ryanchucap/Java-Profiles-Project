package com.profiles.api.model;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public String name;
    private String email;
    private int phone_number;
    private String city;
    private String state;
    private String track;
    private String account;
    private int project_code;
    // @Temporal(TemporalType.DATE)
    private LocalDate start_date;

    public Profile(Long id, String name, String email, int phone_number, String city, String state, String track,
            String account, int project_code, LocalDate start_date) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone_number = phone_number;
        this.city = city;
        this.state = state;
        this.track = track;
        this.account = account;
        this.project_code = project_code;
        this.start_date = start_date;
    }

    public Long get_id() {
        return id;
    }

    public void set_id(Long id) {
        this.id = id;
    }

    public String get_name() {
        return name;
    }

    public String get_email() {
        return email;
    }

    public int get_phone_number() {
        return phone_number;
    }

    public String get_track() {
        return track;
    }

    public String get_account() {
        return account;
    }

    public String get_city() {
        return city;
    }

    public String get_state() {
        return state;
    }

    public int get_project_code() {
        return project_code;
    }

    public LocalDate get_start_date() {
        return start_date;
    }

}
