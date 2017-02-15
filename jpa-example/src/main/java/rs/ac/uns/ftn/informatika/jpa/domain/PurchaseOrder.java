package rs.ac.uns.ftn.informatika.jpa.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PurchaseOrder implements Serializable {


	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue
	@Column(unique=true, nullable=false)
	private Long id;
	@Column(nullable = false)
	private Long offer;
	@Column(nullable = false)
	private Long restaurant;
	@Column(nullable = false)
	private Long provider;
	@Column(nullable = false)
	private Long flag;
	@Column(nullable = false)
	private String timeDeliver;
	@Column(nullable = false)
	private String price;
	
	public PurchaseOrder() {
		
	}

	public PurchaseOrder(Long offer, Long restaurant, Long provider, Long flag,
			String timeDeliver, String price) {
		super();
		this.offer = offer;
		this.restaurant = restaurant;
		this.timeDeliver = timeDeliver;
		this.price = price;
		this.provider = provider;
		this.flag = flag;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOffer() {
		return offer;
	}

	public void setOffer(Long offer) {
		this.offer = offer;
	}

	public Long getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Long restaurant) {
		this.restaurant = restaurant;
	}

	public String getTimeDeliver() {
		return timeDeliver;
	}

	public void setTimeDeliver(String timeDeliver) {
		this.timeDeliver = timeDeliver;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Long getProvider() {
		return provider;
	}

	public void setProvider(Long provider) {
		this.provider = provider;
	}

	public Long getFlag() {
		return flag;
	}

	public void setFlag(Long flag) {
		this.flag = flag;
	}
	
	
	
}
