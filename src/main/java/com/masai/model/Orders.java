package com.masai.model;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Orders {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    @ElementCollection
    private List<String> items;

    @NotNull
    private double totalAmount;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "customerId")
    @JsonIgnoreProperties("orders")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "restaurantId")
    @JsonIgnoreProperties("orders")
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "deliveryId")
    @JsonIgnoreProperties("orders")
    private DeliveryPartner deliveryPartner;
    
    @Override
    public int hashCode() {
    	return Objects.hash(orderId);
    }
    
    @Override
    public boolean equals(Object obj) {
    	if(this == obj) return true;
    	if(obj == null || getClass() != obj.getClass()) return false;
    	Orders orders = (Orders)obj;
    	return orderId == orders.orderId;
    }

}
