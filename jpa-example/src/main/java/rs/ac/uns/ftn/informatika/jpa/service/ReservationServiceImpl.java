package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.jpa.domain.Invitation;
import rs.ac.uns.ftn.informatika.jpa.domain.Reservation;
import rs.ac.uns.ftn.informatika.jpa.domain.User;
import rs.ac.uns.ftn.informatika.jpa.repository.ReservationRepository;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private ReservationRepository reservationRepository;
	
	@Override
	public Reservation createNew(Reservation reservation) {
		return reservationRepository.save(reservation);
	}
	
	@Override
	public Reservation findOne(Long id) {
		return reservationRepository.findOne(id);
	}
	
	@Override
	public Reservation update(Reservation reservation, Long id) {
		reservation.setId(id);
		return reservationRepository.save(reservation);
	}
	
	@Override
	public Reservation findReservationByAll(Long idGuest, Long idRestaurant,String date, String time) {
		
		return reservationRepository.findReservationByAll(idGuest,idRestaurant,date,time);
	}

	@Override
	public ArrayList<Reservation> findByIdGuest(Long idGuest) {
		return reservationRepository.findByIdGuest(idGuest);
	}

	@Override
	public ArrayList<Reservation> findByIdRestaurantAndDate(Long idRestaurant, String date) {
		return reservationRepository.findByIdRestaurantAndDate(idRestaurant, date);
	}
	
	
}
