package rs.ac.uns.ftn.informatika.jpa.domain;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
	private Long foodOrDrink;
	@Column(nullable = true)
	private String flag;
	@Column(nullable = false)
	private int amount;
	@ManyToOne
	@JoinColumn(name="restaurant")
	private Restaurant restaurant;

	
	public Offer() {
		
	}


	public Offer(String endDate, Long foodOrDrink, String flag, Restaurant restaurant, int amount) {
		super();
		this.endDate = endDate;
		this.restaurant = restaurant;
		this.flag = flag;
		this.foodOrDrink = foodOrDrink;
		this.amount = amount;
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


	public Restaurant getRestaurant() {
		return restaurant;
	}
	
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}


	public Long getFoodOrDrink() {
		return foodOrDrink;
	}


	public void setFoodOrDrink(Long foodOrDrink) {
		this.foodOrDrink = foodOrDrink;
	}


	public String getFlag() {
		return flag;
	}


	public void setFlag(String flag) {
		this.flag = flag;
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}

}
