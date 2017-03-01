package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import rs.ac.uns.ftn.informatika.jpa.domain.ReservedTables;
import rs.ac.uns.ftn.informatika.jpa.domain.Restaurant;
import rs.ac.uns.ftn.informatika.jpa.domain.Tablee;
import rs.ac.uns.ftn.informatika.jpa.repository.ReservedTablesRepository;

@Service
public class ReservedTablesServiceImpl implements ReservedTablesService{

	@Autowired
	private ReservedTablesRepository reservedTablesRepository;
	
	
	@Override
	public List<ReservedTables> findReservedTablesByDate(String date) {
		// TODO Auto-generated method stub
		return reservedTablesRepository.findReservedTablesByDate(date);
	}

	
	@Override
	public ReservedTables createNew(ReservedTables reservedTable) {
		// TODO Auto-generated method stub
		return reservedTablesRepository.save(reservedTable);
	}


	@Override
	public ArrayList<ReservedTables> findByRestaurant(Restaurant rest) {
		return this.reservedTablesRepository.findByIdRestaurant(rest);
	}
	
	@Override
	public void Delete(ReservedTables entity) {
		reservedTablesRepository.delete(entity);;
	}


	@Override
	public ReservedTables findReservedTablesByAll(Restaurant idRestaurant, Tablee idTable, String date, String time,
			int duration) {
		
		return reservedTablesRepository.findReservedTablesByAll(idRestaurant, idTable, date, time, duration);
	}

}
