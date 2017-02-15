package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;

import rs.ac.uns.ftn.informatika.jpa.domain.PurchaseOrder;

public interface PurchaseOrderService {

	public PurchaseOrder addPurchaseOrder(PurchaseOrder pc);
	public void updatePurchaseOrder(PurchaseOrder pc);
	public ArrayList<PurchaseOrder> getPurchaseOrders();
	public ArrayList<PurchaseOrder> getPurchaseOrderByOffer(Long id);
	public PurchaseOrder getPurchaseOrderByOfferAndProvider(PurchaseOrder pc);
	public ArrayList<PurchaseOrder> getPurchaseOrderByRestaurant(Long id);
}
