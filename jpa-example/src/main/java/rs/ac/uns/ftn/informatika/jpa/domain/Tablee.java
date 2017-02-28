package rs.ac.uns.ftn.informatika.jpa.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Tablee implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue
	@Column(nullable = false, unique=true)
	private Long id;
	@ManyToOne
	@JoinColumn(name="reon")
	private Reon reon;
	@ManyToOne
	@JoinColumn(name="restaurant")
	private Restaurant restaurant;
	@Column(nullable = false)
	private boolean exist;
	
	public Tablee(){
		
	}
	
	public Tablee(Reon reon, Restaurant restaurant, boolean exist) {
		super();
		this.reon = reon;
		this.restaurant = restaurant;
		this.exist = exist;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Reon getReon() {
		return reon;
	}

	public void setReon(Reon reon) {
		this.reon = reon;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public boolean isExist() {
		return exist;
	}

	public void setExist(boolean exist) {
		this.exist = exist;
	}
	
	

}
