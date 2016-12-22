package rs.ac.uns.ftn.informatika.jpa.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DrinkList implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	//@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	//@OneToMany(fetch = FetchType.LAZY, mappedBy = "drinkList")
	//private Set<Drink> drinks;
	
	public DrinkList() {
		
	}
	
	public DrinkList(Long id, String name){
		this.name = name;
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
