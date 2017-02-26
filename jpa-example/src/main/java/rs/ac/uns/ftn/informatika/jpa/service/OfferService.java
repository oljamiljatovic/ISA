package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;

import rs.ac.uns.ftn.informatika.jpa.domain.Offer;
import rs.ac.uns.ftn.informatika.jpa.domain.Restaurant;

public interface OfferService {

	public Offer addOffer(Offer of);
	
	public ArrayList<Offer> getOffers();
	
	public Offer getOffer(Long id);
	
	public ArrayList<Offer> getOffersByRestaurant(Restaurant id);
	
	public void delete(Long id);
}
