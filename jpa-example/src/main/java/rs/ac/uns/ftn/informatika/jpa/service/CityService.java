package rs.ac.uns.ftn.informatika.jpa.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import rs.ac.uns.ftn.informatika.jpa.domain.City;
import rs.ac.uns.ftn.informatika.jpa.domain.HotelSummary;

public interface CityService {

	Page<City> findCities(String criteria, Pageable pageable);

	City getCity(String name, String country);

	Page<HotelSummary> getHotels(City city, Pageable pageable);
	
	

}
