package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;

import rs.ac.uns.ftn.informatika.jpa.domain.Bill;
import rs.ac.uns.ftn.informatika.jpa.domain.RatingAll;

public interface RatingAllService {
	
	RatingAll addNew(RatingAll rating);
	
	public ArrayList<RatingAll> findByGuestId(Long guestId);
	
	public RatingAll findByGuestIdAndReservationId(Long guestId, Long reservationId);
	
	public ArrayList<RatingAll> findByRestaurant(Long id);
}
