package rs.ac.uns.ftn.informatika.jpa.repository;

import java.util.ArrayList;

import org.springframework.data.repository.PagingAndSortingRepository;

import rs.ac.uns.ftn.informatika.jpa.domain.CalendarForWaiter;

public interface CalendarForWaiterRepository extends PagingAndSortingRepository<CalendarForWaiter, Long>{
	public ArrayList<CalendarForWaiter> findAll();
}
