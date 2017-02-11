package rs.ac.uns.ftn.informatika.jpa.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import rs.ac.uns.ftn.informatika.jpa.domain.Offer;
import rs.ac.uns.ftn.informatika.jpa.domain.User;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Provider;
import rs.ac.uns.ftn.informatika.jpa.service.OfferService;
import rs.ac.uns.ftn.informatika.jpa.service.ProviderService;

@Controller
@RequestMapping("/providerController")
public class ProviderController {
	
	@Autowired
	private ProviderService providerService;
	@Autowired
	private OfferService offerService;
	
	
	@RequestMapping(
			value = "/uzmiPonudjaca",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Provider> uzmiPonudjaca()  throws Exception {
		
		ServletRequestAttributes attr = (ServletRequestAttributes) 
			    RequestContextHolder.currentRequestAttributes();
		HttpSession session= attr.getRequest().getSession(true);
		User u = (User) session.getAttribute("korisnik");
		Provider provider= this.providerService.getProvider(u.getEmail());
		
		return new ResponseEntity<Provider>(provider, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/updateUserPassword",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Provider> updateUserPassword(@RequestBody Provider user)  throws Exception {
		this.providerService.updateProvider(user);
		return new ResponseEntity<Provider>(user, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/uzmiSvePonude",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<Offer>> uzmiSvePonude()  throws Exception {
		
		ArrayList<Offer> offers = this.offerService.getOffers();
		
		return new ResponseEntity<ArrayList<Offer>>(offers, HttpStatus.OK);
	}

	@RequestMapping(
			value = "/uzmiPonudu",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Offer> uzmiPonudu(@RequestBody Offer offer)  throws Exception {
		
		Offer offerr = this.offerService.getOffer(offer.getId());
		
		return new ResponseEntity<Offer>(offerr, HttpStatus.OK);
	}
	
}
