package rs.ac.uns.ftn.informatika.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import rs.ac.uns.ftn.informatika.jpa.domain.City;
import rs.ac.uns.ftn.informatika.jpa.domain.HotelSummary;
import rs.ac.uns.ftn.informatika.jpa.repository.CityRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.HotelRepository;

@Service
@Transactional
public class CityServiceImpl implements CityService {

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private HotelRepository hotelRepository;


	@Override
	public Page<City> findCities(String criteria, Pageable pageable) {

		Assert.notNull(criteria, "Kriterijum ne sme biti null");

		if (!StringUtils.hasLength(criteria)) {
			return this.cityRepository.findAll(null);
		}

		String country = "";
		int splitPos = criteria.lastIndexOf(",");

		if (splitPos >= 0) {
			country = criteria.substring(splitPos + 1);
			criteria = criteria.substring(0, splitPos);
		}

		return this.cityRepository
				.findByNameContainingAndCountryContainingAllIgnoringCase(criteria.trim(),
						country.trim(), pageable);
	}

	@Override
	public City getCity(String name, String country) {
		Assert.notNull(name, "Naziv grada ne sme biti null");
		Assert.notNull(country, "Naziv drzave ne sme biti null");
		return this.cityRepository.findByNameAndCountryAllIgnoringCase(name, country);
	}

	@Override
	public Page<HotelSummary> getHotels(City city, Pageable pageable) {
		Assert.notNull(city, "Grad ne sme null");
		return this.hotelRepository.findByCity(city, pageable);
		
	}
}
