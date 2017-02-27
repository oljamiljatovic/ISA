package rs.ac.uns.ftn.informatika.jpa.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import rs.ac.uns.ftn.informatika.jpa.domain.Reservation;
import rs.ac.uns.ftn.informatika.jpa.domain.Restaurant;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Guest;

public interface ReservationRepository extends PagingAndSortingRepository<Reservation, Long>{

	@Query("select r from Reservation r where r.idGuest = ?1 and r.idRestaurant = ?2 and r.date = ?3 and r.time = ?4")
    Reservation findReservationByAll(Long idGuest, Long idRestaurant, String date, String time);
	
	ArrayList<Reservation> findByIdGuest(Guest idGuest);
	
	ArrayList<Reservation> findByIdRestaurantAndDate(Restaurant idRestaurant, String date);
   
	ArrayList<Reservation> findByAcceptedFriends_Id(Long id);
}
