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
	@Column(nullable=false)
	private String name;
	@Column( nullable=false)
	private String address;
	@Column(nullable=false)
	private String contact;
	//private Menu menu;
	//@Column(name="drink_id")
	//private DrinkList drinkList;
	//@OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
	//private Set<Drink> drinks;
	
	public Restaurant(){
		
	}
	
	public Restaurant(String name, String address, String contact){
		this.name = name;
		this.address = address;
		this.contact = contact;
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
	
}
