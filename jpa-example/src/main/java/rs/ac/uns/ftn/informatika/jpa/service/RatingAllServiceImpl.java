package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rs.ac.uns.ftn.informatika.jpa.domain.RatingAll;
import rs.ac.uns.ftn.informatika.jpa.domain.Reservation;
import rs.ac.uns.ftn.informatika.jpa.domain.Restaurant;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Guest;
import rs.ac.uns.ftn.informatika.jpa.repository.RatingAllRepository;

@Service
@Transactional
public class RatingAllServiceImpl implements RatingAllService{

	@Autowired
	private RatingAllRepository ratingAllRepository;

	@Override
	public RatingAll addNew(RatingAll rating) {
		return ratingAllRepository.save(rating);
	}

	@Override
	public ArrayList<RatingAll> findByGuest(Guest guest) {
		return ratingAllRepository.findByGuest(guest);
	}

	@Override
	public RatingAll findByGuestAndReservation(Guest guest, Reservation reservation) {
		return ratingAllRepository.findByGuestAndReservation(guest, reservation);
	}

	@Override
	public ArrayList<RatingAll> findByRestaurant(Restaurant restaurant) {
		return ratingAllRepository.findByRestaurant(restaurant);
	}



}
