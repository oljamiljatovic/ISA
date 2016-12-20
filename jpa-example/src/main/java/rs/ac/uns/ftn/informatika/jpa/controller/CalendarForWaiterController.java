package rs.ac.uns.ftn.informatika.jpa.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import rs.ac.uns.ftn.informatika.jpa.domain.CalendarForWaiter;
import rs.ac.uns.ftn.informatika.jpa.service.CalendarForWaiterService;

@Controller 
@RequestMapping("/calendarForWaiterController")
public class CalendarForWaiterController {
	
	@Autowired
	private CalendarForWaiterService calendarForWaiterService;
	@RequestMapping(
			value = "/getCalendarForWaiter",
			method = RequestMethod.GET,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getCalendarForWaiter() throws Exception {
		//System.out.println("Usao u calendarForWaiterController/proba");
		ArrayList<CalendarForWaiter> cfw = this.calendarForWaiterService.getCalendarForWaiters();
		
		return new ResponseEntity<Object>(cfw, HttpStatus.OK);
	}
}
