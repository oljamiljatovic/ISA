package rs.ac.uns.ftn.informatika.jpa.domain.users;

import static javax.persistence.InheritanceType.JOINED;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;

import rs.ac.uns.ftn.informatika.jpa.domain.User;

@Entity
@Table(name="guest")
@Inheritance(strategy=JOINED)
public class Guest extends User {


	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String surname;
	
	
	
	public Guest(){
		
	}

	
	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Guest(String name, String surname) {
		super();
		this.name = name;
		this.surname = surname;
	}


	
	
	


	
}
