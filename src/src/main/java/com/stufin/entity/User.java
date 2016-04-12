package com.stufin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.stufin.entity.DomainObject;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "USER")
public class User implements DomainObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name="userId")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long userID;
	
	private String name;
	
	private String emailId;
	
	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}

	public String getName() {
		return name;
	}

	public void setName(String firstName) {
		this.name = firstName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Override
	public String toString() {
		return "User [userID=" + userID + ", name=" + name + ", emailId=" + emailId + "]";
	}

}
