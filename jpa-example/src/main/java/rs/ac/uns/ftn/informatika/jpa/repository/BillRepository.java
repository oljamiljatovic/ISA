package rs.ac.uns.ftn.informatika.jpa.repository;

import java.util.ArrayList;

import org.springframework.data.repository.PagingAndSortingRepository;

import rs.ac.uns.ftn.informatika.jpa.domain.Bill;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Employee;

public interface BillRepository extends PagingAndSortingRepository<Bill, Long>{
	public ArrayList<Bill> findAll();
	
	public ArrayList<Bill> findByWaiter(Employee waiter);
	
}

