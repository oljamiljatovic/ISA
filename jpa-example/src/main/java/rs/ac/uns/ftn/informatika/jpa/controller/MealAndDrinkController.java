package rs.ac.uns.ftn.informatika.jpa.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import rs.ac.uns.ftn.informatika.jpa.domain.Drink;
import rs.ac.uns.ftn.informatika.jpa.domain.Meal;
import rs.ac.uns.ftn.informatika.jpa.service.DrinkService;
import rs.ac.uns.ftn.informatika.jpa.service.MealService;


@Controller
@RequestMapping("/mealAndDrinkController")
public class MealAndDrinkController {
	
	@Autowired
	private DrinkService drinkService;
	@Autowired
	private MealService mealService;
	
	@RequestMapping(
			value = "/uzmiPica",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<Drink>> uzmiPica()  throws Exception {
		ArrayList<Drink> drinks = this.drinkService.getDrinks();
		return new ResponseEntity<ArrayList<Drink>>(drinks, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/uzmiObroke",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<Meal>> uzmiObroke()  throws Exception {
		ArrayList<Meal> meals = this.mealService.getMeals();
		return new ResponseEntity<ArrayList<Meal>>(meals, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/deleteDrink",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteDrink(@RequestBody Drink drink)  throws Exception {
		
		Drink dr = this.drinkService.getDrink(drink.getId());
		this.drinkService.deleteDrink(dr);
	}
	
}
