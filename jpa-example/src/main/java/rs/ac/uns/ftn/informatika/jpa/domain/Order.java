package rs.ac.uns.ftn.informatika.jpa.domain;

import static javax.persistence.InheritanceType.JOINED;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="orderr")
@Inheritance(strategy=JOINED)
public class Order {
	@Id
	@GeneratedValue
	@Column(name="id", unique=true, nullable=false)
	private Long id;
	
	@Column(name="waiter_id",nullable = false)
	private Long waiter_id;
	
	@Column(name="table_id",nullable = false)
	private Long table_id;
	
	@Column(name="restaurant",nullable = false)
	private Long restaurant;
	
	private ArrayList<String> drinks = new ArrayList<String>();
	private ArrayList<String> meals = new ArrayList<String>();
	//private ArrayList<Drink> drinks = new ArrayList<Drink>();

	public Order(){}
	
	public Order(Long waiter_id,Long table_id,Long restaurant,ArrayList<String> drinks,ArrayList<String> meals){
		this.waiter_id = waiter_id;
		this.table_id = table_id;
		this.restaurant = restaurant;
		setDrinks(drinks);
		setMeals(meals);
	}
	
	/*public Order(String username,String desk,ArrayList<Drink> drinks,ArrayList<String> meals){
		this.username = username;
		this.desk = desk;
		setDrinks(drinks);
		setMeals(meals);
	}*/
	
	public Order(Long waiter_id,Long table_id,Long restaurant){
		this.waiter_id = waiter_id;
		this.table_id = table_id;
		this.restaurant = restaurant;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToMany(fetch = FetchType.LAZY)
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
	@ManyToMany(fetch = FetchType.LAZY)
	public ArrayList<String> getMeals() {
		return meals;
	}

	public void setMeals(ArrayList<String> meals) {
		this.meals = meals;
	}/*
	@ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(name="drink_order", joinColumns=@JoinColumn(name="order_id"),
	inverseJoinColumns=@JoinColumn(name="drink_id"))
	public ArrayList<Drink> getDrinks() {
		return drinks;
	}

	public void setDrinks(ArrayList<Drink> drinks) {
		this.drinks = drinks;
	}*/

	public Long getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Long restaurant) {
		this.restaurant = restaurant;
	}

	
}
