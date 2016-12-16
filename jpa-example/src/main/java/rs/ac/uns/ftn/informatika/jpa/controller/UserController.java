package rs.ac.uns.ftn.informatika.jpa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
			consumes = MediaType.APPLICATION_JSON_VALUE)
			//produces = MediaType.APPLICATION_JSON_VALUE)
	public void createGreeting(
			@RequestBody User user) throws Exception {
		System.out.println("Usao u login");
				/*Greeting savedGreeting = greetingService.create(greeting);
		return new ResponseEntity<Greeting>(savedGreeting, HttpStatus.CREATED);*/
	//	return "sreca";
	}

}
