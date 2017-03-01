package rs.ac.uns.ftn.informatika.jpa.controller;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
import rs.ac.uns.ftn.informatika.jpa.domain.PurchaseOrder;
import rs.ac.uns.ftn.informatika.jpa.domain.User;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Provider;
import rs.ac.uns.ftn.informatika.jpa.domain.users.RestaurantManager;
import rs.ac.uns.ftn.informatika.jpa.service.ManagerService;
import rs.ac.uns.ftn.informatika.jpa.service.OfferService;
import rs.ac.uns.ftn.informatika.jpa.service.ProviderService;
import rs.ac.uns.ftn.informatika.jpa.service.PurchaseOrderService;

@Controller
@RequestMapping("/providerController")
public class ProviderController {
	
	@Autowired
	private ProviderService providerService;
	@Autowired
	private OfferService offerService;
	@Autowired
	private PurchaseOrderService purchaseService;
	@Autowired
	private ManagerService managerService;
	
	
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
		
		ArrayList<Offer> po = this.offerService.getOffers();
		ArrayList<Offer> temp = new ArrayList<Offer>();
		
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		Date todayDate = dateFormatter.parse(dateFormatter.format(new Date()));
		
		for(int i=0; i<po.size(); i++){
			Offer offer = this.offerService.getOffer(po.get(i).getId());
			String datum = offer.getEndDate();
			
			Date compare = dateFormatter.parse(datum);
			if(compare.after(todayDate))
				temp.add(po.get(i));	
		}
		
		return new ResponseEntity<ArrayList<Offer>>(temp, HttpStatus.OK);
	}
	@RequestMapping(
			value = "/uzmiSveAktuelnePonudeRestorana",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<Offer>> uzmiSveAktuelnePonude()  throws Exception {
		
		ServletRequestAttributes attr = (ServletRequestAttributes) 
			    RequestContextHolder.currentRequestAttributes();
		HttpSession session= attr.getRequest().getSession(true);
		User u = (User) session.getAttribute("korisnik");
		RestaurantManager rm = null;
		if(u.getRole().equals("restaurantManager")){
			rm= this.managerService.getManager(u.getEmail());
		}
		ArrayList<Offer> po = this.offerService.getOffersByRestaurant(rm.getRestaurant());
		ArrayList<Offer> temp = new ArrayList<Offer>();
		
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		Date todayDate = dateFormatter.parse(dateFormatter.format(new Date()));
		
		for(int i=0; i<po.size(); i++){
			Offer offer = this.offerService.getOffer(po.get(i).getId());
			String datum = offer.getEndDate();
			
			Date compare = dateFormatter.parse(datum);
			if(compare.after(todayDate))
				temp.add(po.get(i));	
		}
		return new ResponseEntity<ArrayList<Offer>>(temp, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/uzmiPonudu",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Offer> uzmiPonudu(@RequestBody Offer offer)  throws Exception {
		
		Offer offerr = this.offerService.getOffer(offer.getId());
		return new ResponseEntity<Offer>(offerr, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/dodajPorudzbenicu",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PurchaseOrder> dodajPorudzbenicu(@RequestBody PurchaseOrder po)  throws Exception {
		

		ServletRequestAttributes attr = (ServletRequestAttributes) 
			    RequestContextHolder.currentRequestAttributes();
		HttpSession session= attr.getRequest().getSession(true);
		User u = (User) session.getAttribute("korisnik");
		Provider provider= this.providerService.getProvider(u.getEmail());
		Offer of = this.offerService.getOffer(po.getOffer().getId());
		po.setOffer(of);
		po.setProvider(provider);
		po.setRestaurant(provider.getRestaurant());
		this.purchaseService.addPurchaseOrder(po);
		return new ResponseEntity<PurchaseOrder>(po, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/uzmiPorudzbenicu",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PurchaseOrder> uzmiPorudzbenicu(@RequestBody PurchaseOrder po)  throws Exception {
		

		ServletRequestAttributes attr = (ServletRequestAttributes) 
			    RequestContextHolder.currentRequestAttributes();
		HttpSession session= attr.getRequest().getSession(true);
		User u = (User) session.getAttribute("korisnik");
		Provider provider= this.providerService.getProvider(u.getEmail());
		Offer of = this.offerService.getOffer(po.getOffer().getId());
		po.setOffer(of);
		po.setProvider(provider);
		po.setRestaurant(provider.getRestaurant());
		PurchaseOrder purch = this.purchaseService.getPurchaseOrderByOfferAndProvider(po);
		return new ResponseEntity<PurchaseOrder>(purch, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/izmeniPorudzbenicu",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PurchaseOrder> izmeniPorudzbenicu(@RequestBody PurchaseOrder po)  throws Exception {
	
		ServletRequestAttributes attr = (ServletRequestAttributes) 
			    RequestContextHolder.currentRequestAttributes();
		HttpSession session= attr.getRequest().getSession(true);
		User u = (User) session.getAttribute("korisnik");
		if(u.getRole().equals("restaurantManager")){
			RestaurantManager rm= this.managerService.getManager(u.getEmail());
			po.setRestaurant(rm.getRestaurant());
		}else if(u.getRole().equals("provider")){
			Provider rm= this.providerService.getProvider(u.getEmail());
			po.setProvider(rm);
			po.setRestaurant(rm.getRestaurant());
		}
		
		if(u.getRole().equals("restaurantManager")){
			Offer of = this.offerService.getOffer(po.getOffer().getId());
			of.setAccepted(true);
			this.offerService.updateFlag(true, of.getId());
			po.setOffer(of);
			ArrayList<PurchaseOrder> por = this.purchaseService.getPurchaseOrderByOffer(of);
			for(int i=0; i<por.size(); i++){
				this.purchaseService.updateFlag(po.getFlag(), por.get(i).getId());
			}
			
		}else if(u.getRole().equals("provider")){
			try{
				this.purchaseService.updatePurchaseOrder(po);
			}catch(Exception e){
				po = null;
			}
		}
		return new ResponseEntity<PurchaseOrder>(po, HttpStatus.OK);
	}

	@RequestMapping(
			value = "/uzmiSvePorudzbeniceAktuelnePonude",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<PurchaseOrder>> uzmiAktivnePoruzbenice(@RequestBody Offer off)  throws Exception {
		
		Offer offerr = this.offerService.getOffer(off.getId());
		ArrayList<PurchaseOrder> po = this.purchaseService.getPurchaseOrderByOffer(offerr);
		ArrayList<PurchaseOrder> temp = new ArrayList<PurchaseOrder>();
		
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		Date todayDate = dateFormatter.parse(dateFormatter.format(new Date()));
		
		for(int i=0; i<po.size(); i++){
			Offer offer = this.offerService.getOffer(po.get(i).getOffer().getId());
			String datum = offer.getEndDate();
			
			Date compare = dateFormatter.parse(datum);
			if(compare.after(todayDate))
				temp.add(po.get(i));	
		}
		return new ResponseEntity<ArrayList<PurchaseOrder>>(temp, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/uzmiSvePorudzbenicePonudjaca",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<PurchaseOrder>> uzmisvePoruzbenice()  throws Exception {
		
		ServletRequestAttributes attr = (ServletRequestAttributes) 
			    RequestContextHolder.currentRequestAttributes();
		HttpSession session= attr.getRequest().getSession(true);
		User u = (User) session.getAttribute("korisnik");
		Provider rm= this.providerService.getProvider(u.getEmail());
		ArrayList<PurchaseOrder> temp = this.purchaseService.getPurchaseOrderByProvider(rm);
		return new ResponseEntity<ArrayList<PurchaseOrder>>(temp, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/updateSeen",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PurchaseOrder> updateSeen(@RequestBody PurchaseOrder po)  throws Exception {
		
		this.purchaseService.updatePurchaseOrderSeen(true, po.getId());
		return new ResponseEntity<PurchaseOrder>(po, HttpStatus.OK);
	}
}
