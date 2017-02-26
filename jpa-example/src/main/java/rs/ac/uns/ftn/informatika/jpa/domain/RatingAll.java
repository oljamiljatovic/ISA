package rs.ac.uns.ftn.informatika.jpa.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="rating_all")
public class RatingAll{
	@Id
    @GeneratedValue
	@Column(name="id", unique=true, nullable=false)
	private Long id;
	
	@Column(name="guest_id",nullable = false)
	private Long guestId;
	
	@Column(name="reservation_id",nullable = false)
	private Long reservationId;
	
	@Column(name="restaurant_id",nullable = false)
	private Long restaurantId;
	
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

	public Long getGuestId() {
		return guestId;
	}

	public void setGuestId(Long guestId) {
		this.guestId = guestId;
	}

	public Long getReservationId() {
		return reservationId;
	}

	public Long getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}

	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
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
	
	
}
