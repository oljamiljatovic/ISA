package rs.ac.uns.ftn.informatika.jpa.domain;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

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
	private int price;
	
	//private ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
	
	public Drink(){
		
	}
	
	public Drink(String name, int price) {
		super();
		this.name = name;
		this.price = price;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	/*@ManyToMany(fetch = FetchType.LAZY, mappedBy = "drinks")
	public ArrayList<Restaurant> getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(ArrayList<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}*/
	
}
