package com.profiles.api.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
public class ProfileItems {

    // Set up Data Members that correspond to columns in the Greeting table
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

    public ProfileItems() {
    }

    @DynamoDbPartitionKey
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone_number() {
        return this.phone_number;
    }

    public void setPhone_number(int phone_number) {
        this.phone_number = phone_number;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTrack() {
        return this.track;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public String getAccount() {
        return this.account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getProject_code() {
        return this.project_code;
    }

    public void setProject_code(int project_code) {
        this.project_code = project_code;
    }

    public LocalDate getStart_date() {
        return this.start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

}