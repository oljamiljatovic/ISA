package rs.ac.uns.ftn.informatika.jpa.domain;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
public class Restaurant implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue
	@Column(name="restaurant_id", unique=true, nullable=false)
	private Long id;
	@Column(nullable=false)
	private String name;
	@Column(nullable=false)
	private String type;
	@Column( nullable=true)
	private String address;
	@Column(nullable=true)
	private String contact;
	private ArrayList<String> drinks = new ArrayList<String>();
	private ArrayList<String> meals = new ArrayList<String>();
	
	public Restaurant(){
		
	}
	
	public Restaurant(String name, String type, String address, String contact, ArrayList<String> drinks, 
			ArrayList<String> meals){
		this.name = name;
		this.type = type;
		this.address = address;
		this.contact = contact;
		setDrinks(drinks);
		setMeals(meals);	
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
	
	public ArrayList<String> getDrinks() {
		return drinks;
	}
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "restaurant_drinks", joinColumns = {
			@JoinColumn(name = "restaurant_id", nullable = false, updatable = false) },
			inverseJoinColumns = { @JoinColumn(name = "drink_id", nullable = false, updatable = false) })
	public void setDrinks(ArrayList<String> drinks) {
		this.drinks = drinks;
	}

	public ArrayList<String> getMeals() {
		return meals;
	}
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "restaurant_meals", joinColumns = {
			@JoinColumn(name = "restaurant_id", nullable = false, updatable = false) },
			inverseJoinColumns = { @JoinColumn(name = "meal_id", nullable = false, updatable = false) })
	public void setMeals(ArrayList<String> meals) {
		this.meals = meals;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
