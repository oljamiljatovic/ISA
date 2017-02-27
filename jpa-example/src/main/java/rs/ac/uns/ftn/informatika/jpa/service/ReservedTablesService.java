package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;
import java.util.List;

import rs.ac.uns.ftn.informatika.jpa.domain.ReservedTables;
import rs.ac.uns.ftn.informatika.jpa.domain.Restaurant;

public interface ReservedTablesService {

	List<ReservedTables> findReservedTablesByDate(String date);

	ReservedTables createNew(ReservedTables reservedTable);
	
	ArrayList<ReservedTables> findByRestaurant(Restaurant rest);
	
 
}
