package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import rs.ac.uns.ftn.informatika.jpa.domain.Reservation;
import rs.ac.uns.ftn.informatika.jpa.domain.Restaurant;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Guest;
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
	public Reservation findReservationByAll(Guest idGuest, Restaurant idRestaurant,String date, String time,String flag) {
		//System.out.println("SERVICE id guest "+idGuest +"idRest "+ idRestaurant );
		return reservationRepository.findReservationByAll(idGuest,idRestaurant,date,time,flag);
	
	}
	@Override
	public ArrayList<Reservation> findByIdGuest(Guest idGuest) {
		return reservationRepository.findByIdGuest(idGuest);
	}

	@Override
	public ArrayList<Reservation> findByIdRestaurantAndDate(Restaurant idRestaurant, String date) {
		return reservationRepository.findByIdRestaurantAndDate(idRestaurant, date);
	}

	@Override
	public ArrayList<Reservation> findByAcceptedFriends_Id(Long id) {
		return reservationRepository.findByAcceptedFriends_Id(id);
	}

	@Override
	public ArrayList<Reservation> findByIdRestaurantAndDateAndFlag(Restaurant idRestaurant, String date, String flag) {
		return reservationRepository.findByIdRestaurantAndDateAndFlag(idRestaurant, date, flag);
	}
	
	
}
