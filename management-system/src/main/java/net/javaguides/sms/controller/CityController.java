package net.javaguides.sms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import net.javaguides.sms.repository.CityRepository;
import net.javaguides.sms.service.CityService;
import net.javaguides.sms.entity.City;

@Controller
public class CityController {

	
	private final CityService cityService;
	
	//@Autowired
    public CityController(CityService cityService) {
        
        this.cityService = cityService;
    }
	
	@GetMapping("/add-city")
	public String showCityForm(Model model) {
	    model.addAttribute("city", new City());	  
	    //List<City> cities = cityRepository.findAll();
	    List<City> cities = cityService.getAllCities();
        model.addAttribute("cities", cities);
	    
	    return "add_city";
	}


    @PostMapping("/saveCity") // ModelAttribute anotasyonu 
    public String saveCity(@ModelAttribute City city, @RequestParam("katSayisi") int katSayisi) {
    	city.setKatSayisi(katSayisi);
    	cityService.saveCity(city);
        //cityRepository.save(city);
        
        return "redirect:/add-city";
    }   
    
    @GetMapping("/deleteCity/{cityId}")
    public String deleteCity(@PathVariable Long cityId) { 
        cityService.deleteCity(cityId);
        return "redirect:/add-city";
    }

    
    


	
}
