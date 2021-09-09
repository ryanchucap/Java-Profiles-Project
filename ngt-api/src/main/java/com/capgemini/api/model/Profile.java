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
    private String phone_number;
    private String city;
    private String state;
    private String track;
    private String account;
    private int project_code;
//    @Temporal(TemporalType.DATE)
//    private Date start_date;
    private String start_date;
    
    public Profile() {
    	super();
    }
    
    public Profile(String name) {
    	this.name = name;
    }

    public Profile(String name, String email, String phone_number, String city, String state,
    		String track, String account, int project_code, String start_date) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getEmail() {
    	return email;
    }
    
    public String getPhone_number() {
    	return phone_number;
    }
    
   public String getTrack() {
    	return track;
    }
    
    public String getAccount() {
    	return account;
    }
    
    public String getCity() {
    	return city;
    }
    
    public String getState() {
    	return state;
    }
    
    public int getProject_code() {
    	return project_code;
    }
    
    public String getStart_date() {
    	return start_date;
    }


}
