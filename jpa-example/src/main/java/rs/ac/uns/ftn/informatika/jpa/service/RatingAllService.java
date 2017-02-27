package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;
import rs.ac.uns.ftn.informatika.jpa.domain.RatingAll;
import rs.ac.uns.ftn.informatika.jpa.domain.Reservation;
import rs.ac.uns.ftn.informatika.jpa.domain.Restaurant;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Guest;

public interface RatingAllService {
	
	RatingAll addNew(RatingAll rating);
	
	public ArrayList<RatingAll> findByGuest(Guest guest);
	
	public RatingAll findByGuestAndReservation(Guest guest, Reservation reservation);
	
	public ArrayList<RatingAll> findByRestaurant(Restaurant restaurant);
}
