package rs.ac.uns.ftn.informatika.jpa.domain;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Reon implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue
	private Long id;
	
	@Column(nullable = false, unique=true)
	private String name;
	@Column(nullable = false, unique=true)
	private String location;
	@Column(nullable = false)
	private int numberTable;
	@Column(nullable = true)
	private ArrayList<String> tables = new ArrayList<String>();
	@Column(nullable = false)
	private String restaurant;
	
	public Reon(){
		
	}
	
	public Reon(String name, String location, String rest, int numberTable, ArrayList<String> tables) {
		super();
		this.name = name;
		this.location = location;
		this.restaurant = rest;
		this.numberTable = numberTable;
		this.tables = tables;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getNumberTable() {
		return numberTable;
	}

	public void setNumberTable(int numberTable) {
		this.numberTable = numberTable;
	}

	public ArrayList<String> getTables() {
		return tables;
	}

	public void setTables(ArrayList<String> tables) {
		this.tables = tables;
	}

	public String getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(String restaurant) {
		this.restaurant = restaurant;
	}
	
	
}
