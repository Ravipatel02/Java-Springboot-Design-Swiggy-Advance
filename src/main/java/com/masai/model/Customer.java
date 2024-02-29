package com.masai.model;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Customer {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;

    @NotBlank
    @Size(min = 3, max = 40)
    private String username;

    @Email
    private String email;

    @OneToMany(mappedBy = "customer")
    @JsonIgnoreProperties("customer")
    private List<Orders> orders;
    
    @Override
    public int hashCode() {
    	return Objects.hash(customerId);
    }
    
    @Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
        Customer customer = (Customer)obj;
        return customerId == customer.customerId;
	}
    
    

}
