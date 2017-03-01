package rs.ac.uns.ftn.informatika.jpa.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import rs.ac.uns.ftn.informatika.jpa.domain.AssignReon;
import rs.ac.uns.ftn.informatika.jpa.domain.Bill;
import rs.ac.uns.ftn.informatika.jpa.domain.Drink;
import rs.ac.uns.ftn.informatika.jpa.domain.Meal;
import rs.ac.uns.ftn.informatika.jpa.domain.Offer;
import rs.ac.uns.ftn.informatika.jpa.domain.PurchaseOrder;
import rs.ac.uns.ftn.informatika.jpa.domain.RatingAll;
import rs.ac.uns.ftn.informatika.jpa.domain.Reon;
import rs.ac.uns.ftn.informatika.jpa.domain.Restaurant;
import rs.ac.uns.ftn.informatika.jpa.domain.Tablee;
import rs.ac.uns.ftn.informatika.jpa.domain.User;
import rs.ac.uns.ftn.informatika.jpa.domain.WorkSchedule;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Employee;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Provider;
import rs.ac.uns.ftn.informatika.jpa.domain.users.RestaurantManager;
import rs.ac.uns.ftn.informatika.jpa.repository.EmployeeRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.PurchaseOrderRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.ReonRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.RestaurantRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.TableRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.WorkScheduleRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestaurantIntegrationTest {
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	@Autowired
	private RestaurantService restaurantService;
	@Autowired
	private ManagerService managerService;
	@Autowired
	private UserService userService;
	@Autowired
	private DrinkService drinkService;
	@Autowired
	private MealService mealService;
	@Autowired
	private TableRepository tableRepository;
	@Autowired
	private ReonRepository reonRepository;
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private WorkScheduleService workScheduleService;
	@Autowired
	private WorkScheduleRepository workScheduleRepository;
	@Autowired
	private ReonService reonService;
	@Autowired
	private AssignReonService assignReonService;
	@Autowired
	private RatingAllService ratingAllService;
	@Autowired
	private BillService billService;
	@Autowired
	private OfferService offerService;
	@Autowired
	private PurchaseOrderService pcService;
	@Autowired
	private PurchaseOrderRepository pcRepository;
	@Autowired
	private ProviderService providerService;
	
	
	@Test										//1
	public void findAllRestaurant() {

		int x = ((ArrayList<Restaurant>)this.restaurantRepository.findAll()).size();
		assertThat(x).isEqualTo(5);
	}
	
	@Test										//2
	public void updateRestaurant() {

		Restaurant rest = this.restaurantRepository.findOne((long)4);
		String address = "Ilije Bircanina";
		rest.setAddress(address);
		restaurantService.updateRestaurant(rest);
		rest = this.restaurantRepository.findOne((long)4);
		assertEquals(address, rest.getAddress());
	}
	
	@Test									//3
	public void addRestaurantManager() {	
		
		Restaurant rest = this.restaurantRepository.findOne((long)4);
		RestaurantManager manager = new RestaurantManager("cao", "caocao", "restaurantManager", 
				"nedje", "caaao", "-", "miica",rest , "true");
		
		this.managerService.addManager(manager);
		ArrayList<User> user = this.userService.findByEmail("caaao");
		
		assertThat(user.size()).isGreaterThan(0);
	}
	
	@Test								//4
	public void deleteDrink() {	
		
		Drink drink = this.drinkService.getDrink((long)2);
		drink.setExist(false);
		this.drinkService.updateDrinkFlag(drink);
		boolean exist = this.drinkService.getDrink((long)2).isExist();
		assertFalse(exist);
	}
	
	@Test								//5
	public void updateMeal() {	
		
		Meal meal = this.mealService.getMeal((long)2);
		float price = 30;
		meal.setPrice(price);
		this.mealService.updateMeal(meal);
		assertThat(price).isEqualTo(this.mealService.getMeal((long)2).getPrice());
	}
	
	@Test								//6
	public void addTables() {
		int first = ((ArrayList<Tablee>)this.tableRepository.findAll()).size();
		User foundUser  = userService.findUserByEmailAndPassword("mmica", "mica");
		ServletRequestAttributes attr = (ServletRequestAttributes) 
			    RequestContextHolder.currentRequestAttributes();
			HttpSession session= attr.getRequest().getSession(true); 
			session.setAttribute("korisnik", foundUser);
		RestaurantManager manag = this.managerService.getManager("mmica");
		Restaurant rest = manag.getRestaurant();
		Reon reon = this.reonRepository.findOne((long)1);
		Tablee tab = new Tablee(reon,rest,true);
		this.tableRepository.save(tab);
		int second = ((ArrayList<Tablee>)this.tableRepository.findAll()).size();
		int x = second-first;
		assertEquals(x, 1);
	}
	

	@Test								//7
	public void addShift() {
		int x = ((ArrayList<WorkSchedule>)this.workScheduleRepository.findAll()).size();
		User foundUser  = userService.findUserByEmailAndPassword("mmica", "mica");
		ServletRequestAttributes attr = (ServletRequestAttributes) 
			    RequestContextHolder.currentRequestAttributes();
			HttpSession session= attr.getRequest().getSession(true); 
			session.setAttribute("korisnik", foundUser);
		RestaurantManager manag = this.managerService.getManager("mmica");
		Restaurant rest = manag.getRestaurant();
		Employee empl = this.employeeRepository.findById((long)13);
		String dateStart = "2017-03-03";
		String dateEnd = "2017-04-04";
		String shift = "prva";
		
		WorkSchedule ws = new WorkSchedule(dateStart, dateEnd, shift, rest, empl);
		this.workScheduleService.createSchedule(ws);

		int y = ((ArrayList<WorkSchedule>)this.workScheduleRepository.findAll()).size();
		int z = y-x;
		assertEquals(z, 1);
		
	}
	
	@Test								//8
	public void updateShift() {
		
		WorkSchedule ws = this.workScheduleRepository.findOne((long)2);
		String shift = "treca";
		ws.setShift(shift);
		this.workScheduleService.update(ws);
		ws = this.workScheduleRepository.findOne((long)2);
		assertEquals(shift, ws.getShift());
		
	}
	
	
	@Test								//9
	public void findAllShifts() {
		User foundUser  = userService.findUserByEmailAndPassword("mmica", "mica");
		ServletRequestAttributes attr = (ServletRequestAttributes) 
			    RequestContextHolder.currentRequestAttributes();
			HttpSession session= attr.getRequest().getSession(true); 
			session.setAttribute("korisnik", foundUser);
		RestaurantManager manag = this.managerService.getManager("mmica");
		Restaurant rest = manag.getRestaurant();
		ArrayList<WorkSchedule> fs = this.workScheduleService.findByRestaurant(rest);
		

		assertThat(fs.size()).isGreaterThan(0);
	}
	
	
	@Test								//10
	public void addAsignReon() {

		int x = ((ArrayList<AssignReon>)this.assignReonService.findAll()).size();
		User u  = userService.findUserByEmailAndPassword("mmica", "mica");
		ServletRequestAttributes attr = (ServletRequestAttributes) 
			    RequestContextHolder.currentRequestAttributes();
		HttpSession session= attr.getRequest().getSession(true); 
		session.setAttribute("korisnik", u);
		
		AssignReon reon = new AssignReon();
		RestaurantManager rm = this.managerService.getManager(u.getEmail());
		reon.setRestaurant(rm.getRestaurant());
		Employee e = this.employeeService.findById((long)8);
		reon.setWaiter(e);
		Reon rr = this.reonService.findOne((long)2);
		reon.setReon(rr);
		this.assignReonService.createAssignReon(reon);
		int y = ((ArrayList<AssignReon>)this.assignReonService.findAll()).size();
		assertThat(y-x).isEqualTo(1);
	}
	
	@Test								//11
	public void deleteAsignReon() {

		int x = ((ArrayList<AssignReon>)this.assignReonService.findAll()).size();
		this.assignReonService.delete((long)1);
		int y = ((ArrayList<AssignReon>)this.assignReonService.findAll()).size();
		assertThat(x-y).isEqualTo(1);
	}
	
	@Test								//12
	public void ratingRestaurant() {

		User u  = userService.findUserByEmailAndPassword("mmica", "mica");
		ServletRequestAttributes attr = (ServletRequestAttributes) 
			    RequestContextHolder.currentRequestAttributes();
		HttpSession session= attr.getRequest().getSession(true); 
		session.setAttribute("korisnik", u);
		RestaurantManager rm= this.managerService.getManager(u.getEmail());
		Restaurant restaurant = restaurantService.getRestaurant(rm.getRestaurant().getId());
		ArrayList<RatingAll> temp = this.ratingAllService.findByRestaurant(restaurant);
		
		int br = temp.size();
		int suma = 0;
		for(int i=0; i<br; i++)
			suma = suma + temp.get(i).getRestaurantRating();
		
		float prosek = (float) (suma/br);
		assertThat(prosek).isEqualTo(3);
	}
	
	
	@Test								//13
	public void ratingEmployee() {

		Employee e = this.employeeService.findById((long)8);
		ArrayList<RatingAll> temp = this.ratingAllService.findByWaiter(e);

		int br = temp.size();
		int suma = 0;
		for(int i=0; i<br; i++)
			suma = suma + temp.get(i).getServiceRating();
			
		float prosek = (float) (suma/br);
			
		assertThat(prosek).isGreaterThan(0);
	}
	
	@Test								//14
	public void ratingMeals() {
		
		
		Meal meal = this.mealService.findByName("supa");
		ArrayList<RatingAll> temp = this.ratingAllService.findByMeals(meal.getId());

		int br = temp.size();
		int suma = 0;
		for(int i=0; i<br; i++)
			suma = suma + temp.get(i).getMealRating();
			
		int prosek = suma/br;
			
	assertThat(4).isEqualTo(prosek);
	}
	
	@Test								//15
	public void getBillsFromRestaurant() {
		

		User u  = userService.findUserByEmailAndPassword("mmica", "mica");
		ServletRequestAttributes attr = (ServletRequestAttributes) 
			    RequestContextHolder.currentRequestAttributes();
		HttpSession session= attr.getRequest().getSession(true); 
		session.setAttribute("korisnik", u);
		RestaurantManager rm= this.managerService.getManager(u.getEmail());
		
		ArrayList<Employee> employees = this.employeeService.getEmployeesOfRestaurant(rm.getRestaurant());

		ArrayList<Bill> cfw = this.billService.getBills();
		ArrayList<Bill> temp = new ArrayList<Bill>();
		for(int i=0; i<cfw.size(); i++){
			for(int j=0; j<employees.size(); j++){
				Long first = employees.get(j).getId();
				Long second = cfw.get(i).getWaiter().getId();
				if(first.equals(second))
					temp.add(cfw.get(i));
			}
		}
		int suma = 0;
		for(int i=0; i<temp.size(); i++){
			suma = suma + temp.get(i).getBill();
		}
			
		assertThat(suma).isEqualTo(530);
	}
	
	
	@Test								//16
	public void getBillsFromEmployee() {
		
		Employee empl = this.employeeService.findById((long)8);

		ArrayList<Bill> cfw = this.billService.getBills();
		ArrayList<Bill> temp = new ArrayList<Bill>();
		for(int i=0; i<cfw.size(); i++){
				Long first = cfw.get(i).getWaiter().getId();
				if(first.equals(empl.getId()))
					temp.add(cfw.get(i));
		}
		
		int suma =0;
		for(int i=0; i<temp.size(); i++)
			suma = suma+temp.get(i).getBill();
		
		assertThat(suma).isEqualTo(530);
	}
	
	@Test								//17
	public void addOffer() {

		int x = ((ArrayList<Offer>)this.offerService.getOffers()).size();
		User u  = userService.findUserByEmailAndPassword("mmica", "mica");
		ServletRequestAttributes attr = (ServletRequestAttributes) 
			    RequestContextHolder.currentRequestAttributes();
		HttpSession session= attr.getRequest().getSession(true); 
		session.setAttribute("korisnik", u);
		RestaurantManager rm= this.managerService.getManager(u.getEmail());
		Restaurant restaurant = rm.getRestaurant();
		Offer offer = new Offer("2017-05-05", 1L , "pice", restaurant, 40, false);
		this.offerService.addOffer(offer);
		int y = ((ArrayList<Offer>)this.offerService.getOffers()).size();

		assertThat(y-x).isEqualTo(1);
		
	}
	
	@Test								//18
	public void addPurchaseOrder() {

		int x = ((ArrayList<PurchaseOrder>)this.pcRepository.findAll()).size();

		User u  = userService.findUserByEmailAndPassword("pera", "perapera");
		ServletRequestAttributes attr = (ServletRequestAttributes) 
			    RequestContextHolder.currentRequestAttributes();
		HttpSession session= attr.getRequest().getSession(true); 
		session.setAttribute("korisnik", u);
		Provider provider= this.providerService.getProvider(u.getEmail());
		Offer of = this.offerService.getOffer((long)2);
		PurchaseOrder po = new PurchaseOrder();
		po.setOffer(of);
		po.setProvider(provider);
		po.setRestaurant(provider.getRestaurant());
		po.setPrice("140");
		po.setFlag(0L);
		po.setSeen(false);
		po.setTimeDeliver("2");
		this.pcService.addPurchaseOrder(po);
		
		int y = ((ArrayList<PurchaseOrder>)this.pcRepository.findAll()).size();

		assertThat(y-x).isEqualTo(1);
	}
	
	@Test								//19
	public void updatePurchaseOrder() {

		User u  = userService.findUserByEmailAndPassword("pera", "perapera");
		ServletRequestAttributes attr = (ServletRequestAttributes) 
			    RequestContextHolder.currentRequestAttributes();
		HttpSession session= attr.getRequest().getSession(true); 
		session.setAttribute("korisnik", u);
		Provider provider= this.providerService.getProvider(u.getEmail());
		Offer offer = this.offerService.getOffer(1L);
		PurchaseOrder po = this.pcRepository.findByProviderAndOffer(provider, offer);
		po.setPrice("222");
		this.pcService.updatePurchaseOrder(po);
		po = this.pcRepository.findByProviderAndOffer(provider, offer);
		
		assertNotEquals("250", po.getPrice());
	}
	
	
	@Test								//20
	public void updatePurchaseOrderFlag() {
		

		PurchaseOrder po = this.pcRepository.findOne(1L);
		Offer of = this.offerService.getOffer(po.getOffer().getId());
		of.setAccepted(true);
		this.offerService.updateFlag(true, of.getId());
		po.setOffer(of);
		ArrayList<PurchaseOrder> por = this.pcService.getPurchaseOrderByOffer(of);
		for(int i=0; i<por.size(); i++){
			this.pcService.updateFlag(po.getFlag(), por.get(i).getId());
		}
		

		PurchaseOrder po2 = this.pcRepository.findOne(2L);
		assertNotEquals(po2.getFlag(), po2.getId());
	}
	
	
	@Test								//21
	public void updateProvider() {
		

		User u  = userService.findUserByEmailAndPassword("pera", "perapera");
		ServletRequestAttributes attr = (ServletRequestAttributes) 
			    RequestContextHolder.currentRequestAttributes();
		HttpSession session= attr.getRequest().getSession(true); 
		session.setAttribute("korisnik", u);
		Provider provider= this.providerService.getProvider(u.getEmail());
		String name = provider.getName();
		provider.setName("caaaaooooooo");
		this.providerService.updateProvider(provider);
		
		assertNotEquals(name, provider.getName());
	}
	
	@Test								//22
	public void addEmployee() {
		

		int x = ((ArrayList<Employee>)this.employeeRepository.findAll()).size();

		User u  = userService.findUserByEmailAndPassword("mmica", "mica");
		ServletRequestAttributes attr = (ServletRequestAttributes) 
			    RequestContextHolder.currentRequestAttributes();
		HttpSession session= attr.getRequest().getSession(true); 
		session.setAttribute("korisnik", u);
		RestaurantManager rm= this.managerService.getManager(u.getEmail());
		Restaurant	r = restaurantService.getRestaurant(rm.getRestaurant().getId());
		Employee empl = new Employee("desa", "todic", "1994-05-05", "waiter", "M", "41", r, "desa", "true", "toda");
		this.employeeService.addEmployee(empl);
		
		int y = ((ArrayList<Employee>)this.employeeRepository.findAll()).size();
		
		assertThat(y-x).isEqualTo(1);
	}
	
	
	
}
