package rs.ac.uns.ftn.informatika.jpa.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import rs.ac.uns.ftn.informatika.jpa.domain.users.Guest;
import rs.ac.uns.ftn.informatika.jpa.repository.GuestRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GuestServiceTest {

	@Autowired
	private GuestRepository repo;
	
	@Autowired
	private GuestService guestService;
	
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetGuests() {
		
		
		ArrayList<Guest> guests  = repo.findAll();
		assertNotNull(guests);
	}

	@Test
	public void testFindGuestByEmail() {
		Guest guest = guestService.findGuestByEmail("olja.miljatovic@yahoo.com");
		assertNotNull(guest);
	}

	@Test
	public void testCreateNew() {
		
		
		Guest newGuest = new Guest ("mirko.miljatovic@yahoo.com", "1317","guest","accept","Mirko","Miljatovic");
		Guest guest = guestService.createNew(newGuest);
		assertNotNull(guest);
		
		
	}

	@Test
	public void testUpdate() {
		Guest guest = guestService.findOne((long)1);
		guest.setName("Oljka");
		
		Guest founded = guestService.update(guest, guest.getId());
		assertEquals("Oljka", founded.getName());
		
	}

	@Test
	public void testFindOne() {
		
		assertNotNull(guestService.findOne((long) 1));
	}

	
	@Test
	public void testFindGuestsByName() {
		
		Guest guest = guestService.findOne((long)5);
		
		List<Guest> guests = guestService.findGuestsByName("Vesna");
		
		assertNotNull(guest);
		assertNotNull(guests);
		
		 for(int i = 0; i<guests.size() ; i++){
			 if(guests.get(i).getId().equals(guest.getId())){
				 assertTrue(guests.get(i).getId().equals(guest.getId()));
			 }
		 }
	}

	@Test
	public void testFindGuestsBySurname() {
		
		List<Guest> foundedWithSurname = guestService.findGuestsBySurname("Stanojevic");
		
		Guest guest  = guestService.findOne((long)3);
		
		assertNotNull(foundedWithSurname);
		 for(int i = 0; i<foundedWithSurname.size() ; i++){
			 if(foundedWithSurname.get(i).getId().equals(guest.getId())){
				 assertTrue(foundedWithSurname.get(i).getId().equals(guest.getId()));
			 }
		 }
	}

	@Test
	public void testFindGuestsByNameAndSurname() {
		
		String name = guestService.findOne((long)1).getName();
		String surname = guestService.findOne((long)1).getSurname();
		
		int numberOfGuests = guestService.findGuestsByNameAndSurname(name, surname).size();
		
		assertThat(numberOfGuests).isGreaterThan(0);
	}

}
