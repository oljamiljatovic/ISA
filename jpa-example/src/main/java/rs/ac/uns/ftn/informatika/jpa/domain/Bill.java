package rs.ac.uns.ftn.informatika.jpa.domain;

import static javax.persistence.InheritanceType.JOINED;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;

@Entity
@Table(name="bill")
@Inheritance(strategy=JOINED)
public class Bill implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue
	@Column(name="bill_id", unique=true, nullable=false)
	private Long id;
	
	@Column(nullable = false)
	private Long waiter_id;
	
	@Column(nullable = false)
	private int bill;
	
	@Column(nullable = false)
	private Date dateOfBill;
	
	public Bill(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getBill() {
		return bill;
	}

	public void setBill(int bill) {
		this.bill = bill;
	}

	public Date getDateOfBill() {
		return dateOfBill;
	}

	public void setDateOfBill(Date dateOfBill) {
		this.dateOfBill = dateOfBill;
	}

	public Long getWaiter_id() {
		return waiter_id;
	}

	public void setWaiter_id(Long waiter_id) {
		this.waiter_id = waiter_id;
	}
	
	
}
