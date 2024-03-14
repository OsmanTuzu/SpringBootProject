package net.javaguides.sms.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cities")
public class City {

    

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "kat_sayisi")
    private Integer katSayisi;
    


	

	public City(String name, Integer katSayisi) {
		super();
		this.name = name;
		this.katSayisi = katSayisi;
	}
    
    public City() {
    	
    }
    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getKatSayisi() {
		return katSayisi;
	}
	public void setKatSayisi(Integer katSayisi) {
		this.katSayisi = katSayisi;
	}
	
}

