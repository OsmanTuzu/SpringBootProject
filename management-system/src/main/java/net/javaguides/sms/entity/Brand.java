package net.javaguides.sms.entity;

import java.util.List;

import jakarta.persistence.*;


@Entity
@Table(name = "brands")
public class Brand {

	
	

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "brand_name", nullable = false)
    private String brandName;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL)
    private List<ModelBrand> modelBrands;
    
    public Brand() {
    	
    }
	
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public List<ModelBrand> getModels() {
		return modelBrands;
	}

	public void setModels(List<ModelBrand> modelBrands) {
		this.modelBrands = modelBrands;
	}
    
    
}
