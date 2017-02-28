package rs.ac.uns.ftn.informatika.jpa.service;

import static com.jayway.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;

import rs.ac.uns.ftn.informatika.jpa.domain.Reservation;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Guest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationControllerTest {


	@Autowired
	private InvitationService invitationService;
	
	
	@BeforeClass
    public static void setup() {
        String port = System.getProperty("server.port");
        if (port == null) {
            RestAssured.port = Integer.valueOf(9999);
        }
        else{
            RestAssured.port = Integer.valueOf(port);
        }

    }
	
	@Test
	public void getReservationById() {
		
		
		assertNotNull( given().pathParam("idReservation", 1)
	        .when().put("/reservationController/getReservationById/{idReservation}").then());
		
		RestAssured.when()
		.put("/reservationController/getReservationById/1")
		.then()
		.statusCode(HttpStatus.SC_OK)
		.contentType(ContentType.JSON)
		.body("", Matchers.notNullValue());
		    
	}
	
	@Test
	public void getFriends() {
		
		RestAssured.when()
		.put("/reservationController/getFriends/1")
		.then()
		.statusCode(HttpStatus.SC_OK)
	    .contentType(ContentType.JSON)
		.body("", Matchers.notNullValue());
		    
	}
	
	
	@Test
	public void getSenderByIdTest() {
		
		assertNotNull(given().pathParam("idReservation", 1)
	    .when().put("/reservationController/getSenderById/{idReservation}").then());
	      
		Guest guest = given().pathParam("idReservation", 1)
	    .when().put("/reservationController/getSenderById/{idReservation}").as(Guest.class);
		
		assertNotNull(guest);
		
	}
	
	
	@Test
	public void getReservedTableForReservation() {
		
		RestAssured.when()
		.get("/reservationController/getReservedTableForReservation/1")
		.then()
		.statusCode(HttpStatus.SC_OK)
		.contentType(ContentType.JSON)
		.body("", Matchers.notNullValue());
		
	}
	
	
	@Test
	public void addFriendToReservation() {

		Reservation reservation = given()
		.contentType("application/json")
		.body((long)1)
		.when().post("/reservationController/addFriendToReservation/5").as(Reservation.class);
		                		 
		assertThat(reservation).hasNoNullFieldsOrProperties();
		    		
	}
	
	
	
}
