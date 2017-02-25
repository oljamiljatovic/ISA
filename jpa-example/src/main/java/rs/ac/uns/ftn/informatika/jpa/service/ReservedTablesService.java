package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.List;

import rs.ac.uns.ftn.informatika.jpa.domain.ReservedTables;

public interface ReservedTablesService {

	List<ReservedTables> findReservedTablesByDate(String date);
	
 
}
