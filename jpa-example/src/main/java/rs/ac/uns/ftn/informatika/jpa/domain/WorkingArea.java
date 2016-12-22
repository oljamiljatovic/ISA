package rs.ac.uns.ftn.informatika.jpa.domain;

import static javax.persistence.InheritanceType.JOINED;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;

@Entity
@Table(name="workingArea")
@Inheritance(strategy=JOINED)
public class WorkingArea {
	@Id
	@GeneratedValue
	@Column(name="id", unique=true, nullable=false)
	private Integer id;
	
	@Column(name="username",nullable = false)
	private String username;
	
	@Column(nullable = false)
	private String area;

	public WorkingArea(){}

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

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
}
