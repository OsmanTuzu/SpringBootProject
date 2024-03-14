package net.javaguides.sms.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_id")
    private Long id;

    @Column(name = "plaka_no")
    private String plakaNo;

    @Column(name = "brand")
    @Enumerated(EnumType.STRING) // belirli ve sınırlı değer kümesi
    private Brand brand;

    @Column(name = "model")
    @Enumerated(EnumType.STRING)
    private Model model;

    @Column(name = "production_year")
    private int productionYear;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    
    public enum Brand {
        BMW, MERCEDES, AUDI
    }

    public enum Model {
        M5, M3, CLA, S, RS6, A5
    }

    

    public Vehicle(String plakaNo, Brand brand, Model model, int productionYear, Customer customer) {
		super();
		this.plakaNo = plakaNo;
		this.brand = brand;
		this.model = model;
		this.productionYear = productionYear;
		this.customer = customer;
	}
    
    public Vehicle() {
    	
    }

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlakaNo() {
        return plakaNo;
    }

    public void setPlakaNo(String plakaNo) {
        this.plakaNo = plakaNo;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
