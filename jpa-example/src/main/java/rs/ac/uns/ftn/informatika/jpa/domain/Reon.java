package rs.ac.uns.ftn.informatika.jpa.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Reon implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue
	private Long id;
	
	@Column(nullable = false, unique=true)
	private String name;
	@Column(nullable = false, unique=true)
	private String location;
	@Column(nullable = false)
	private int numberTable;
	@ManyToOne
	@JoinColumn(name="restaurant")
	private Restaurant restaurant;
	
	public Reon(){
		
	}
	
	public Reon(String name, String location, Restaurant rest, int numberTable) {
		super();
		this.name = name;
		this.location = location;
		this.restaurant = rest;
		this.numberTable = numberTable;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getNumberTable() {
		return numberTable;
	}

	public void setNumberTable(int numberTable) {
		this.numberTable = numberTable;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
	
}
