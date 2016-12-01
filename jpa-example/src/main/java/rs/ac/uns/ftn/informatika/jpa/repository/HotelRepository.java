package rs.ac.uns.ftn.informatika.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import rs.ac.uns.ftn.informatika.jpa.domain.City;
import rs.ac.uns.ftn.informatika.jpa.domain.Hotel;
import rs.ac.uns.ftn.informatika.jpa.domain.HotelSummary;
import rs.ac.uns.ftn.informatika.jpa.domain.RatingCount;

public interface HotelRepository extends Repository<Hotel, Long> {

	public Hotel findByCityAndName(City city, String name);

	@Query("select h.city as city, h.name as name, avg(r.rating) as averageRating "
			+ "from Hotel h left outer join h.reviews r where h.city = ?1 group by h")
	public Page<HotelSummary> findByCity(City city, Pageable pageable);

	@Query("select r.rating as rating, count(r) as count "
			+ "from Review r where r.hotel = ?1 group by r.rating order by r.rating DESC")
	public List<RatingCount> findRatingCounts(Hotel hotel);

}
