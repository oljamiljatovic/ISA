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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import rs.ac.uns.ftn.informatika.jpa.domain.users.Employee;

@Entity
@Table(name="orderr")
@Inheritance(strategy=JOINED)
public class Order {
	@Id
	@GeneratedValue
	@Column(name="order_id", unique=true, nullable=false)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="waiter")
	private Employee waiter;
	
	@ManyToOne
	@JoinColumn(name="tablee")
	private Tablee tablee;
	
	@ManyToOne
	@JoinColumn(name="reservation")
	private Reservation reservation;
	
	@ManyToOne
	@JoinColumn(name="restaurant")
	private Restaurant restaurant;

	//stanja: kreirana, preuzeo_sanker, gotovo_pice, kraj
	@Column(name="barman_state",nullable = false)
	private String barman_state;
	
	//stanja: kreirana, preuzeo_kuvar, gotovo_jelo, kraj
	@Column(name="cook_state",nullable = false)
	private String cook_state;
	
	@Column(name="time_of_order",nullable = false)
	private Date timeOfOrder;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Employee getWaiter() {
		return waiter;
	}

	public void setWaiter(Employee waiter) {
		this.waiter = waiter;
	}

	public Tablee getTablee() {
		return tablee;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public void setTablee(Tablee tablee) {
		this.tablee = tablee;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
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

	public Date getTimeOfOrder() {
		return timeOfOrder;
	}

	public void setTimeOfOrder(Date timeOfOrder) {
		this.timeOfOrder = timeOfOrder;
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
