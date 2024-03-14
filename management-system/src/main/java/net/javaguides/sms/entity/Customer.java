package net.javaguides.sms.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import javax.validation.constraints.*;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.*;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.lang.*;




@Entity //JPA
@Table(name = "customers")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //veritabanı yeni bir satır ekle
	private Long id;

	
	@Column(name = "first_name", nullable = false)
	@Length(max=100)
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "email")
	@Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Geçerli bir email adresi giriniz")
	@Email(message = "Geçerli bir e-posta adresi giriniz")
	private String email;
	
	@Column(name = "credit_score")
	private int  creditScore;
	
	@Column(name = "tckn")
	private String tckn;
	/*
	@Enumerated(EnumType.STRING)
	@Column(name = "city")
	private City city;
	
	public enum City {
	    ISTANBUL, IZMIR, MANISA, ANKARA
	}*/
	
	
	//--
	@ManyToOne // müşteri hangi şehirle ilişkilendirildi
	@JoinColumn(name = "city_id")
	private City city;
	//--
	
	
	
	//--------- müşterinin sahip olduğu araçlar, ilişki çift yönlü, işlemleri yansıt, 
	 @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	 private List<Vehicle> vehicles; 
	 
	 // müşterinin araçlarını listeye ekle
	 public void addVehicle(Vehicle vehicle) {
		    if (vehicles == null) {
		        vehicles = new ArrayList<>(); 
		    }
		    vehicles.add(vehicle);
		    vehicle.setCustomer(this); 
		}


	

	 // constructorlar
	public Customer(String firstName, String lastName, String email, int creditScore, String tckn, City city) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.creditScore = creditScore;
		this.tckn = tckn;
		//this.city = city;
	}
	
	@AssertTrue(message = "TCKN must be 11 characters long")
	private boolean isValidTckn() {
	    return tckn == null || tckn.length() == 11;
	}


	public Customer() {
		
	}
	
	/*
	public Customer(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}*/
	
	
	
	public Long getId() {
		return id;
	}
	public List<Vehicle> getVehicles() {
		return vehicles;
	}




	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}




	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		if (firstName != null && !firstName.trim().isEmpty()) {
	        this.firstName = firstName;
	    } else {
	        throw new IllegalArgumentException("İsim kısmı boş olamaz");
	    }
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getCreditScore() {
        return creditScore;
    }
    public void setCreditScore(int creditScore) {
        this.creditScore = creditScore;
    }
    public String getTckn() {
		return tckn;
	}
	public void setTckn(String tckn) {
		this.tckn = tckn;
	}/*
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}*/
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	
	
	

}
