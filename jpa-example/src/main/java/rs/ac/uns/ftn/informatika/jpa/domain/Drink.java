package rs.ac.uns.ftn.informatika.jpa.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Drink implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue
	@Column(name="drink_id", unique=true, nullable=false)
	private Long id;
	@Column(nullable = false, unique=true)
	private String name;
	@Column(nullable = false)
	private String description;
	@Column(nullable = false)
	private float price;
	@ManyToOne
	@JoinColumn(name="restaurant")
	private Restaurant restaurant;
	
	public Drink(){
		
	}
	
	public Drink(String name, String description, float price, Restaurant rest) {
		super();
		this.name = name;
		this.price = price;
		this.price = price;
		this.restaurant = rest;
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

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	

}
