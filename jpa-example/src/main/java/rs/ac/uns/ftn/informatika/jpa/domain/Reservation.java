package rs.ac.uns.ftn.informatika.jpa.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import rs.ac.uns.ftn.informatika.jpa.domain.users.Guest;


@Entity
@Table(name="reservation")
public class Reservation {

	@Id
    @GeneratedValue
	@Column(name="id", unique=true, nullable=false)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="idGuest")
	private Guest idGuest;
	
	@ManyToOne
	@JoinColumn(name="idRestaurant")
	private Restaurant idRestaurant;
	
	private String date;
	
	private String time;
	
	private int duration;
	
	@ManyToMany(cascade=CascadeType.ALL) 
	@JoinTable(name="reservation_reserved_tables", joinColumns=@JoinColumn(name="reservation_id"),
	inverseJoinColumns=@JoinColumn(name="table_id"))
	private List<Tablee> reservedTables ;
	
	
	@ManyToMany(cascade=CascadeType.ALL) 
	@JoinTable(name="accepted_friends", joinColumns=@JoinColumn(name="reservation_id"),
	inverseJoinColumns=@JoinColumn(name="friend_id"))
	private List<Guest> acceptedFriends ;
	
	private String flag;
	
	public Reservation(){
		
	}

	public Reservation(Guest idGuest, Restaurant idRestaurant, String date, String time, int duration,String flag) {
		super();
		this.idGuest = idGuest;
		this.idRestaurant = idRestaurant;
		this.date = date;
		this.time = time;
		this.duration = duration;
		this.reservedTables = new ArrayList<Tablee>();
		this.acceptedFriends = new ArrayList<Guest>();
		this.flag = flag;
	}
	
	

	public Reservation(Guest idGuest, Restaurant idRestaurant, String date, String time, int duration,
			List<Tablee> reservedTables,String flag) {
		super();
		this.idGuest = idGuest;
		this.idRestaurant = idRestaurant;
		this.date = date;
		this.time = time;
		this.duration = duration;
		this.reservedTables = reservedTables;
		this.flag = flag;
	}

	

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Guest getIdGuest() {
		return idGuest;
	}

	public void setIdGuest(Guest idGuest) {
		this.idGuest = idGuest;
	}

	public Restaurant getIdRestaurant() {
		return idRestaurant;
	}

	public void setIdRestaurant(Restaurant idRestaurant) {
		this.idRestaurant = idRestaurant;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public List<Tablee> getReservedTables() {
		return reservedTables;
	}

	public void setReservedTables(List<Tablee> reservedTables) {
		this.reservedTables = reservedTables;
	}

	public List<Guest> getAcceptedFriends() {
		return acceptedFriends;
	}

	public void setAcceptedFriends(List<Guest> acceptedFriends) {
		this.acceptedFriends = acceptedFriends;
	}


	
	
}
