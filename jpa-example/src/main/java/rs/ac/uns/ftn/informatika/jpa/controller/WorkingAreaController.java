package rs.ac.uns.ftn.informatika.jpa.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import rs.ac.uns.ftn.informatika.jpa.domain.WorkingArea;
import rs.ac.uns.ftn.informatika.jpa.service.WorkingAreaService;

@Controller 
@RequestMapping("/workingAreaController")
public class WorkingAreaController {
	@Autowired
	private WorkingAreaService workingAreaService;
	@RequestMapping(
			value = "/getWorkingArea",
			method = RequestMethod.GET,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getWorkingArea() throws Exception {
		//System.out.println("Usao u calendarForWaiterController/proba");
		ArrayList<WorkingArea> cfw = this.workingAreaService.getWorkingAreas();
		
		return new ResponseEntity<Object>(cfw, HttpStatus.OK);
	}
}
