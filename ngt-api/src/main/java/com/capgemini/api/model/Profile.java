package com.capgemini.api.model;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String name;
    private String email;
    private long phone_number;
    private String city;
    private String state;
    private String track;
    private String account;
    private int project_code;
//    @Temporal(TemporalType.DATE)
//    private Date start_date;
    private Date start_date;
    
    public Profile() {
    	super();
    }
    
    public Profile(String name) {
    	this.name = name;
    }

    public Profile(String name, String email, long phone_number, String city, String state,
    		String track, String account, int project_code, Date start_date) {
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
    
    public String getName() {
    	return name;
    }
    
    public void setName(String name) {
    	this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getEmail() {
    	return email;
    }
    
    public void setEmail(String email) {
    	this.email = email;
    }
    
    public long getPhone_number() {
    	return phone_number;
    }
    
    public void setPhone_number(long phone_number) {
    	this.phone_number = phone_number;
    }
    
   public String getTrack() {
    	return track;
    }
   
   public void setTrack(String track) {
	   this.track = track;
   }
    
    public String getAccount() {
    	return account;
    }
    
    public void setAccount(String account) {
    	this.account = account;
    }
    
    public String getCity() {
    	return city;
    }
    
    public void setCity(String city) {
    	this.city = city;
    }
    
    public String getState() {
    	return state;
    }
    
    public void setState(String state) {
    	this.state = state;
    }
    
    public int getProject_code() {
    	return project_code;
    }
    
    public void setProject_code(int project_code) {
    	this.project_code = project_code;
    }
    
    public Date getStart_date() {
    	return start_date;
    }

}
