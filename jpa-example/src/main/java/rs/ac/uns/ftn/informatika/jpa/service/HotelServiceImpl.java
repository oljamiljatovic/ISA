package rs.ac.uns.ftn.informatika.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import rs.ac.uns.ftn.informatika.jpa.domain.City;
import rs.ac.uns.ftn.informatika.jpa.domain.Hotel;
import rs.ac.uns.ftn.informatika.jpa.domain.Review;
import rs.ac.uns.ftn.informatika.jpa.domain.ReviewDetails;
import rs.ac.uns.ftn.informatika.jpa.repository.HotelRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.ReviewRepository;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class HotelServiceImpl implements HotelService {

	@Autowired
	private HotelRepository hotelRepository;

	@Autowired
	private ReviewRepository reviewRepository;

	@Override
	public Hotel getHotel(City city, String name) {
		Assert.notNull(city, "Grad ne sme biti null");
		Assert.hasLength(name, "Ime ne sme biti prazan string");
		return this.hotelRepository.findByCityAndName(city, name);
	}

	@Override
	public Page<Review> getReviews(Hotel hotel, Pageable pageable) {
		Assert.notNull(hotel, "Hotel ne sme biti null");
		return this.reviewRepository.findByHotel(hotel, pageable);
	}

	@Override
	public Review getReview(Hotel hotel, int reviewNumber) {
		Assert.notNull(hotel, "Hotel ne sme biti null");
		return this.reviewRepository.findByHotelAndIndex(hotel, reviewNumber);
	}

	@Override
	@Transactional(
            propagation = Propagation.REQUIRED,
            readOnly = false)
	public Review addReview(Hotel hotel, ReviewDetails details) {
		Review review = new Review(hotel, 1, details);
		return this.reviewRepository.save(review);
	}
}
