package rs.ac.uns.ftn.informatika.jpa.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import rs.ac.uns.ftn.informatika.jpa.domain.Reservation;
import rs.ac.uns.ftn.informatika.jpa.domain.ReservedTables;
import rs.ac.uns.ftn.informatika.jpa.domain.Restaurant;
import rs.ac.uns.ftn.informatika.jpa.domain.Tablee;

public interface ReservedTablesRepository extends PagingAndSortingRepository<ReservedTables, Long>{

	@Query("select r from ReservedTables r where r.date = ?1")
	List<ReservedTables> findReservedTablesByDate(String date);

	ArrayList<ReservedTables> findByIdRestaurant(Restaurant idrestaurant);
	 
	@Query("select r from ReservedTables r where r.idRestaurant = ?1 and r.idTable = ?2 and r.date = ?3 and r.time = ?4 and r.duration = ?5")
    ReservedTables findReservedTablesByAll(Restaurant idRestaurant, Tablee idTable, String date, String time , int duration);
	
}
