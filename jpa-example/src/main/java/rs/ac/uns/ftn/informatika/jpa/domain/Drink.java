package rs.ac.uns.ftn.informatika.jpa.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.util.Assert;

@Entity
public class Drink implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private int price;
	
	//@ManyToOne(optional = false)
	//private DrinkList drinkList;
	
	public Drink(){
		
	}
	
	public Drink(/*DrinkList drinkList,*/ String name, int price) {
		super();
		//Assert.notNull(drinkList, "Lista pica ne sme biti null");
		//this.drinkList = drinkList;
		this.name = name;
		this.price = price;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
