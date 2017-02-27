package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rs.ac.uns.ftn.informatika.jpa.domain.Bill;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Employee;
import rs.ac.uns.ftn.informatika.jpa.repository.BillRepository;

@Service
@Transactional
public class BillServiceImpl implements BillService{
	@Autowired
	private BillRepository billRepository;
	
	@Override
	public ArrayList<Bill> getBills() {
		
		return this.billRepository.findAll();
	}
	public Bill addNew(Bill bill) {
		return billRepository.save(bill);
	}
	@Override
	public ArrayList<Bill> findByWaiter(Employee waiter) {
		return billRepository.findByWaiter(waiter);
	}
	
}