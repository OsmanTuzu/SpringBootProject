package net.javaguides.sms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.javaguides.sms.entity.City;
import net.javaguides.sms.repository.CityRepository;

@Service
public class CityService {
    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }
    
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }
    
    public Optional<City> getCityById(Long cityId) {
        return cityRepository.findById(cityId);
    }

    public void saveCity(City city) {
        cityRepository.save(city);
    }
    
    public void deleteCity(Long cityId) {
        cityRepository.deleteById(cityId);
    }
    
}

