package rs.ac.uns.ftn.informatika.jpa.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="reservation")
public class Reservation {

	@Id
    @GeneratedValue
	@Column(name="id", unique=true, nullable=false)
	private Long id;
	
	private Long idGuest;
	
	private Long idRestaurant;
	
	private String date;
	
	private String time;
	
	private int duration;
	
	@OneToMany
	private List<Tablee> reservedTables =new ArrayList<Tablee>();
	
	public Reservation(){
		
	}

	public Reservation(Long idGuest, Long idRestaurant, String date, String time, int duration) {
		super();
		this.idGuest = idGuest;
		this.idRestaurant = idRestaurant;
		this.date = date;
		this.time = time;
		this.duration = duration;
		this.reservedTables = new ArrayList<Tablee>();
	}
	
	

	public Reservation(Long idGuest, Long idRestaurant, String date, String time, int duration,
			List<Tablee> reservedTables) {
		super();
		this.idGuest = idGuest;
		this.idRestaurant = idRestaurant;
		this.date = date;
		this.time = time;
		this.duration = duration;
		this.reservedTables = reservedTables;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdGuest() {
		return idGuest;
	}

	public void setIdGuest(Long idGuest) {
		this.idGuest = idGuest;
	}

	public Long getIdRestaurant() {
		return idRestaurant;
	}

	public void setIdRestaurant(Long idRestaurant) {
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
	
	
}
