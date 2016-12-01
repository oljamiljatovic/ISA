package rs.ac.uns.ftn.informatika.jpa.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


public class Restaurant implements Serializable{

	
	private Long id;
	
	
	private String name;
	
	private Menu menu;
	
	private DrinkList drinkList;
	
	
	public Restaurant(){
		
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


	public Menu getMenu() {
		return menu;
	}


	public void setMenu(Menu menu) {
		this.menu = menu;
	}


	public DrinkList getDrinkList() {
		return drinkList;
	}


	public void setDrinkList(DrinkList drinkList) {
		this.drinkList = drinkList;
	}
	
	
}
