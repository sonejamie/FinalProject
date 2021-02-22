package com.ccgg.beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "bank_user")
public class User implements UserDetails {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
	@SequenceGenerator(name = "SEQ", sequenceName = "BANK_USER_SEQ")
	private int id;
	@Column(name = "username", unique = true, nullable = false)
	
	private String username;
	@Column(name = "password", nullable = false)
	private String password;
	@Column(name = "firstname", nullable = false)
	private String firstname;
	@Column(name = "lastname", nullable = false)
	private String lastname;
	@Column(name = "email", nullable = false)
	private String email;
	@Column(name = "phone", nullable = false)
	private String phone;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "b_user_user_profile", joinColumns = {
			@JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "user_profile_id", referencedColumnName = "id")  })
	private List<UserProfile> profiles = new ArrayList<UserProfile>();
	
	@OneToOne
	private PrimaryAccount primaryAccount;
	
	@OneToOne
	private SavingsAccount savingsAccount; 
	
	public User(int id, String username, String password, String firstname, String lastname, String email, String phone,
			List<UserProfile> profiles, PrimaryAccount primaryAccount, SavingsAccount savingsAccount) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phone = phone;
		this.profiles = profiles;
		this.primaryAccount = primaryAccount;
		this.savingsAccount = savingsAccount;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", firstname=" + firstname
				+ ", lastname=" + lastname + ", email=" + email + ", phone=" + phone + ", profiles=" + profiles
				+ ", primaryAccount=" + primaryAccount + ", savingsAccount=" + savingsAccount + "]";
	}

	public PrimaryAccount getPrimaryAccount() {
		return primaryAccount;
	}

	public void setPrimaryAccount(PrimaryAccount primaryAccount) {
		this.primaryAccount = primaryAccount;
	}

	public SavingsAccount getSavingsAccount() {
		return savingsAccount;
	}

	public void setSavingsAccount(SavingsAccount savingsAccount) {
		this.savingsAccount = savingsAccount;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return profiles;
	}
	
	@Override//override userdetails
	public String getPassword() {
		return password;
	}
	
	@Override
	public String getUsername() {
		return username;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}


	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public List<UserProfile> getProfiles() {
		return profiles;
	}
	
	public void setProfiles(List<UserProfile> profiles) {
		this.profiles = profiles;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public User() {
		super();
	}



}
