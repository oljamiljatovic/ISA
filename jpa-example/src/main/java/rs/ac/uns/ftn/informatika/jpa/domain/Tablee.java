package rs.ac.uns.ftn.informatika.jpa.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
	
	@Column
	private String reon;
	@Column
	private String restaurant;
	
	public Tablee(){
		
	}
	
	public Tablee(String reon, String restaurant) {
		super();
		this.reon = reon;
		this.restaurant = restaurant;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReon() {
		return reon;
	}

	public void setReon(String reon) {
		this.reon = reon;
	}

	public String getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(String restaurant) {
		this.restaurant = restaurant;
	}

	

}
