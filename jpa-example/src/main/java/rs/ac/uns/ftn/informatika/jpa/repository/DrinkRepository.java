package rs.ac.uns.ftn.informatika.jpa.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import rs.ac.uns.ftn.informatika.jpa.domain.Drink;

public interface DrinkRepository extends PagingAndSortingRepository<Drink, Long>{
	
	public ArrayList<Drink> findAll();
	void delete(Drink entity);
	public Drink findById(Long id);
	
	@Modifying
	@Query("update Drink set name = ?, description = ?,price = ?, restaurant = ? where id = ? ")
	public void updateDrink(String name, String desc, float price, Long rest, Long id);
	
	public ArrayList<Drink> findByRestaurant(Long restaurant);

}
