package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;

import rs.ac.uns.ftn.informatika.jpa.domain.Offer;
import rs.ac.uns.ftn.informatika.jpa.domain.PurchaseOrder;
import rs.ac.uns.ftn.informatika.jpa.domain.Restaurant;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Provider;

public interface PurchaseOrderService {

	public PurchaseOrder addPurchaseOrder(PurchaseOrder pc);
	public void updatePurchaseOrder(PurchaseOrder pc);
	public void updateFlag(Long flag, Long id);
	public ArrayList<PurchaseOrder> getPurchaseOrders();
	public ArrayList<PurchaseOrder> getPurchaseOrderByOffer(Offer id);
	public PurchaseOrder getPurchaseOrderByOfferAndProvider(PurchaseOrder pc);
	public ArrayList<PurchaseOrder> getPurchaseOrderByRestaurant(Restaurant id);
	public ArrayList<PurchaseOrder> getPurchaseOrderByProvider(Provider id);
	public void updatePurchaseOrderSeen(boolean flag, Long id);
	public PurchaseOrder findOne(Long id);
	
}
