package domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;



@Entity
public class Hotel implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(optional = false)
	private City city;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String address;

	@Column(nullable = false)
	private String zip;

	/*@OneToMany(fetch = FetchType.LAZY, mappedBy = "hotel")
	private Set<Review> reviews;
*/
	protected Hotel() {
	}

	public Hotel(City city, String name) {
		this.city = city;
		this.name = name;
	}

	public City getCity() {
		return this.city;
	}

	public String getName() {
		return this.name;
	}

	public String getAddress() {
		return this.address;
	}

	public String getZip() {
		return this.zip;
	}
}
