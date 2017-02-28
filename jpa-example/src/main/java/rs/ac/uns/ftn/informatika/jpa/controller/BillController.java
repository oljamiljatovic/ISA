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
import rs.ac.uns.ftn.informatika.jpa.domain.Reservation;
import rs.ac.uns.ftn.informatika.jpa.domain.ReservedTables;
import rs.ac.uns.ftn.informatika.jpa.domain.User;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Employee;
import rs.ac.uns.ftn.informatika.jpa.domain.users.RestaurantManager;
import rs.ac.uns.ftn.informatika.jpa.service.BillService;
import rs.ac.uns.ftn.informatika.jpa.service.EmployeeService;
import rs.ac.uns.ftn.informatika.jpa.service.ManagerService;
import rs.ac.uns.ftn.informatika.jpa.service.OrderService;
import rs.ac.uns.ftn.informatika.jpa.service.ReservationService;
import rs.ac.uns.ftn.informatika.jpa.service.ReservedTablesService;
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
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private ManagerService managerService;
	
	@Autowired
	private ReservedTablesService reservedTablesService;
	
	@Autowired
	private ReservationService reservationService;
	
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
		Employee waiter = employeeService.findById(u.getId());
		ArrayList<Bill> bills = this.billService.findByWaiter(waiter);
		System.out.println("Duzina bills "+bills.size());
		return new ResponseEntity<Object>(bills, HttpStatus.OK);
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
		Long orderId = bill.getOrderBill().getId();
		Order orderForClose = this.orderService.findById(orderId);
		Employee waiter = employeeService.findById(u.getId());
		bill.setWaiter(waiter);
		bill.setOrderBill(orderForClose);
		Bill addedBill = billService.addNew(bill);
		orderForClose.setBarman_state("kraj");
		orderForClose.setCook_state("kraj");
		Order changedOrder = orderService.update(orderForClose, orderId);
		
		//dodala Olja
		Reservation reservationToChange = changedOrder.getReservation();
		System.out.println("restaurant "+reservationToChange.getIdRestaurant());
		System.out.println("reservisani sto 0"+reservationToChange.getReservedTables().get(0));
		System.out.println("date "+reservationToChange.getDate());
		System.out.println("time "+reservationToChange.getTime());
		System.out.println("duration "+reservationToChange.getDuration());
		
		for(int i = 0; i<reservationToChange.getReservedTables().size(); i++){ //Tablee
			
			
			ReservedTables temp = reservedTablesService.findReservedTablesByAll(reservationToChange.getIdRestaurant(),
					reservationToChange.getReservedTables().get(i), reservationToChange.getDate(), reservationToChange.getTime(), reservationToChange.getDuration());
			if(temp!=null){
				reservedTablesService.Delete(temp);
				System.out.println("sto za brisanje "+temp.getId());
			}
			
		}
		
		ArrayList<Order> orders = orderService.findByReservation(reservationToChange);
		System.out.println("orders size "+orders.size());
		boolean kraj = true;
		for(int i=0;i<orders.size();i++){
			Order order = orders.get(i);
			if(!order.getBarman_state().equals("kraj") && !order.getBarman_state().equals("kraj")){
				kraj = false;
				System.out.println("nije krajjj");
				break;
			}
		}
		if(kraj){
			reservationToChange.setFlag("neaktivna");
		}
		reservationService.update(reservationToChange, reservationToChange.getId());
		
		return new ResponseEntity<Bill>(addedBill, HttpStatus.OK);
	}
	
	
	@RequestMapping(
			value = "/getBillsOfRestaurant",
			method = RequestMethod.GET,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<Bill>> getBillsOfRestaurant() throws Exception {
		
		ArrayList<Bill> cfw = this.billService.getBills();
		ArrayList<Bill> temp = new ArrayList<Bill>();
		ServletRequestAttributes attr = (ServletRequestAttributes) 
			    RequestContextHolder.currentRequestAttributes();
		HttpSession session= attr.getRequest().getSession(true);
		User u = (User) session.getAttribute("korisnik");
		RestaurantManager rm= this.managerService.getManager(u.getEmail());
		ArrayList<Employee> employees = this.employeeService.getEmployeesOfRestaurant(rm.getRestaurant());

		for(int i=0; i<cfw.size(); i++){
			for(int j=0; j<employees.size(); j++){
				Long first = employees.get(j).getId();
				Long second = cfw.get(i).getWaiter().getId();
				if(first.equals(second))
					temp.add(cfw.get(i));
			}
		}
		return new ResponseEntity<ArrayList<Bill>>(temp, HttpStatus.OK);
	}
	
	
	@RequestMapping(
			value = "/getBillsOfWaiter",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<Bill>> getBillsOfWaiter(@RequestBody Employee empl) throws Exception {
		ArrayList<Bill> cfw = this.billService.getBills();
		ArrayList<Bill> temp = new ArrayList<Bill>();
		for(int i=0; i<cfw.size(); i++){
				Long first = cfw.get(i).getWaiter().getId();
				if(first.equals(empl.getId()))
					temp.add(cfw.get(i));
		}
		
		return new ResponseEntity<ArrayList<Bill>>(temp, HttpStatus.OK);
	}
	
}
