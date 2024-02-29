package com.masai.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.DeliveryException;
import com.masai.model.DeliveryPartner;
import com.masai.repository.DeliveryRepository;


@Service
public class DeliveryServiceImpl implements DeliveryService  {
	@Autowired
	private DeliveryRepository repo;

	@Override
	public DeliveryPartner addDeliveryPartner(DeliveryPartner deliveryPartner) throws DeliveryException {
		try {
			DeliveryPartner save = this.repo.save(deliveryPartner);
			return save;
			
		} catch (Exception e) {
			throw new DeliveryException("Error Ocrred when delivery partner save",e);
		}
	}

	@Override
	public DeliveryPartner getDeliveryPartner(int deliveryPartnerId) throws DeliveryException {
		try {
			Optional<DeliveryPartner> findById = this.repo.findById(deliveryPartnerId);
			if(findById.isPresent()) {
				DeliveryPartner deliveryPartner = findById.get();
				return deliveryPartner;
			}else {
				throw new DeliveryException("Delevery partner is not present with "+deliveryPartnerId);
			}
			
		} catch (Exception e) {
			throw new DeliveryException("Error occred when deliveryPartner find",e);
		}
	}

	@Override
	public DeliveryPartner updateDeliveryPartner(int deliveryPartnerId, DeliveryPartner deliveryPartner)
			throws DeliveryException {
		try {
			Optional<DeliveryPartner> findById = this.repo.findById(deliveryPartnerId);
			if(findById.isPresent()) {
				DeliveryPartner deliveryPartner2 = findById.get();
				deliveryPartner2.setAddress(deliveryPartner.getAddress());
				deliveryPartner2.setDeliveryName(deliveryPartner.getDeliveryName());
				DeliveryPartner save = this.repo.save(deliveryPartner2);
				return save;
			}else {
				throw new DeliveryException("Delevery partner is not present with "+deliveryPartnerId);
			}
			
		} catch (Exception e) {
			throw new DeliveryException("Error occured when find update deleverypartner",e);
		}
	}

	@Override
	public DeliveryPartner deleteDeliveryPartner(int deliveryPartnerId) throws DeliveryException {
		try {
			Optional<DeliveryPartner> findById = this.repo.findById(deliveryPartnerId);
			if(findById.isPresent()) {
				DeliveryPartner deliveryPartner = findById.get();
				this.repo.deleteById(deliveryPartnerId);
				return deliveryPartner;
			}else {
				throw new DeliveryException("Delevery partner is not present with "+deliveryPartnerId);
			}
			
		} catch (Exception e) {
			throw new DeliveryException("Error occurd when delete delvery partner",e);
		}
	}

}
