
package rs.ac.uns.ftn.informatika.jpa.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import rs.ac.uns.ftn.informatika.jpa.domain.ReservedTables;
import rs.ac.uns.ftn.informatika.jpa.domain.Restaurant;

public interface ReservedTablesRepository extends PagingAndSortingRepository<ReservedTables, Long>{

	@Query("select r from ReservedTables r where r.date = ?1")
	List<ReservedTables> findReservedTablesByDate(String date);

	ArrayList<ReservedTables> findByIdRestaurant(Restaurant idrestaurant);
	 
}