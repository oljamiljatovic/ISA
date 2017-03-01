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
import com.jayway.restassured.response.ResponseBodyExtractionOptions;

import rs.ac.uns.ftn.informatika.jpa.domain.users.Guest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GuestControllerTest {

	@Autowired
	private GuestService guestService;
	
	
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
	public void findById() {
		
		assertNotNull( given().pathParam("id", 1)
	    .when().put("/guestController/findById/{id}").then());
	    
		RestAssured.when()
		.put("/guestController/findById/1")
		.then()
		.statusCode(HttpStatus.SC_OK)
		.contentType(ContentType.JSON)
		.body("", Matchers.notNullValue());
		    
	}
	
	
	@Test
	public void changeAddFriendTest(){
	 
		RestAssured.when()
        .put("guestController/changeAddFriend/1/5")
        .then()
        .statusCode(HttpStatus.SC_OK);
	 
	 }
	 
	@Test
    public void regInTest() { 
		
		Guest newGuest = new Guest ("mirko.miljatovic@yahoo.com", "1317","guest","accept","Mirko","Miljatovic");
		
        given()
        .contentType("application/json")
        .body(newGuest)
        .when().post("/guestController/regIn").then()
        .statusCode(HttpStatus.SC_OK);
       
    }
	
	@Test
    public void searchTest() {
		
		 Guest[] persons = given()
        .contentType("application/json")
        .body("vesna")
        .when().post("/guestController/search").as(Guest[].class);
        
		 assertThat(persons.length).isGreaterThan(0);
    }

	
}
