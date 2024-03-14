package net.javaguides.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import net.javaguides.sms.entity.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    
}

