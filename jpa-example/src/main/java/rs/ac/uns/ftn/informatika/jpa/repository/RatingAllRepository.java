package rs.ac.uns.ftn.informatika.jpa.repository;

import java.util.ArrayList;

import org.springframework.data.repository.PagingAndSortingRepository;
import rs.ac.uns.ftn.informatika.jpa.domain.RatingAll;
import java.lang.Long;
import java.util.List;


public interface RatingAllRepository extends PagingAndSortingRepository<RatingAll, Long>{
	public ArrayList<RatingAll> findByGuestId(Long guestId);
	
	public RatingAll findByGuestIdAndReservationId(Long guestId, Long reservationId);
	
	public ArrayList<RatingAll> findByRestaurantId(Long restaurantid);
}
