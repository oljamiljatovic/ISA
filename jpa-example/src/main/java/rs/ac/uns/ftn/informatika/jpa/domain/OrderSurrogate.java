package rs.ac.uns.ftn.informatika.jpa.domain;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class OrderSurrogate {

	private Long waiter_id;
	
	private Long table_id;
	
	private Long restaurant;
	
	private ArrayList<String> drinks = new ArrayList<String>();
	private ArrayList<String> meals = new ArrayList<String>();

	public OrderSurrogate(){}
	

	public ArrayList<String> getDrinks() {
		return drinks;
	}
	public Long getWaiter_id() {
		return waiter_id;
	}

	public void setWaiter_id(Long waiter_id) {
		this.waiter_id = waiter_id;
	}

	public Long getTable_id() {
		return table_id;
	}

	public void setTable_id(Long table_id) {
		this.table_id = table_id;
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
