package rs.ac.uns.ftn.informatika.jpa.domain.users;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Employee extends rs.ac.uns.ftn.informatika.jpa.domain.User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String surname;
	@Column(nullable = false)
	private String dateBirth;
	@Column(nullable = false)
	private String confNumber;
	@Column(nullable = false)
	private String shoeNumber;
	@Column(nullable = false)
	private Long restaurant;
	@Column(name="first_log",nullable = false)
	private String firstLog;
	
	public Employee(){
		super();
	}

	public Employee(String name, String surname, String dateBirth, String type, String confNumber, 
			String shoeNumber, Long restaurant, String email, String accept, String password) {
		this.name = name;
		this.surname = surname;
		this.dateBirth = dateBirth;
		this.confNumber = confNumber;
		this.shoeNumber = shoeNumber;
		this.restaurant = restaurant;
		this.role = type;
		this.accept = accept;
		this.password = password;
		this.email = email;	
	}

	public String getFirstLog() {
		return firstLog;
	}

	public void setFirstLog(String firstLog) {
		this.firstLog = firstLog;
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

	public String getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(String dateBirth) {
		this.dateBirth = dateBirth;
	}

	public String getConfNumber() {
		return confNumber;
	}

	public void setConfNumber(String confNumber) {
		this.confNumber = confNumber;
	}

	public String getShoeNumber() {
		return shoeNumber;
	}

	public void setShoeNumber(String shoeNumber) {
		this.shoeNumber = shoeNumber;
	}

	public Long getRestaurant() {
		return restaurant;
	}
	
	@OneToOne
	public void setRestaurant(Long restaurant) {
		this.restaurant = restaurant;
	}
	
}
