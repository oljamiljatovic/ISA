package rs.ac.uns.ftn.informatika.jpa.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.beans.factory.annotation.Autowired;

import rs.ac.uns.ftn.informatika.jpa.domain.users.Provider;

@Entity
public class PurchaseOrder implements Serializable {


	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue
	@Column(unique=true, nullable=false)
	private Long id;
	@OneToOne
	@JoinColumn(name="offer" )
	@Cascade(value = { CascadeType.SAVE_UPDATE, CascadeType.DELETE })
	private Offer offer;
	@ManyToOne
	@JoinColumn(name="restaurant")
	private Restaurant restaurant;
	@ManyToOne
	@JoinColumn(name="provider")
	private Provider provider;
	private Long flag;
	@Column(nullable = false)
	private String timeDeliver;
	@Column(nullable = false)
	private String price;
	@Column(nullable = false)
	private boolean seen;
	
	public PurchaseOrder() {
		
	}

	public PurchaseOrder(Offer offer, Restaurant restaurant, Provider provider, Long flag,
			String timeDeliver, String price, boolean seen) {
		super();
		this.offer = offer;
		this.restaurant = restaurant;
		this.timeDeliver = timeDeliver;
		this.price = price;
		this.provider = provider;
		this.flag = flag;
		this.seen = seen;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}
	
	public void setRestaurant(Restaurant restaurant) {
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

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public Long getFlag() {
		return flag;
	}

	public void setFlag(Long flag) {
		this.flag = flag;
	}

	public boolean isSeen() {
		return seen;
	}

	public void setSeen(boolean seen) {
		this.seen = seen;
	}
	
	
	
}
