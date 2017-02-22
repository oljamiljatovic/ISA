package rs.ac.uns.ftn.informatika.jpa.domain;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

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
	@Column(nullable = true)
	private ArrayList<Long> drinks;
	@Column(nullable = true)
	private ArrayList<Long> meals;
	@Column(nullable = false)
	private Long restaurant;

	
	public Offer() {
		
	}


	public Offer(String endDate, ArrayList<Long> drinks, ArrayList<Long> meals, Long restaurant) {
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

	
	public ArrayList<Long> getDrinks() {
		return drinks;
	}


	@ManyToMany
	@JoinColumn(name="offer_drink_id", referencedColumnName="drink_id", nullable=true)
	public void setDrinks(ArrayList<Long> drinks) {
		this.drinks = drinks;
	}
	
	public ArrayList<Long> getMeals() {
		return meals;
	}


	public void setMeals(ArrayList<Long> meals) {
		this.meals = meals;
	}


	public Long getRestaurant() {
		return restaurant;
	}


	public void setRestaurant(Long restaurant) {
		this.restaurant = restaurant;
	}

}
