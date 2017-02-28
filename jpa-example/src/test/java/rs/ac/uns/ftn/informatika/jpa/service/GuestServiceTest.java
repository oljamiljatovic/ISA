package rs.ac.uns.ftn.informatika.jpa.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;

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
		fail("Not yet implemented");
	}

	@Test
	public void testCreateNew() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindOne() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindByFriends() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindGuestsByName() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindGuestsBySurname() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindGuestsByNameAndSurname() {
		fail("Not yet implemented");
	}

}
