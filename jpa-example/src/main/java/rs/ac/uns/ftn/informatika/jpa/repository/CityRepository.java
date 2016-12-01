package rs.ac.uns.ftn.informatika.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import rs.ac.uns.ftn.informatika.jpa.domain.City;

public interface CityRepository extends Repository<City, Long> {
	
	public Page<City> findAll(Pageable pageable);

	public Page<City> findByNameContainingAndCountryContainingAllIgnoringCase(String name,
			String country, Pageable pageable);

	public City findByNameAndCountryAllIgnoringCase(String name, String country);

}
