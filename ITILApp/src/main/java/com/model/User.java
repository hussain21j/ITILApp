package com.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_um")
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "USER_ID")
	private String username; //this is actually user id, due to spring security its name has to be username 
	@Column(name = "USER_FULL_NAME")
	private String userFullName;
	@Column(name = "USER_PASSWORD")
	private String userPassword;
	@Column(name = "USER_STATUS")
	private String userStatus;
	@ManyToMany
	@JoinTable(name = "TBL_USER_ROLE_MAPPING", 
		joinColumns = @JoinColumn(name = "USER_ID"),
		inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
	private List<UserRole> roles;
	
	//defining the constructor
	public User(){}

	public User(String username, String userFullName, String userPassword,
			String userStatus, List<UserRole> roles) {
		super();
		this.username = username;
		this.userFullName = userFullName;
		this.userPassword = userPassword;
		this.userStatus = userStatus;
		this.roles = roles;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserFullName() {
		return userFullName;
	}

	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public List<UserRole> getRoles() {
		return roles;
	}

	public void setRoles(List<UserRole> roles) {
		this.roles = roles;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
