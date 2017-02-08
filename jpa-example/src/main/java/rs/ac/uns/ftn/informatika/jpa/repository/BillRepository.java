package rs.ac.uns.ftn.informatika.jpa.repository;

import java.util.ArrayList;

import org.springframework.data.repository.PagingAndSortingRepository;

import rs.ac.uns.ftn.informatika.jpa.domain.Bill;

public interface BillRepository extends PagingAndSortingRepository<Bill, Long>{
	public ArrayList<Bill> findAll();
}

