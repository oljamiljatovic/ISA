package rs.ac.uns.ftn.informatika.jpa.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import rs.ac.uns.ftn.informatika.jpa.domain.Offer;
import rs.ac.uns.ftn.informatika.jpa.domain.PurchaseOrder;
import rs.ac.uns.ftn.informatika.jpa.domain.Restaurant;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Provider;

public interface PurchaseOrderRepository extends PagingAndSortingRepository<PurchaseOrder, Long>  {
	
	public ArrayList<PurchaseOrder> findByOffer(Offer offer);
	public PurchaseOrder findByProviderAndOffer(Provider provider, Offer offer);
	public ArrayList<PurchaseOrder> findByRestaurant(Restaurant restaurant);

	@Modifying
	@Query("update PurchaseOrder set price = ?, timeDeliver = ?, flag=? where id = ? ")
	public void updatePuchaseOrder(String price, String days, Long flag, Long id);
	

	@Modifying
	@Query("update PurchaseOrder set  flag=? where id = ? ")
	public void updateFlag(Long flag, Long id);
	
	public ArrayList<PurchaseOrder> findByProvider(Provider provider);
}
