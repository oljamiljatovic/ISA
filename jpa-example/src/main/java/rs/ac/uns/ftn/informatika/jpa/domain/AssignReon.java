package rs.ac.uns.ftn.informatika.jpa.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AssignReon implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private Long waiter_id;
	@Column(nullable = false)
	private String waiter_name;
	@Column(nullable = false)
	private Long reon_id;
	@Column(nullable = false)
	private String reon_name;
	@Column(nullable = false)
	private Long restaurant;
	
	public AssignReon() {
		
	}
	
	public AssignReon(Long waiter_id, String waiter_name, Long reon_id, String reon_name, Long restaurant) {
		super();
		this.waiter_id = waiter_id;
		this.waiter_name = waiter_name;
		this.reon_id = reon_id;
		this.reon_name = reon_name;
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

	public Long getReon_id() {
		return reon_id;
	}

	public void setReon_id(Long reon_id) {
		this.reon_id = reon_id;
	}

	public Long getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Long restaurant) {
		this.restaurant = restaurant;
	}

	public String getWaiter_name() {
		return waiter_name;
	}

	public void setWaiter_name(String waiter_name) {
		this.waiter_name = waiter_name;
	}

	public String getReon_name() {
		return reon_name;
	}

	public void setReon_name(String reon_name) {
		this.reon_name = reon_name;
	}

}
