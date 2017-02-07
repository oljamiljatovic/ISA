package rs.ac.uns.ftn.informatika.jpa.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AssignReon implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private Long waiter_id;
	@Column(nullable = false)
	private Long reon_id;
	
	public AssignReon() {
		
	}
	
	public AssignReon(Long waiter_id, Long reon_id) {
		super();
		this.waiter_id = waiter_id;
		this.reon_id = reon_id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getWaiter_id() {
		return waiter_id;
	}

	public void setWaiter_id(Long waiter_id) {
		this.waiter_id = waiter_id;
	}

	public Long getReon_id() {
		return reon_id;
	}

	public void setReon_id(Long reon_id) {
		this.reon_id = reon_id;
	}
	
	

}
