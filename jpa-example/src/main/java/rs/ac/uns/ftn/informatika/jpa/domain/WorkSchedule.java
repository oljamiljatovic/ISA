package rs.ac.uns.ftn.informatika.jpa.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import rs.ac.uns.ftn.informatika.jpa.domain.users.Employee;

@Entity
public class WorkSchedule implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue
	@Column(unique=true, nullable=false)
	private Long id;
	@Column(nullable = false)
	public String dateStart;
	@Column(nullable = false)
	public String dateEnd;
	@Column(nullable = false)
	public String shift;
	@OneToOne
	@JoinColumn(name="worker")
	private Employee worker;
	@ManyToOne
	@JoinColumn(name="restaurant")
	private Restaurant restaurant;
	
	public WorkSchedule() {
	}
	
	public WorkSchedule(String dateStart, String dateEnd, String shift, 
			Restaurant rest, Employee worker) {
		super();
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.shift = shift;
		this.worker = worker;
		this.restaurant = rest;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDateStart() {
		return dateStart;
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	public String getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}

	public String getShift() {
		return shift;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}

	public Employee getWorker() {
		return worker;
	}

	public void setWorker(Employee worker) {
		this.worker = worker;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

}
