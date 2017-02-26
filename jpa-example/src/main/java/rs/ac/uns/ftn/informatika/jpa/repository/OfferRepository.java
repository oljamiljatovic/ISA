package rs.ac.uns.ftn.informatika.jpa.repository;

import java.util.ArrayList;

import org.springframework.data.repository.PagingAndSortingRepository;

import rs.ac.uns.ftn.informatika.jpa.domain.Offer;
import rs.ac.uns.ftn.informatika.jpa.domain.Restaurant;

public interface OfferRepository extends PagingAndSortingRepository<Offer, Long> {

	public ArrayList<Offer> findByRestaurant(Restaurant restaurant);
}
