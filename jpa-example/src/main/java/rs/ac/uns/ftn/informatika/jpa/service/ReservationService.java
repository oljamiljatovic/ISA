package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;

import rs.ac.uns.ftn.informatika.jpa.domain.Reservation;
import rs.ac.uns.ftn.informatika.jpa.domain.Restaurant;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Guest;

public interface ReservationService {


	Reservation createNew(Reservation reservation);

	Reservation findOne(Long id);

	Reservation update(Reservation reservation, Long id);

	Reservation findReservationByAll(Guest idGuest, Restaurant idRestaurant, String date, String time,String flag);
	
	ArrayList<Reservation> findByIdGuest(Guest idGuest);
	
	ArrayList<Reservation> findByIdRestaurantAndDate(Restaurant idRestaurant, String date);
	
	ArrayList<Reservation> findByAcceptedFriends_Id(Long id);

}
