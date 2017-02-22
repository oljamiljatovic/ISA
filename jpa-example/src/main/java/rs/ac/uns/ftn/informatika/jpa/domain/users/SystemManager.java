package rs.ac.uns.ftn.informatika.jpa.domain.users;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

import rs.ac.uns.ftn.informatika.jpa.domain.User;

@Entity
public class SystemManager extends User implements Serializable{
	
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String surname;
	@Column(nullable = false)
	private String address;
	@Column(nullable = false)
	private String contact;
	
	public SystemManager() {
		
	}
	
	public SystemManager(String name, String surname, String role, String address, String email, String contact,
			String password, String accept) {
		super();
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.email = email;
		this.contact = contact;
		this.password = password;
		this.accept = accept;
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
	
	
	
}
