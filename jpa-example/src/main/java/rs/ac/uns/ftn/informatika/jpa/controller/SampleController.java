package rs.ac.uns.ftn.informatika.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import rs.ac.uns.ftn.informatika.jpa.domain.Hotel;
import rs.ac.uns.ftn.informatika.jpa.service.CityService;
import rs.ac.uns.ftn.informatika.jpa.service.HotelService;

@Controller
public class SampleController {

	@Autowired
	private CityService cityService;
	

	@Autowired
	private HotelService hotelService;

	@GetMapping("/city")
	@ResponseBody
	@Transactional(readOnly = true)
	public String getCityName() {
		return this.cityService.getCity("Bath", "UK").getName();
	}
	
/*	@GetMapping("/user")
	@ResponseBody
	@Transactional(readOnly = true)
	public String getUserName() {
		System.out.println("Pogodio je metodu getUserName");
		return this.userService.getUser("Olja", "Oljka").getName();
	}*/
	
	
	@GetMapping("/hotel")
	@ResponseBody
	@Transactional(readOnly = true)
	public String getReview() {
		Hotel hotel = hotelService.getHotel(this.cityService.getCity("Bath", "UK"), "The Bath Priory Hotel");
		return this.hotelService.getReview(hotel, 5).getTitle() + "-" + this.hotelService.getReview(hotel, 5).getDetails();
	}

}
