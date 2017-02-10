package rs.ac.uns.ftn.informatika.jpa.service;

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

}
