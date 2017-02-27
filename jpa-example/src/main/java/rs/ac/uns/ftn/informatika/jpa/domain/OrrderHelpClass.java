package rs.ac.uns.ftn.informatika.jpa.domain;

import java.util.ArrayList;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class OrrderHelpClass {


		private Long waiter_id;	
		private Long table_id;
		private Long restaurant;	
		private Long reservation;	
		private String barman_state;
		private String cook_state;	
		private String date;
		private String time;
		private ArrayList<String> drinks = new ArrayList<String>();
		private ArrayList<String> meals = new ArrayList<String>();

		public OrrderHelpClass(){}
		
		public String getBarman_state() {
			return barman_state;
		}


		public void setBarman_state(String barman_state) {
			this.barman_state = barman_state;
		}


		public String getCook_state() {
			return cook_state;
		}


		public void setCook_state(String cook_state) {
			this.cook_state = cook_state;
		}


		public Long getReservation() {
			return reservation;
		}

		public void setReservation(Long reservation) {
			this.reservation = reservation;
		}

	
		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}

		public ArrayList<String> getDrinks() {
			return drinks;
		}
		public Long getWaiter_id() {
			return waiter_id;
		}

		public void setWaiter_id(Long waiter_id) {
			this.waiter_id = waiter_id;
		}

		public Long getTable_id() {
			return table_id;
		}

		public void setTable_id(Long table_id) {
			this.table_id = table_id;
		}

		public void setDrinks(ArrayList<String> drinks) {
			this.drinks = drinks;
		}

		public ArrayList<String> getMeals() {
			return meals;
		}

		public void setMeals(ArrayList<String> meals) {
			this.meals = meals;
		}


		public Long getRestaurant() {
			return restaurant;
		}

		public void setRestaurant(Long restaurant) {
			this.restaurant = restaurant;
		}

		
	

}
