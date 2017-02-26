package rs.ac.uns.ftn.informatika.jpa.domain.users;

import static javax.persistence.InheritanceType.JOINED;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import rs.ac.uns.ftn.informatika.jpa.domain.Restaurant;
import rs.ac.uns.ftn.informatika.jpa.domain.User;

@Entity
@Inheritance(strategy=JOINED)
public class RestaurantManager extends User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String surname;
	@Column(nullable = false)
	private String address;
	@Column(nullable = false)
	private String contact;
	@ManyToOne
	@JoinColumn(name="restaurant")
	private Restaurant restaurant;
	
	
	public RestaurantManager(){
		
	}
	
	public RestaurantManager(String name, String surname, String role, String address, String email, String contact,
			String password, Restaurant restaurant, String accept) {
		super();
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.email = email;
		this.contact = contact;
		this.password = password;
		this.accept = accept;
		this.role = role;
		setRestaurant(restaurant);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Restaurant getRestaurant() {
		return restaurant;
	}
	
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
}
