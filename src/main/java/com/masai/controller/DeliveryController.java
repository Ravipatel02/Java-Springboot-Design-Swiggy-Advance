package com.masai.controller;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.DeliveryException;
import com.masai.model.DeliveryPartner;
import com.masai.service.DeliveryServiceImpl;

@RestController
@RequestMapping("/api/deliverypartner")
public class DeliveryController {
	@Autowired
	private DeliveryServiceImpl serviceImpl;
	
	@PostMapping("/")
	public DeliveryPartner addDeliveryPartner(@RequestBody DeliveryPartner deliveryPartner) {
		DeliveryPartner addDeliveryPartner = this.serviceImpl.addDeliveryPartner(deliveryPartner);
		return addDeliveryPartner;
		
	}
	
	@GetMapping("/{deliveryPartnerId}")
    public DeliveryPartner getDeliveryPartner(@PathVariable Integer deliveryPartnerId) {
		DeliveryPartner deliveryPartner = this.serviceImpl.getDeliveryPartner(deliveryPartnerId);
		return deliveryPartner;
    	
    }
	@PutMapping("/{deliveryPartnerId}")
   public  DeliveryPartner updateDeliveryPartner(@PathVariable Integer deliveryPartnerId,@RequestBody DeliveryPartner deliveryPartner) {
    	DeliveryPartner updateDeliveryPartner = this.serviceImpl.updateDeliveryPartner(deliveryPartnerId, deliveryPartner);
    	return updateDeliveryPartner;
    	
    }
	@DeleteMapping("/{deliveryPartnerId}")
    public DeliveryPartner deleteDeliveryPartner(@PathVariable Integer deliveryPartnerId) {
		DeliveryPartner deleteDeliveryPartner = this.serviceImpl.deleteDeliveryPartner(deliveryPartnerId);
		return deleteDeliveryPartner;
    	
    }
}
