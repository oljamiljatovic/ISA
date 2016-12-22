package rs.ac.uns.ftn.informatika.jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import rs.ac.uns.ftn.informatika.jpa.domain.User;
import rs.ac.uns.ftn.informatika.jpa.service.CityService;
import rs.ac.uns.ftn.informatika.jpa.service.UserService;

@Controller 
@RequestMapping("/userController")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	@RequestMapping("/")
	public String Indexas() {
	
		return "index.html";
	}
	
	@RequestMapping(
			value = "/logIn",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> logIn(
			@RequestBody User user) throws Exception {
		
		
		User foundUser  = userService.findUserByEmailAndPassword(user.getEmail(), user.getPassword());
		if(foundUser == null){
			System.out.println("NULL");
		}
		return new ResponseEntity<User>(foundUser, HttpStatus.OK);
	}
	
	

	@RequestMapping(
			value = "/regIn",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> regIn(
			@RequestBody User user) throws Exception {
	
		
		User addedUser = userService.createNew(user);
		
		return new ResponseEntity<User>(addedUser, HttpStatus.OK);
	}
	
	
	/*@RequestMapping(
			value = "/change/{id}",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> update(
			@RequestBody User user, @PathVariable Long id) throws Exception {
	
		
		User addedUser = userService.update(user, id);
		
		return new ResponseEntity<User>(addedUser, HttpStatus.OK);
	}
	*/
	
	

	
}
