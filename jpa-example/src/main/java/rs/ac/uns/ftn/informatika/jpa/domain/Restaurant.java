package rs.ac.uns.ftn.informatika.jpa.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Restaurant implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue
	@Column(name="id", unique=true, nullable=false)
	private Long id;
	
	@Column(name="name", unique=true, nullable=false)
	private String name;
	//private Menu menu;
	//@Column(name="drink_id")
	//private DrinkList drinkList;
	//@OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
	//private Set<Drink> drinks;
	
	public Restaurant(){
		
	}
	
	public Restaurant(String name){
		this.name = name;
		//this.drink = drink;
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


	/*public Menu getMenu() {
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
	}*/
	
	
}
