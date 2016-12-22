package rs.ac.uns.ftn.informatika.jpa.domain;

import static javax.persistence.InheritanceType.JOINED;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;

@Entity
@Table(name="calendarForWaiter")
/*@Inheritance(strategy=JOINED)*/
public class CalendarForWaiter {
	@Id
	@GeneratedValue
	@Column(name="id", unique=true, nullable=false)
	private Integer id;
	
	@Column(name="username",nullable = false)
	private String username;
	
	@Column(nullable = false)
	private String datum;
	
	@Column(nullable = false)
	private String times;
	
	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

	public CalendarForWaiter(){}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	
	
}
