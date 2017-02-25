package rs.ac.uns.ftn.informatika.jpa.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import rs.ac.uns.ftn.informatika.jpa.domain.Reservation;
import rs.ac.uns.ftn.informatika.jpa.domain.User;

public interface ReservationRepository extends PagingAndSortingRepository<Reservation, Long>{

	@Query("select r from Reservation r where r.idGuest = ?1 and r.idRestaurant = ?2 and r.date = ?3 and r.time = ?4")
    Reservation findReservationByAll(Long idGuest, Long idRestaurant, String date, String time);
   
}
