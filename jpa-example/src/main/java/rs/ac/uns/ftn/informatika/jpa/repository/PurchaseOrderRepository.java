package rs.ac.uns.ftn.informatika.jpa.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import rs.ac.uns.ftn.informatika.jpa.domain.PurchaseOrder;
import java.lang.Long;
import java.util.List;

public interface PurchaseOrderRepository extends PagingAndSortingRepository<PurchaseOrder, Long>  {
	
	public ArrayList<PurchaseOrder> findByOffer(Long offer);
	public PurchaseOrder findByProviderAndOffer(Long provider, Long offer);
	public ArrayList<PurchaseOrder> findByRestaurant(Long restaurant);

	@Modifying
	@Query("update PurchaseOrder set price = ?, timeDeliver = ?, flag=? where id = ? ")
	public void updatePuchaseOrder(String price, String days, Long flag, Long id);
	

	@Modifying
	@Query("update PurchaseOrder set  flag=? where id = ? ")
	public void updateFlag(Long flag, Long id);
	
	public ArrayList<PurchaseOrder> findByProvider(Long provider);
}
