package com.masai.model;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Restaurant {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer restaurantId;

    @NotBlank
    private String restaurantName;

    @NotBlank
    private String address;

    @OneToMany(mappedBy = "restaurant")
    @JsonIgnoreProperties("restaurant")
    private List<Orders> orders;

   
    @ManyToMany()
    @JoinTable(
        name = "Restaurant_deliveryPartner",
        joinColumns = @JoinColumn(name = "restaurant_restaurantId"),
        inverseJoinColumns = @JoinColumn(name = "deliveryPartner_deliveryId"))
    private Set<DeliveryPartner> deliveryPartners = new HashSet<>();
    
    @Override
    public int hashCode() {
    	return Objects.hash(restaurantId);
    }
    
    @Override
    public boolean equals(Object obj) {
    	if(this == obj) return true;
    	if(obj == null || getClass() != obj.getClass()) return false;
    	
    	Restaurant restaurant = (Restaurant)obj;
    	return restaurantId == restaurant.restaurantId;
    }

}
