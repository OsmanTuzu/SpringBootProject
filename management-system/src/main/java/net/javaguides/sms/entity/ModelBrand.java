package net.javaguides.sms.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "models")
public class ModelBrand {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "model_name", nullable = false)
    private String modelName;

    @Column(name = "model_premium")
    private double modelPremium;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;
    
    
    
    
    public ModelBrand(String modelName, double modelPremium, Brand brand) {
        this.modelName = modelName;
        this.modelPremium = modelPremium;
        this.brand = brand;
    }

    public ModelBrand() {
    }
    
    

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public double getModelPremium() {
		return modelPremium;
	}

	public void setModelPremium(double modelPremium) {
		this.modelPremium = modelPremium;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	

}
