package repository;

import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mysql.fabric.xmlrpc.base.Array;

import domain.City;



public interface CityRepository {
	
	public ArrayList<City> findAll(Pageable pageable);
}
