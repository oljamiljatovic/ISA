package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;

import rs.ac.uns.ftn.informatika.jpa.domain.Invitation;
import rs.ac.uns.ftn.informatika.jpa.domain.Reservation;

public interface ReservationService {


	Reservation createNew(Reservation reservation);

	Reservation findOne(Long id);

	Reservation update(Reservation reservation, Long id);

	Reservation findReservationByAll(Long idGuest, Long idRestaurant, String date, String time);
	
	ArrayList<Reservation> findByIdGuest(Long idGuest);
	
	ArrayList<Reservation> findByIdRestaurantAndDate(Long idRestaurant, String date);
	
	ArrayList<Reservation> findByAcceptedFriends_Id(Long id);

}
