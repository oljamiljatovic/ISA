package rs.ac.uns.ftn.informatika.jpa.repository;


import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import rs.ac.uns.ftn.informatika.jpa.domain.Tablee;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Employee;

public interface TableRepository extends PagingAndSortingRepository<Tablee, Long> {
	
	public ArrayList<Tablee> findByReonAndRestaurant(Long reon, Long restaurant);
}
