package rs.ac.uns.ftn.informatika.jpa.repository;

import java.util.ArrayList;

import org.springframework.data.repository.PagingAndSortingRepository;

import rs.ac.uns.ftn.informatika.jpa.domain.RatingAll;
import rs.ac.uns.ftn.informatika.jpa.domain.Reservation;
import rs.ac.uns.ftn.informatika.jpa.domain.Restaurant;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Guest;
import java.util.List;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Employee;


public interface RatingAllRepository extends PagingAndSortingRepository<RatingAll, Long>{
	public ArrayList<RatingAll> findByGuest(Guest guest);
	
	public RatingAll findByGuestAndReservation(Guest guest, Reservation reservation);
	
	public ArrayList<RatingAll> findByRestaurant(Restaurant restaurant);
	
	public ArrayList<RatingAll> findByMeals_id(Long id);
	
	public ArrayList<RatingAll> findByWaiter(Employee waiter);
}
