package rs.ac.uns.ftn.informatika.jpa.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.websocket.Session;

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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import rs.ac.uns.ftn.informatika.jpa.domain.User;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Guest;
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
			/*@Valid*/ @RequestBody User user) throws Exception {
		
		
		User foundUser  = userService.findUserByEmailAndPassword(user.getEmail(), user.getPassword());
		
		
	
		if(foundUser == null){
			System.out.println("NULL");
		}
		
		ServletRequestAttributes attr = (ServletRequestAttributes) 
			    RequestContextHolder.currentRequestAttributes();
			HttpSession session= attr.getRequest().getSession(true); 
			session.setAttribute("korisnik", foundUser);
			
			
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
	
	

	@RequestMapping(
			value = "/isValidate",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> findGuestByEmail() throws Exception {
		
		ServletRequestAttributes attr = (ServletRequestAttributes) 
			    RequestContextHolder.currentRequestAttributes();
		
		HttpSession session= attr.getRequest().getSession(true);
		User u = (User) session.getAttribute("korisnik");
		if(u!=null)
			System.out.println("Korisnik u sesiji"+u.getEmail());

		return new ResponseEntity<User>(u, HttpStatus.OK);
	
	}
	
	@RequestMapping(
			value = "/logout",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> logout() throws Exception {
		
		ServletRequestAttributes attr = (ServletRequestAttributes) 
			    RequestContextHolder.currentRequestAttributes();
		
		HttpSession session= attr.getRequest().getSession(true);
		session.invalidate();
		String ok = "ok";
		return new ResponseEntity<String>(ok, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/checkSession",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> checkSession() throws Exception {
		
		ServletRequestAttributes attr = (ServletRequestAttributes) 
			    RequestContextHolder.currentRequestAttributes();
		
		HttpSession session= attr.getRequest().getSession(true);
		User u = (User) session.getAttribute("korisnik");
		String status = "ok";
		if(u!=null){
			System.out.println("Korisnik u sesiji"+u.getEmail());
		}else{
			status= "notOk";
		}

		return new ResponseEntity<String>(status, HttpStatus.OK);
	
	}


	
}
