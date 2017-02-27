package rs.ac.uns.ftn.informatika.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import rs.ac.uns.ftn.informatika.jpa.domain.users.Guest;

@Entity
@Table(name="invitation")
public class Invitation {

	@Id
    @GeneratedValue
	@Column(name="id", unique=true, nullable=false)
	private Long id;
	
	
	@OneToOne
	@JoinColumn(name="sender")
	private Guest sender;
	
	@OneToOne
	@JoinColumn(name="recipient")
	private Guest recipient;
	
	@Column(name="accept",nullable = false)
	private String accept;
	
	public Invitation(){
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Guest getSender() {
		return sender;
	}

	public void setSender(Guest sender) {
		this.sender = sender;
	}

	public Guest getRecipient() {
		return recipient;
	}

	public void setRecipient(Guest recipient) {
		this.recipient = recipient;
	}

	public String getAccept() {
		return accept;
	}

	public void setAccept(String accept) {
		this.accept = accept;
	}

	public Invitation(Long id, Guest sender, Guest recipient, String accept) {
		super();
		this.id = id;
		this.sender = sender;
		this.recipient = recipient;
		this.accept = accept;
	}

	public Invitation(Guest sender, Guest recipient, String accept) {
		super();
		this.sender = sender;
		this.recipient = recipient;
		this.accept = accept;
	}
	
	
	
}
