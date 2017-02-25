package rs.ac.uns.ftn.informatika.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="invitation")
public class Invitation {

	@Id
    @GeneratedValue
	@Column(name="id", unique=true, nullable=false)
	private Long id;
	
	
	@Column(name="sender",nullable = false)
	private Long sender;
	
	@Column(name="recipient",nullable = false)
	private Long recipient;
	
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

	public Long getSender() {
		return sender;
	}

	public void setSender(Long sender) {
		this.sender = sender;
	}

	public Long getRecipient() {
		return recipient;
	}

	public void setRecipient(Long recipient) {
		this.recipient = recipient;
	}

	public String getAccept() {
		return accept;
	}

	public void setAccept(String accept) {
		this.accept = accept;
	}

	public Invitation(Long id, Long sender, Long recipient, String accept) {
		super();
		this.id = id;
		this.sender = sender;
		this.recipient = recipient;
		this.accept = accept;
	}

	public Invitation(Long sender, Long recipient, String accept) {
		super();
		this.sender = sender;
		this.recipient = recipient;
		this.accept = accept;
	}
	
	
	
}
