package rs.ac.uns.ftn.informatika.jpa.domain.users;

import static javax.persistence.InheritanceType.JOINED;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import rs.ac.uns.ftn.informatika.jpa.domain.User;

@Entity
@Table(name="guest")
@Inheritance(strategy=JOINED)
public class Guest extends User {


	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String surname;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name="friends",
	 joinColumns=@JoinColumn(name="personId"),
	 inverseJoinColumns=@JoinColumn(name="friendId")
	)
	private List<Guest> friends;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name="friends",
	 joinColumns=@JoinColumn(name="friendId"),
	 inverseJoinColumns=@JoinColumn(name="personId")
	)
	private List<Guest> friendOf;
	
	public Guest(){
		
	}

	
	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Guest(String name, String surname) {
		super();
		this.name = name;
		this.surname = surname;
	}


	public List<Guest> getFriends() {
		return friends;
	}


	public void setFriends(List<Guest> friends) {
		this.friends = friends;
	}


	public List<Guest> getFriendOf() {
		return friendOf;
	}


	public void setFriendOf(List<Guest> friendOf) {
		this.friendOf = friendOf;
	}



	
	
	


	
}
