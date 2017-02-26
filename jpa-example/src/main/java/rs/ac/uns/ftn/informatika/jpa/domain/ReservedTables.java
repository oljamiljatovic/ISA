package rs.ac.uns.ftn.informatika.jpa.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="reservedTables")
public class ReservedTables {

	@Id
    @GeneratedValue
	@Column(name="id", unique=true, nullable=false)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="idRestaurant")
	private Restaurant idRestaurant;
	
	@ManyToOne
	@JoinColumn(name="idTable")
	private Tablee idTable;
	
	
	private String date;
	private String time;
	private int duration; 
	
	public ReservedTables(){
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Restaurant getIdRestaurant() {
		return idRestaurant;
	}

	public void setIdRestaurant(Restaurant idRestaurant) {
		this.idRestaurant = idRestaurant;
	}




	public Tablee getIdTable() {
		return idTable;
	}

	public void setIdTable(Tablee idTable) {
		this.idTable = idTable;
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

	public ReservedTables(Restaurant idRestaurant,Tablee idTable, String date, String time, int duration) {
		super();
		this.idRestaurant = idRestaurant;
		this.idTable = idTable;
		this.date = date;
		this.time = time;
		this.duration = duration;
	}
	
	
	
	
}
