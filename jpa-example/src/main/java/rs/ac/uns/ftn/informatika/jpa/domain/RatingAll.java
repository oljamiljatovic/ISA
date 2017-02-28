package rs.ac.uns.ftn.informatika.jpa.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import rs.ac.uns.ftn.informatika.jpa.domain.users.Employee;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Guest;

@Entity
@Table(name="rating_all")
public class RatingAll{
	@Id
    @GeneratedValue
	@Column(name="id", unique=true, nullable=false)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="guest")
	private Guest guest;
	
	@ManyToOne
	@JoinColumn(name="reservation")
	private Reservation reservation;
	
	@ManyToOne
	@JoinColumn(name="restaurant")
	private Restaurant restaurant;
	
	@ManyToOne
	@JoinColumn(name="waiter")
	private Employee waiter;
	
	@ManyToMany(cascade=CascadeType.ALL) 
	@JoinTable(name="rating_all_meals", joinColumns=@JoinColumn(name="rating_id"),
	inverseJoinColumns=@JoinColumn(name="meal_id"))
	private List<Meal> meals;
	
	@Column(name="restaurant_rating",nullable = false)
	private int restaurantRating;
	
	@Column(name="service_rating",nullable = false)
	private int serviceRating;
	
	@Column(name="meal_rating",nullable = false)
	private int mealRating;
	
	public RatingAll(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public int getRestaurantRating() {
		return restaurantRating;
	}

	public void setRestaurantRating(int restaurantRating) {
		this.restaurantRating = restaurantRating;
	}

	public int getServiceRating() {
		return serviceRating;
	}

	public void setServiceRating(int serviceRating) {
		this.serviceRating = serviceRating;
	}

	public int getMealRating() {
		return mealRating;
	}

	public void setMealRating(int mealRating) {
		this.mealRating = mealRating;
	}

	public Employee getWaiter() {
		return waiter;
	}

	public void setWaiter(Employee waiter) {
		this.waiter = waiter;
	}

	public List<Meal> getMeals() {
		return meals;
	}

	public void setMeals(List<Meal> meals) {
		this.meals = meals;
	}
	
	
	
	
}
