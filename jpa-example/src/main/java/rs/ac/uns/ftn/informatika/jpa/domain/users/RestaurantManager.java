package rs.ac.uns.ftn.informatika.jpa.domain.users;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class RestaurantManager implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String surname;
	@Column(nullable = false)
	private String address;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String contact;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private String restaurant;
	
	public RestaurantManager(){
		
	}
	
	public RestaurantManager(String name, String surname, String address, String email, String contact,
			String password, String restaurant) {
		super();
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.email = email;
		this.contact = contact;
		this.password = password;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRestaurant() {
		return restaurant;
	}

	@OneToOne
	public void setRestaurant(String restaurant) {
		this.restaurant = restaurant;
	}
	
}
