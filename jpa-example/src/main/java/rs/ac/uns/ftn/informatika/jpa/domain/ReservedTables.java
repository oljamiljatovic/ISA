package rs.ac.uns.ftn.informatika.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="reservedTables")
public class ReservedTables {

	@Id
    @GeneratedValue
	@Column(name="id", unique=true, nullable=false)
	private Long id;
	
	private Long idRestaurant;
	private Long idReon;
	private Long idTable;
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

	public Long getIdRestaurant() {
		return idRestaurant;
	}

	public void setIdRestaurant(Long idRestaurant) {
		this.idRestaurant = idRestaurant;
	}



	public Long getIdReon() {
		return idReon;
	}

	public void setIdReon(Long idReon) {
		this.idReon = idReon;
	}

	public Long getIdTable() {
		return idTable;
	}

	public void setIdTable(Long idTable) {
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

	public ReservedTables(Long idRestaurant, Long idReon, Long idTable, String date, String time, int duration) {
		super();
		this.idRestaurant = idRestaurant;
		this.idReon = idReon;
		this.idTable = idTable;
		this.date = date;
		this.time = time;
		this.duration = duration;
	}
	
	
	
	
}
