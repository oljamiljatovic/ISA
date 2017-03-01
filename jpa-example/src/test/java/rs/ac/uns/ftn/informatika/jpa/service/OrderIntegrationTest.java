package rs.ac.uns.ftn.informatika.jpa.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import rs.ac.uns.ftn.informatika.jpa.domain.AssignReon;
import rs.ac.uns.ftn.informatika.jpa.domain.Bill;
import rs.ac.uns.ftn.informatika.jpa.domain.Order;
import rs.ac.uns.ftn.informatika.jpa.domain.RatingAll;
import rs.ac.uns.ftn.informatika.jpa.domain.Reservation;
import rs.ac.uns.ftn.informatika.jpa.domain.Restaurant;
import rs.ac.uns.ftn.informatika.jpa.domain.Tablee;
import rs.ac.uns.ftn.informatika.jpa.domain.WorkSchedule;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Employee;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Guest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderIntegrationTest {
	@Autowired
	OrderService orderService;
	@Autowired
	BillService billService;
	@Autowired 
	RatingAllService ratingAllService;
	@Autowired 
	EmployeeService employeeService;
	@Autowired 
	RestaurantService restaurantService;
	@Autowired 
	TableService tableService;
	@Autowired 
	ReservationService reservationService;
	@Autowired 
	MealService mealService;
	@Autowired 
	DrinkService drinkService;
	@Autowired
	AssignReonService assignReonService;
	@Autowired
	WorkScheduleService workScheduleService;
	@Autowired
	GuestService guestService;

	@Test//1
	public void findFirstOrder() {
		Restaurant restaurant = restaurantService.getRestaurant(1L);
		ArrayList<Employee> waiters = employeeService.getWaitersOfRestaurant("waiter", restaurant);
		Employee waiter = waiters.get(0);
		ArrayList<Order> orders = orderService.findByWaiter(waiter);
		Order order = orders.get(0);
		Assert.assertNotNull(order);
	}
	
	@Test//2
	public void addOrder() {
		Restaurant restaurant = restaurantService.getRestaurant(1L);
		System.out.println(restaurant.getName());
		ArrayList<Employee> waiters = employeeService.getWaitersOfRestaurant("waiter", restaurant);
		Employee waiter = waiters.get(0);
		System.out.println(waiter.getName());
		ArrayList<Tablee> tables = tableService.findByRestaurant(restaurant);
		Tablee table = tables.get(0);
		System.out.println(table.getId());
		ArrayList<Reservation> reservations = reservationService.findByIdRestaurantAndDate(restaurant, "2017-02-28");
		Reservation reservation = reservations.get(0);
		System.out.println(reservation.getFlag());
		String barmanState  = "kreirana";
		String cookState = "kreirana";
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date timeOfOrder = new Date();
		System.out.println(dateFormat.format(timeOfOrder));
		Order order = new Order();
		order.setWaiter(waiter);
		order.setReservation(reservation);
		order.setRestaurant(restaurant);
		order.setTablee(table);
		order.setBarman_state(barmanState);
		order.setCook_state(cookState);
		order.setTimeOfOrder(timeOfOrder);
		order.setMeals(null);
		order.setDrinks(null);
		ArrayList<Order> ordersBefore = orderService.findByRestaurant(restaurant);
		int sizeBefore = ordersBefore.size();
		System.out.println(sizeBefore);
		Order addedOrder = orderService.createNew(order);
		System.out.println(addedOrder.getBarman_state());
		ArrayList<Order> ordersAfter = orderService.findByRestaurant(restaurant);
		int sizeAfter = ordersAfter.size();
		System.out.println(sizeAfter);
		int size = sizeBefore+1;
		assertThat(sizeAfter).isEqualTo(size);
	}
	
	@Test//3
	public void updateOrder() {
		Order order = orderService.findById(1L);
		System.out.println(order.getBarman_state());
		order.setBarman_state("kraj");
		Order updateOrder = orderService.update(order, order.getId());
		System.out.println(updateOrder.getBarman_state());
		assertThat(updateOrder.getBarman_state()).isEqualTo("kraj");
	}
	
	@Test//4
	public void checkBill() {
		Restaurant restaurant = restaurantService.getRestaurant(1L);
		ArrayList<Employee> waiters = employeeService.getWaitersOfRestaurant("waiter", restaurant);
		Employee waiter = waiters.get(0);
		ArrayList<Bill> bills = billService.findByWaiter(waiter);
		Assert.assertNotNull(bills);
	}
	
	@Test//5
	public void addBill() {
		Order order = orderService.findById(1L);
		Restaurant restaurant = restaurantService.getRestaurant(1L);
		ArrayList<Employee> waiters = employeeService.getWaitersOfRestaurant("waiter", restaurant);
		Employee waiter = waiters.get(0);
		int money = 800;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date dateOfBill = new Date();
		Bill bill = new Bill();
		bill.setOrderBill(order);
		bill.setDateOfBill(dateOfBill);
		bill.setBill(money);
		bill.setWaiter(waiter);
		ArrayList<Bill> billBefore = billService.findByWaiter(waiter);
		int sizeBefore = billBefore.size();
		System.out.println(sizeBefore);
		Bill addedBill = billService.addNew(bill);
		ArrayList<Bill> billAfter = billService.findByWaiter(waiter);
		int sizeAfter = billAfter.size();
		System.out.println(sizeAfter);
		int size = sizeBefore+1;
		assertThat(sizeAfter).isEqualTo(size);
	}
	

	
	@Test//6
	public void updateProfileOfEmployee() {
		Restaurant restaurant = restaurantService.getRestaurant(1L);
		Employee employee = employeeService.getEmployeesOfRestaurant(restaurant).get(0);
		System.out.println(employee.getConfNumber());
		String confNumber = "XXL";
		employee.setConfNumber(confNumber);
		Employee updateEmployee = employeeService.update(employee, employee.getId());
		System.out.println(updateEmployee.getConfNumber());
		assertThat(updateEmployee.getConfNumber()).isEqualTo(confNumber);
	}
	
	@Test//7
	public void changePassword() {
		Restaurant restaurant = restaurantService.getRestaurant(1L);
		ArrayList<Employee> employees = employeeService.getEmployeesOfRestaurant(restaurant);
		Employee employee = employees.get(0);
		System.out.println(employee.getPassword());
		String password = "dragana.mirkovic";
		employee.setPassword(password);
		Employee updateEmployee = employeeService.update(employee, employee.getId());
		System.out.println(updateEmployee.getPassword());
		assertThat(updateEmployee.getPassword()).isEqualTo(password);
	}
	
	@Test//8
	public void waitersReons() {
		Restaurant restaurant = restaurantService.getRestaurant(1L);
		ArrayList<Employee> employees = employeeService.getWaitersOfRestaurant("waiter", restaurant);
		Employee waiter = employees.get(0);
		ArrayList<AssignReon> assignReons = assignReonService.findByWaiter(waiter);
		AssignReon assignReon = assignReons.get(0);
		String expected = "North";
		String actual = assignReon.getReon().getName();
		System.out.println(actual);
		Assert.assertEquals(expected, actual);
	}
	
	@Test//9
	public void waitersWorkSchedule() {
		Restaurant restaurant = restaurantService.getRestaurant(1L);
		ArrayList<Employee> employees = employeeService.getWaitersOfRestaurant("waiter", restaurant);
		Employee waiter = employees.get(0);
		ArrayList<WorkSchedule> workSchedules = workScheduleService.findByWorker_id(waiter.getId());
		WorkSchedule workSchedule = workSchedules.get(0);
		String expected = "prva";
		String actual = workSchedule.getShift();
		System.out.println(actual);
		Assert.assertEquals(expected, actual);
	}
	
	@Test//10
	public void cooksWorkSchedule() {
		Restaurant restaurant = restaurantService.getRestaurant(1L);
		ArrayList<Employee> employees = employeeService.getWaitersOfRestaurant("cook", restaurant);
		Employee cook = employees.get(0);
		ArrayList<WorkSchedule> workSchedules = workScheduleService.findByWorker_id(cook.getId());
		WorkSchedule workSchedule = workSchedules.get(0);
		String expectedStart = "2017-02-10";
		String actualStart = workSchedule.getDateStart();
		String expectedEnd = "2017-03-11";
		String actualEnd = workSchedule.getDateEnd();
		System.out.println(actualStart);
		System.out.println(actualEnd);
		Assert.assertEquals(expectedStart, actualStart);
		Assert.assertEquals(expectedEnd, actualEnd);
	}
	
	@Test//11
	public void findRatingOfMeal() {
		ArrayList<RatingAll> ratings= ratingAllService.findByMeals(1L);
		int mealRating = ratings.get(0).getMealRating();
		assertThat(mealRating).isEqualTo(4);
	}
	
	@Test//12
	public void findAllRatingsForRestaurant() {
		Restaurant restaurant = restaurantService.getRestaurant(1L);
		ArrayList<RatingAll> ratings= ratingAllService.findByRestaurant(restaurant);
		System.out.println(ratings.size());
		assertThat(ratings.size()).isGreaterThan(1);
	}
	
	@Test//13
	public void findAllRatingsForEmployee() {
		Restaurant restaurant = restaurantService.getRestaurant(1L);
		ArrayList<Employee> employees = employeeService.getWaitersOfRestaurant("waiter", restaurant);
		Employee waiter = employees.get(0);
		ArrayList<RatingAll> ratings= ratingAllService.findByWaiter(waiter);
		System.out.println(ratings.size());
		assertThat(ratings.size()).isGreaterThan(2);
	}
	
	@Test//14
	public void addRating(){
		Guest guest = guestService.findOne(1L);
		ArrayList<Reservation> reservations = reservationService.findByIdGuest(guest);
		Reservation reservation = reservations.get(0);
		Restaurant restaurant = reservation.getIdRestaurant();
		ArrayList<Employee> employees = employeeService.getWaitersOfRestaurant("waiter", restaurant);
		Employee waiter = employees.get(0);
		int restaurantRating = 3;
		int serviceRating = 4;
		int mealRating = 2;
		RatingAll ratingAll = new RatingAll();
		ratingAll.setGuest(guest);
		ratingAll.setReservation(reservation);
		ratingAll.setRestaurant(restaurant);
		ratingAll.setMeals(null);
		ratingAll.setMealRating(mealRating);
		ratingAll.setServiceRating(serviceRating);
		ratingAll.setRestaurantRating(restaurantRating);
		ArrayList<RatingAll> ratingAllBefore = ratingAllService.findByRestaurant(restaurant);
		int sizeBefore = ratingAllBefore.size();
		System.out.println(sizeBefore);
		RatingAll addedRatingAll = ratingAllService.addNew(ratingAll);
		ArrayList<RatingAll> ratingAllAfter =ratingAllService.findByRestaurant(restaurant);
		int sizeAfter = ratingAllAfter.size();
		System.out.println(sizeAfter);
		int size = sizeBefore+1;
		assertThat(sizeAfter).isEqualTo(size);
	}
	
	@Test//15
	public void checkFirstLog() {
		Restaurant restaurant = restaurantService.getRestaurant(1L);
		ArrayList<Employee> employees = employeeService.getWaitersOfRestaurant("saladCook", restaurant);
		Employee saladCook = employees.get(0);
		String firstLog = "false";
		System.out.println(saladCook.getFirstLog());
		String actual = saladCook.getFirstLog();
		Assert.assertEquals(firstLog, actual);
	}
	
	@Test//16
	public void checkAssignTablesForWaiter() {
		Restaurant restaurant = restaurantService.getRestaurant(1L);
		ArrayList<Employee> employees = employeeService.getWaitersOfRestaurant("waiter", restaurant);
		Employee waiter = employees.get(0);
		ArrayList<AssignReon> assignReons = assignReonService.findByWaiter(waiter);
		AssignReon assignReon = assignReons.get(0);
		ArrayList<Tablee> tables = tableService.findByReon(assignReon.getReon());
		int numOfTables = tables.size();
		int reonTables = assignReon.getReon().getNumberTable();
		System.out.println(numOfTables);
		assertThat(numOfTables).isEqualTo(reonTables);
	}

	@Test//17
	public void findOrdersForGuestFriend() {
		Long guestId = 3L;
		ArrayList<Reservation> reservations = reservationService.findByAcceptedFriends_Id(guestId);
		Reservation reservation = reservations.get(0);
	}
}
