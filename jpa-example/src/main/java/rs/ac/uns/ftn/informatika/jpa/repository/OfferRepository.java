package rs.ac.uns.ftn.informatika.jpa.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import rs.ac.uns.ftn.informatika.jpa.domain.Offer;

public interface OfferRepository extends PagingAndSortingRepository<Offer, Long> {

}