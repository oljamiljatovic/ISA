package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rs.ac.uns.ftn.informatika.jpa.domain.Offer;
import rs.ac.uns.ftn.informatika.jpa.repository.OfferRepository;


@Service
@Transactional
public class OfferServiceImpl implements OfferService {
	
	@Autowired
	private OfferRepository offerRepository;

	@Override
	public Offer addOffer(Offer of) {
		return this.offerRepository.save(of);
	}

	@Override
	public ArrayList<Offer> getOffers() {
		return (ArrayList<Offer>) offerRepository.findAll();
	}

	@Override
	public Offer getOffer(Long id) {
		return this.offerRepository.findOne(id);
	}

	@Override
	public ArrayList<Offer> getOffersByRestaurant(Long id) {
		// TODO Auto-generated method stub
		return this.offerRepository.findByRestaurant(id);
	}

	@Override
	public void delete(Long id) {
		this.offerRepository.delete(id);
	}

}
