package rs.ac.uns.ftn.informatika.jpa.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;

import rs.ac.uns.ftn.informatika.jpa.domain.City;
import rs.ac.uns.ftn.informatika.jpa.domain.Drink;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Employee;
import java.lang.Long;
import java.util.List;

public interface DrinkRepository extends PagingAndSortingRepository<Drink, Long>{
	
	public ArrayList<Drink> findAll();
	@Override
	void delete(Drink entity);
	public Drink findById(Long id);
}
