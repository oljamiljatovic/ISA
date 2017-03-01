package rs.ac.uns.ftn.informatika.jpa.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import rs.ac.uns.ftn.informatika.jpa.domain.Invitation;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Guest;
import rs.ac.uns.ftn.informatika.jpa.repository.InvitationRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InvitationServiceTest {

	@Autowired
	private InvitationRepository invitationRepository;
	
	@Autowired
	private GuestService guestService;
	
	@Test
	public void findRequestsTest() {
		
		Guest guest = guestService.findOne((long)5);
		List<Invitation> invitations = invitationRepository.findRequests(guest);
		assertNotNull(invitations);
	}
}
