package net.javaguides.sms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import net.javaguides.sms.entity.Brand;
import net.javaguides.sms.repository.BrandRepository;
import net.javaguides.sms.service.BrandService;

@Service
public class BrandServiceImpl implements BrandService {

    private BrandRepository brandRepository;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    @Transactional
    public List<Brand> getAllBrands() {
        
        return brandRepository.findAll();
    }

    @Override
    @Transactional
    public void addBrand(Brand brand) {
        
        brandRepository.save(brand);
    }
    
   
}

