package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;

import rs.ac.uns.ftn.informatika.jpa.domain.Bill;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Employee;

public interface BillService {
	ArrayList<Bill> getBills();
	
	Bill addNew(Bill bill);
	
	public ArrayList<Bill> findByWaiter(Employee waiter);
}
