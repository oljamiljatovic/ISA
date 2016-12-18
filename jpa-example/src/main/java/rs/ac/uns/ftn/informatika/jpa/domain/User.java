package rs.ac.uns.ftn.informatika.jpa.domain;

import static javax.persistence.InheritanceType.JOINED;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

@Entity
@Table(name="user")
@Inheritance(strategy=JOINED)
public class User {

	@Id
	  @GeneratedValue
	  @Column(name="id", unique=true, nullable=false)
	  private Integer id;
	
	@Column(name="username",nullable = false)
	protected String username;
	
	@Column(nullable = false)
	protected String password;

	public Integer getId() {
		return id;
	}
	
	public User(){
		
	}
	
	

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		this.id = 151;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	






	
}
