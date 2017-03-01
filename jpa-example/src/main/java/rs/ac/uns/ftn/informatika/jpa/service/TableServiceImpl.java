package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import rs.ac.uns.ftn.informatika.jpa.domain.Reon;
import rs.ac.uns.ftn.informatika.jpa.domain.Restaurant;
import rs.ac.uns.ftn.informatika.jpa.domain.Tablee;
import rs.ac.uns.ftn.informatika.jpa.repository.TableRepository;

@Service
public class TableServiceImpl implements TableService {

	@Autowired
	private TableRepository tableRepository;
	
	
	@Override
	public Tablee createTable(Tablee table) {
		
		 return this.tableRepository.save(table);
	}


	@Override
	public ArrayList<Tablee> findByReonAndRestaurant(Reon reon, Restaurant restaurant) {
		return tableRepository.findByReonAndRestaurant(reon, restaurant);
	}


	@Override
	public ArrayList<Tablee> findByRestaurant(Restaurant restaurant) {
		return tableRepository.findByRestaurant(restaurant);
	}


	@Override
	public ArrayList<Tablee> findByReon(Reon reon) {
		// TODO Auto-generated method stub
		return this.tableRepository.findByReon(reon);
	}


	@Override
	public void delete(Long id) {
		this.tableRepository.delete(id);
	}
	
	

	@Override
	public Tablee findById(Long id) {
		return tableRepository.findOne(id);
	}


	@Transactional(isolation=Isolation.SERIALIZABLE)
	@Override
	public void updateTableFlag(Tablee table) {
		this.tableRepository.updateTableFlag(table.isExist(), table.getId());
	}
}
