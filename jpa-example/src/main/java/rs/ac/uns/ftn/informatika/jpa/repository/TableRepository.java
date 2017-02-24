package rs.ac.uns.ftn.informatika.jpa.repository;


import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import rs.ac.uns.ftn.informatika.jpa.domain.Tablee;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Employee;
import java.lang.Long;
import java.util.List;

public interface TableRepository extends PagingAndSortingRepository<Tablee, Long> {
	
	public ArrayList<Tablee> findByReonAndRestaurant(Long reon, Long restaurant);
	
	public ArrayList<Tablee> findByRestaurant(Long restaurant);
	
	public ArrayList<Tablee> findByReon(Long reon);
}
