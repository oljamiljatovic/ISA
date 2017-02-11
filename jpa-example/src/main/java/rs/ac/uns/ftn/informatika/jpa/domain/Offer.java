package rs.ac.uns.ftn.informatika.jpa.domain;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Offer implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue
	@Column(unique=true, nullable=false)
	private Long id;
	@Column(nullable = false)
	private String endDate;
	@Column(nullable = false)
	private ArrayList<String> drinks;
	@Column(nullable = false)
	private ArrayList<String> meals;
	@Column(nullable = false)
	private Long restaurant;

	
	public Offer() {
		
	}


	public Offer(String endDate, ArrayList<String> drinks, ArrayList<String> meals, Long restaurant) {
		super();
		this.endDate = endDate;
		this.drinks = drinks;
		this.meals = meals;
		this.restaurant = restaurant;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getEndDate() {
		return endDate;
	}


	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}


	public ArrayList<String> getDrinks() {
		return drinks;
	}


	public void setDrinks(ArrayList<String> drinks) {
		this.drinks = drinks;
	}


	public ArrayList<String> getMeals() {
		return meals;
	}


	public void setMeals(ArrayList<String> meals) {
		this.meals = meals;
	}


	public Long getRestaurant() {
		return restaurant;
	}


	public void setRestaurant(Long restaurant) {
		this.restaurant = restaurant;
	}

}
