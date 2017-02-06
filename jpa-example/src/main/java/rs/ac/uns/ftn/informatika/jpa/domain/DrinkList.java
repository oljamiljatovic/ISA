/*package rs.ac.uns.ftn.informatika.jpa.domain;

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

import antlr.collections.List;

@Entity
public class DrinkList implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue
	@Column(name="drinks_id", unique=true, nullable=false)
	private Long id;
	private ArrayList drinks;
	
	public DrinkList(ArrayList drinks) {
		this.drinks = drinks;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "restaurant_drinks", joinColumns = {
			@JoinColumn(name = "drinks_id", nullable = false, updatable = false) },
			inverseJoinColumns = { @JoinColumn(name = "drink_id",
					nullable = false, updatable = false) })
	public ArrayList getDrinks() {
		return drinks;
	}

	public void setDrinks(ArrayList drinks) {
		this.drinks = drinks;
	}
	
}*/
