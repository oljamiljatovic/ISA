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

import rs.ac.uns.ftn.informatika.jpa.domain.users.Guest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InvitationControllerTest {

	
	@Autowired
	private InvitationService invitationService;
	
	
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
	public void getRequestsTest() {
		
		RestAssured.when()
        .post("/invitationController/getRequests/5")
        .then()
        .statusCode(HttpStatus.SC_OK)
        .contentType(ContentType.JSON)
        .body("", Matchers.notNullValue());
		        
		       	
	}
	
	
	 @Test
	 public void sendRequestTest(){
	 
		Guest sender = guestService.findOne((long)1);
		Guest recipient = guestService.findOne((long)5);
	
	 RestAssured.when()
     .post("invitationController/sendRequest/"+sender.getId()+"/"+recipient.getId())
     .then()
     .statusCode(HttpStatus.SC_OK);
	 
	 }
	 
	
}
