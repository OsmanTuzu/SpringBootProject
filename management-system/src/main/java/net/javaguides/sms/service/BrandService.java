package net.javaguides.sms.service;

import java.util.List;
import net.javaguides.sms.entity.Brand;

public interface BrandService {
    
    List<Brand> getAllBrands();
    
    void addBrand(Brand brand);

}

