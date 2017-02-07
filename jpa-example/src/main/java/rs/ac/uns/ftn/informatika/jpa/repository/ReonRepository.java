package rs.ac.uns.ftn.informatika.jpa.repository;

import java.util.ArrayList;

import org.springframework.data.repository.PagingAndSortingRepository;

import rs.ac.uns.ftn.informatika.jpa.domain.Reon;

public interface ReonRepository extends PagingAndSortingRepository<Reon, Long> {
	
	public ArrayList<Reon> findByRestaurant(String restaurant);

}
