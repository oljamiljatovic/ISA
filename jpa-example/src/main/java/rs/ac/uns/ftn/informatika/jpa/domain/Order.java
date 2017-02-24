package rs.ac.uns.ftn.informatika.jpa.domain;

import static javax.persistence.InheritanceType.JOINED;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
	@Column(name="order_id", unique=true, nullable=false)
	private Long id;
	
	@Column(name="waiter_id",nullable = false)
	private Long waiter_id;
	
	@Column(name="table_id",nullable = false)
	private Long table_id;
	
	@Column(name="restaurant",nullable = false)
	private Long restaurant;
	
	//stanja: kreirana, preuzeo_sanker, gotovo_pice, kraj
	@Column(name="barman_state",nullable = false)
	private String barman_state;
	
	//stanja: kreirana, preuzeo_kuvar, gotovo_jelo, kraj
	@Column(name="cook_state",nullable = false)
	private String cook_state;
	
	@Column(name="time_of_order",nullable = false)
	private Date timeOfOrder;
	
	/*private ArrayList<Meal> meals = new ArrayList<Meal>();
	private ArrayList<Drink> drinks = new ArrayList<Drink>();*/

	@ManyToMany(cascade=CascadeType.ALL) 
	@JoinTable(name="meal_order", joinColumns=@JoinColumn(name="order_id"),
	inverseJoinColumns=@JoinColumn(name="meal_id"))
	public List<Meal> meals;
	
	@ManyToMany(cascade=CascadeType.ALL) 
	@JoinTable(name="drink_order", joinColumns=@JoinColumn(name="order_id"),
	inverseJoinColumns=@JoinColumn(name="drink_id"))
	public List<Drink> drinks;
	//private ArrayList<String> drinks = new ArrayList<String>();
	//private ArrayList<String> meals = new ArrayList<String>();

	public Order(){}
	
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

	public Date getTimeOfOrder() {
		return timeOfOrder;
	}

	public void setTimeOfOrder(Date timeOfOrder) {
		this.timeOfOrder = timeOfOrder;
	}

	/*@ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.MERGE)
	@JoinTable(name="meal_order", joinColumns=@JoinColumn(name="order_id"),
	inverseJoinColumns=@JoinColumn(name="meal_id"))
	public ArrayList<Meal> getMeals() {
		return meals;
	}
	
	public void setMeals(ArrayList<Meal> meals) {
		this.meals = meals;
	}
	
	@ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.MERGE)
	@JoinTable(name="drink_order", joinColumns=@JoinColumn(name="order_id"),
	inverseJoinColumns=@JoinColumn(name="drink_id"))
	public ArrayList<Drink> getDrinks() {
		return drinks;
	}

	public void setDrinks(ArrayList<Drink> drinks) {
		this.drinks = drinks;
	}*/
	/*public ArrayList<String> getDrinks() {
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
	}*/

	public Long getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Long restaurant) {
		this.restaurant = restaurant;
	}

	public String getBarman_state() {
		return barman_state;
	}

	public void setBarman_state(String barman_state) {
		this.barman_state = barman_state;
	}

	public String getCook_state() {
		return cook_state;
	}

	public void setCook_state(String cook_state) {
		this.cook_state = cook_state;
	}

	public List<Meal> getMeals() {
		return meals;
	}

	public void setMeals(List<Meal> meals) {
		this.meals = meals;
	}

	public List<Drink> getDrinks() {
		return drinks;
	}

	public void setDrinks(List<Drink> drinks) {
		this.drinks = drinks;
	}

	
	
	
}
