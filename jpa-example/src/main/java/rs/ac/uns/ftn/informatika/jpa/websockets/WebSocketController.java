package rs.ac.uns.ftn.informatika.jpa.websockets;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import rs.ac.uns.ftn.informatika.jpa.domain.Offer;
import rs.ac.uns.ftn.informatika.jpa.domain.PurchaseOrder;
import rs.ac.uns.ftn.informatika.jpa.domain.User;
import rs.ac.uns.ftn.informatika.jpa.domain.users.RestaurantManager;
import rs.ac.uns.ftn.informatika.jpa.service.ManagerService;
import rs.ac.uns.ftn.informatika.jpa.service.OfferService;
import rs.ac.uns.ftn.informatika.jpa.service.PurchaseOrderService;


@RestController
public class WebSocketController {

	@Autowired
	Producer producer;
	
	@Autowired
	private OfferService offerService;
	@Autowired
	private PurchaseOrderService purchaseService;
	@Autowired
	private ManagerService managerService;
	
	
	@RequestMapping("/send/{topic}")
	public String sender(@PathVariable String topic, @RequestParam String newOrder){
		producer.sendMessageTo(topic, newOrder);
		return "OK-newOrder";
	}
	
	@RequestMapping("/acceptMeal")
	public String sender(@RequestParam String acceptMeal){
		ServletRequestAttributes attr = (ServletRequestAttributes) 
			    RequestContextHolder.currentRequestAttributes();
		HttpSession session= attr.getRequest().getSession(true);
		User u = (User) session.getAttribute("korisnik");
		String message =  "Kuvar " + u.getEmail()+" "+acceptMeal;
		producer.sendAcceptSignalFromCookToWaiter("acceptMeal",message);
		return "OK-acceptMeal";
	}
	
	@RequestMapping("/signalMeal")
	public String senderSignalMeal(@RequestParam String signalMeal){
		producer.sendPreparedSignalFromCookToWaiter("signalMeal",signalMeal);
		return "OK-signalMeal";
	}
	
	@RequestMapping("/acceptDrink")
	public String senderAcceptDrink(@RequestParam String acceptDrink){
		ServletRequestAttributes attr = (ServletRequestAttributes) 
			    RequestContextHolder.currentRequestAttributes();
		HttpSession session= attr.getRequest().getSession(true);
		User u = (User) session.getAttribute("korisnik");
		String message =  "Šanker " + u.getEmail()+" "+acceptDrink;
		producer.sendAcceptSignalFromBarmanToWaiter("acceptDrink",message);
		return "OK-acceptDrink";
	}
	
	@RequestMapping("/signalDrink")
	public String senderSignalDrink(@RequestParam String signalDrink){
		producer.sendPreparedSignalFromBarmanToWaiter("signalDrink",signalDrink);
		return "OK-signalDrink";
	}
	
	@RequestMapping("/acceptOffer")
	public String senderAcceptOffer(@RequestParam Long acceptOffer){
		PurchaseOrder accept= this.purchaseService.findOne(acceptOffer);
		String message = Long.toString(accept.getId())+";"+Long.toString(accept.getProvider().getId())
				+";"+Long.toString(accept.getOffer().getId())+";"+Boolean.toString(accept.isSeen());
		producer.sendAcceptSignalFromManagerToProvider("acceptOffer",message);
		return "OK-acceptDrink";
	}

}