package rs.ac.uns.ftn.informatika.jpa.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import rs.ac.uns.ftn.informatika.jpa.domain.users.Employee;

@Entity
public class AssignReon implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue
	private Long id;
	@OneToOne
	@JoinColumn(name="waiter")
	private Employee waiter;
	@OneToOne
	@JoinColumn(name="reon")
	private Reon reon;
	@ManyToOne
	@JoinColumn(name="restaurant")
	private Restaurant restaurant;
	
	public AssignReon() {
		
	}

	public AssignReon(Long id, Employee waiter, Reon reon, Restaurant restaurant) {
		super();
		this.id = id;
		this.waiter = waiter;
		this.reon = reon;
		this.restaurant = restaurant;
	}



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

	public Reon getReon() {
		return reon;
	}

	public void setReon(Reon reon) {
		this.reon = reon;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

}
