package com.masai.model;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
public class DeliveryPartner {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer deliveryId;

	@NotBlank
	private String deliveryName;

	@NotBlank
	private String address;

	@OneToMany(mappedBy = "deliveryPartner")
	@JsonIgnoreProperties("deliveryPartner")
	private List<Orders> orders;

	
	 @ManyToMany()
	    @JoinTable(
	        name = "Restaurant_deliveryPartner",
	        joinColumns = @JoinColumn(name = "deliveryPartner_deliveryId"),
	        inverseJoinColumns = @JoinColumn(name = "restaurant_restaurantId"))
	private Set<Restaurant> restaurants;

	@Override
	public int hashCode() {
		return Objects.hash(deliveryId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (this == null || getClass() != obj.getClass())
			return false;
		DeliveryPartner deliveryPartner = (DeliveryPartner) obj;
		return deliveryId == deliveryPartner.deliveryId;
	}

}
