package rs.ac.uns.ftn.informatika.jpa.service;

import rs.ac.uns.ftn.informatika.jpa.domain.Invitation;
import rs.ac.uns.ftn.informatika.jpa.domain.Reservation;

public interface ReservationService {


	Reservation createNew(Reservation reservation);

	Reservation findOne(Long id);

	Reservation update(Reservation reservation, Long id);

	Reservation findReservationByAll(Long idGuest, Long idRestaurant, String date, String time);

}
