package rs.ac.uns.ftn.informatika.jpa.controller;

import java.util.ArrayList;

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

import rs.ac.uns.ftn.informatika.jpa.domain.Bill;
import rs.ac.uns.ftn.informatika.jpa.domain.Order;
import rs.ac.uns.ftn.informatika.jpa.domain.User;
import rs.ac.uns.ftn.informatika.jpa.service.BillService;
import rs.ac.uns.ftn.informatika.jpa.service.OrderService;
import rs.ac.uns.ftn.informatika.jpa.service.WorkScheduleService;

@Controller 
@RequestMapping("/billController")
public class BillController {
	@Autowired
	private BillService billService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private WorkScheduleService workScheduleService;
	
	@RequestMapping(
			value = "/getBills",
			method = RequestMethod.GET,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getBill() throws Exception {
		ArrayList<Bill> cfw = this.billService.getBills();		
		return new ResponseEntity<Object>(cfw, HttpStatus.OK);
	}
	@RequestMapping(
			value = "/getMyBills",
			method = RequestMethod.GET,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getMyBills() throws Exception {
		ServletRequestAttributes attr = (ServletRequestAttributes) 
			    RequestContextHolder.currentRequestAttributes();
		HttpSession session= attr.getRequest().getSession(true);
		User u = (User) session.getAttribute("korisnik");
		System.out.println("Billl user "+u.getEmail()+"  id"+u.getId());
		ArrayList<Bill> cfw = this.billService.findByWaiter_id(u.getId());
		System.out.println("Duzina bills "+cfw.size());
		return new ResponseEntity<Object>(cfw, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/addBill",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Bill> addBill(
			@RequestBody Bill bill) throws Exception {
		ServletRequestAttributes attr = (ServletRequestAttributes) 
			    RequestContextHolder.currentRequestAttributes();
		HttpSession session= attr.getRequest().getSession(true);
		User u = (User) session.getAttribute("korisnik");		
		Long orderId = bill.getOrder_id();
		Order orderForClose = this.orderService.findById(orderId);
		//////////////////////////
		orderForClose.setBarman_state("kraj");
		orderForClose.setCook_state("kraj");
		Order changedOrder = orderService.update(orderForClose, orderId);
		/////////////////////////
		Long waiterIdFromOrder = orderForClose.getWaiter_id();
		Long waiterIdFromSession = u.getId();
		///ako onaj ko kreira racun i onaj na kome je porudzbina nisu isti
		//provjeri da li je se onom sa porudzbine zavrsila smjena
		//ako jest onda provjeri koje je duze opsluzivao
		if(!waiterIdFromOrder.equals(waiterIdFromSession)){
			//ArrayList<WorkSchedule> 
			bill.setWaiter_id(waiterIdFromSession);
		}else{
			bill.setWaiter_id(waiterIdFromSession);
		}
		Bill addedBill = billService.addNew(bill);
		return new ResponseEntity<Bill>(addedBill, HttpStatus.OK);
	}
	
}
