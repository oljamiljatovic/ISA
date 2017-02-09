package rs.ac.uns.ftn.informatika.jpa.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import rs.ac.uns.ftn.informatika.jpa.domain.User;
import rs.ac.uns.ftn.informatika.jpa.domain.WorkSchedule;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Employee;
import rs.ac.uns.ftn.informatika.jpa.service.WorkScheduleService;

@Controller 
@RequestMapping("/waiterController")
public class WaiterController {
	@Autowired
	private WorkScheduleService workScheduleService;
	
	@RequestMapping(
			value = "/getWorkSchedules",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<WorkSchedule>> getWorkSchedules()  throws Exception {
		
		ServletRequestAttributes attr = (ServletRequestAttributes) 
			    RequestContextHolder.currentRequestAttributes();
		HttpSession session= attr.getRequest().getSession(true);
		User u = (User) session.getAttribute("korisnik");
		ArrayList<WorkSchedule> temp = new ArrayList<WorkSchedule>();
		if(u.getRole().equals("waiter")){
			ArrayList<WorkSchedule> ws = workScheduleService.findAll();
			Long id =u.getId();
			Long worker_id = null;
			for(int i=0;i<ws.size();i++){
				worker_id = ws.get(i).getWorker_id();
				if(id.equals(worker_id)){
					temp.add(ws.get(i));
				}
			}
		}
		
		return new ResponseEntity<ArrayList<WorkSchedule>>(temp, HttpStatus.OK);
	}
}
