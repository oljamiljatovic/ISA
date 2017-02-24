package rs.ac.uns.ftn.informatika.jpa.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import rs.ac.uns.ftn.informatika.jpa.domain.Reon;

public interface ReonRepository extends PagingAndSortingRepository<Reon, Long> {
	
	public ArrayList<Reon> findByRestaurant(Long restaurant);


	@Modifying
	@Query("update Reon set name = ?, location = ?, numberTable = ? where id = ? ")
	public void updateReon(String name, String location, int numberTable , Long id);
	
}
