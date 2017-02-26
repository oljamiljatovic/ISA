package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rs.ac.uns.ftn.informatika.jpa.domain.RatingAll;
import rs.ac.uns.ftn.informatika.jpa.repository.RatingAllRepository;

@Service
@Transactional
public class RatingAllServiceImpl implements RatingAllService{

	@Autowired
	private RatingAllRepository ratingAllRepository;
	
	@Override
	public ArrayList<RatingAll> findByGuestId(Long guestId) {
		return this.ratingAllRepository.findByGuestId(guestId);
	}

	@Override
	public RatingAll addNew(RatingAll rating) {
		return ratingAllRepository.save(rating);
	}

	@Override
	public RatingAll findByGuestIdAndReservationId(Long guestId, Long reservationId) {
		return ratingAllRepository.findByGuestIdAndReservationId(guestId, reservationId);
	}

}
