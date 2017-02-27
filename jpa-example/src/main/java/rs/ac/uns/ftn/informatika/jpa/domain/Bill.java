package rs.ac.uns.ftn.informatika.jpa.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Employee;

@Entity
@Table(name="bill")
public class Bill{
	@Id
    @GeneratedValue
	@Column(name="bill_id", unique=true, nullable=false)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="waiter")
	private Employee waiter;
	
	@ManyToOne
	@JoinColumn(name="order_bill")
	private Order orderBill;
	
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

	public Employee getWaiter() {
		return waiter;
	}

	public void setWaiter(Employee waiter) {
		this.waiter = waiter;
	}

	public Order getOrderBill() {
		return orderBill;
	}

	public void setOrderBill(Order orderBill) {
		this.orderBill = orderBill;
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


	
}
