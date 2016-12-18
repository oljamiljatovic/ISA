package rs.ac.uns.ftn.informatika.jpa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import rs.ac.uns.ftn.informatika.jpa.domain.User;

@Controller 
@RequestMapping("/loginController")
public class UserController {

	@RequestMapping("/")
	public String Indexas() {
	
		return "index.html";
	}
	
	@RequestMapping(
			value = "/login",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> createGreeting(
			@RequestBody User user) throws Exception {
		System.out.println("Usao u login");
		System.out.println("User :"+ user.getUsername());
		System.out.println("User :"+ user.getPassword());
		
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	
	
}
