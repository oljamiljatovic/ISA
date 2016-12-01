package rs.ac.uns.ftn.informatika.jpa.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import rs.ac.uns.ftn.informatika.jpa.domain.users.Guest;
import rs.ac.uns.ftn.informatika.jpa.service.GuestService;


@Controller
public class GuestController {

	@Autowired
	private GuestService guestService;
	
	
	@GetMapping("/guests")
	@ResponseBody
	@Transactional(readOnly = true)
	public ArrayList<String> getGuests() {
	
		ArrayList<String> guestUsernames= new ArrayList<String>();
		ArrayList<Guest> guests = this.guestService.getGuests();
		
		for(int i = 0 ; i <guests.size(); i++){
			guestUsernames.add(guests.get(i).getUsername());
			
		}
		
		return guestUsernames;
	}
	//@RequestMapping(value="/schedule", method = RequestMethod.POST)
	//public void action(@RequestParam(value = "param[]") String[] paramValues){...}
	@RequestMapping(
			value = "/log/{id}/{id2}",
			method = RequestMethod.POST)
	public String getGreeting(@RequestParam("id") String username, @RequestParam("id2") String password) {
	
		ArrayList<Guest> guests = this.guestService.getGuests();

		for(int i = 0 ; i <guests.size(); i++){
			if(guests.get(i).getUsername().equals(username)){
				if(guests.get(i).getPassword().equals(password)){
					return "Uspjesno prijavljen";
				}else{
					return "Pogresna lozinka";
				}
			}else{
				return "Ne postoji korisnicko ime";
			}
			
		}
		return null;
	}
}
