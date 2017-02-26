package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rs.ac.uns.ftn.informatika.jpa.domain.Offer;
import rs.ac.uns.ftn.informatika.jpa.domain.PurchaseOrder;
import rs.ac.uns.ftn.informatika.jpa.domain.Restaurant;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Provider;
import rs.ac.uns.ftn.informatika.jpa.repository.PurchaseOrderRepository;


@Service
@Transactional
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

	@Autowired
	private PurchaseOrderRepository purchaseOrderRepository;
	
	@Override
	public PurchaseOrder addPurchaseOrder(PurchaseOrder pc) {
		return purchaseOrderRepository.save(pc);
	}

	@Override
	public ArrayList<PurchaseOrder> getPurchaseOrders() {
		return (ArrayList<PurchaseOrder>) this.purchaseOrderRepository.findAll();
	}

	@Override
	public ArrayList<PurchaseOrder> getPurchaseOrderByOffer(Offer id) {
		return this.purchaseOrderRepository.findByOffer(id);
	}

	@Override
	public void updatePurchaseOrder(PurchaseOrder pc) {
		this.purchaseOrderRepository.updatePuchaseOrder(pc.getPrice(), pc.getTimeDeliver(), pc.getFlag(), pc.getId());
	}

	@Override
	public PurchaseOrder getPurchaseOrderByOfferAndProvider(PurchaseOrder pc) {
		return this.purchaseOrderRepository.findByProviderAndOffer(pc.getProvider(), pc.getOffer());
	}

	@Override
	public ArrayList<PurchaseOrder> getPurchaseOrderByRestaurant(Restaurant id) {
		// TODO Auto-generated method stub
		return this.purchaseOrderRepository.findByRestaurant(id);
	}

	@Override
	public void updateFlag(Long flag, Long id) {
		this.purchaseOrderRepository.updateFlag(flag, id);
	}

	@Override
	public ArrayList<PurchaseOrder> getPurchaseOrderByProvider(Provider id) {
		
		return this.purchaseOrderRepository.findByProvider(id);
	}

}
