package rs.ac.uns.ftn.informatika.jpa.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import rs.ac.uns.ftn.informatika.jpa.domain.Drink;
import rs.ac.uns.ftn.informatika.jpa.domain.Restaurant;

public interface DrinkRepository extends PagingAndSortingRepository<Drink, Long>{
	
	public ArrayList<Drink> findAll();
	void delete(Drink entity);
	public Drink findById(Long id);
	
	@Modifying
	@Query("update Drink set name = ?, description = ?,price = ? where id = ? ")
	public void updateDrink(String name, String desc, float price, Long id);
	
	public ArrayList<Drink> findByRestaurant(Restaurant restaurant);
	
	public Drink findByName(String name);
	
	@Modifying
	@Query("update Drink set exist = ? where id = ? ")
	public void updateDrinkFlag(boolean exist, Long id);

}
