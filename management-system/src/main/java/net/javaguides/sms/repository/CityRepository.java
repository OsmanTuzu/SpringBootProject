package net.javaguides.sms.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguides.sms.entity.City;


public interface CityRepository extends JpaRepository<City, Long> {
}
