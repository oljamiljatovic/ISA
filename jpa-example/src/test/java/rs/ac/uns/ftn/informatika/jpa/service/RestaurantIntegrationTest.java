package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import rs.ac.uns.ftn.informatika.jpa.domain.Restaurant;
import rs.ac.uns.ftn.informatika.jpa.repository.RestaurantRepository;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestaurantIntegrationTest {
	
	@Autowired
	private RestaurantRepository repo;
	
	@Test
	public void findsFirstPageOfCities() {

		this.repo.delete((long) 2);
		int x = ((ArrayList<Restaurant>)this.repo.findAll()).size();
		assertThat(x).isEqualTo(4);
	}

}
