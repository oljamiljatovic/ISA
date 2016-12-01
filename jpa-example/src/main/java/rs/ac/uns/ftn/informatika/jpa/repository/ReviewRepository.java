package rs.ac.uns.ftn.informatika.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import rs.ac.uns.ftn.informatika.jpa.domain.Hotel;
import rs.ac.uns.ftn.informatika.jpa.domain.Review;

public interface ReviewRepository extends Repository<Review, Long> {

	public Page<Review> findByHotel(Hotel hotel, Pageable pageable);

	public Review findByHotelAndIndex(Hotel hotel, int index);

	public Review save(Review review);

}
