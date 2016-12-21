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

	@Column(nullable = false)
	protected String role;
	
	public Integer getId() {
		return id;
	}
	
	public User(){
		
	}
	
	public User(String username, String password) {
		
		this.username = username;
		this.password = password;
		
	}



	public User(Integer id, String username, String password, String role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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
