package net.javaguides.sms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;



import net.javaguides.sms.entity.Customer;
//-
import net.javaguides.sms.entity.City;
import net.javaguides.sms.service.CityService;
//import net.javaguides.sms.entity.Customer.City;
import net.javaguides.sms.service.CustomerService;
import net.javaguides.sms.entity.Vehicle;
import net.javaguides.sms.repository.CityRepository;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;



@Controller
public class CustomerController {

    private CustomerService customerService;
    private CityService cityService;
    //--
    /*@Autowired
    private CityRepository cityRepository;*/

    public CustomerController(CustomerService customerService, CityService cityService) {
        this.customerService = customerService;
        this.cityService = cityService;
        //--
       
    }

    @GetMapping("/customers")
    public String listCustomers(Model model) {
        List<Customer> customers = customerService.getAllCustomers();

        
        customers.forEach(customer -> {
            int creditScore = calculateCreditScore(customer.getCity(), customer.getFirstName().length(), customer.getLastName().length());
            customer.setCreditScore(creditScore);
            customerService.saveCustomer(customer); 
        });

        model.addAttribute("customers", customers);
        return "customers";
    }
    //---
    
    private int calculateCreditScore(City city, int firstNameLength, int lastNameLength) {
    	if (city != null) {
            int cityScore = city.getKatSayisi();
            return cityScore * (firstNameLength + lastNameLength);
        } else {
            // city null ise uygun bir değer veya hata mesajı döndürün
            return 0; // Veya başka bir varsayılan değer
        }
    }

    @GetMapping("/customers/new")
    public String createCustomerForm(Model model) {
        Customer customer = new Customer();
        Vehicle vehicle = new Vehicle(); 
        customer.addVehicle(vehicle);
        
        //---
       // List<City> cities = cityRepository.findAll(); 
        List<City> cities = cityService.getAllCities();
        model.addAttribute("cities", cities);
        //--
        model.addAttribute("customer", customer);
        return "create_customer";
    }

    @PostMapping("/customers")
    public String saveCustomer(@Valid @ModelAttribute Customer customer, BindingResult bindingResult, @RequestParam("city") Long cityId, Model model) {
        // @Validated var

        if (bindingResult.hasErrors()) {
            List<City> cities = cityService.getAllCities();
            model.addAttribute("cities", cities);
            model.addAttribute("customer", customer);
            return "create_customer";
        }

        //City city = cityRepository.findById(cityId).orElse(null);
        City city = cityService.getCityById(cityId).orElse(null);

        if (city == null) {
            bindingResult.rejectValue("city", "error.customer", "Geçerli bir şehir seçmelisiniz");
            List<City> cities = cityService.getAllCities();
            model.addAttribute("cities", cities);
            model.addAttribute("customer", customer);
            return "create_customer";
        }

        customer.setCity(city);

        if (!customer.getTckn().matches("\\d{11}")) {
            bindingResult.rejectValue("tckn", "error.customer", "TCKN 11 haneli ve rakam içermelidir!");
            List<City> cities = cityService.getAllCities();
            model.addAttribute("cities", cities);
            model.addAttribute("customer", customer);
            return "create_customer";
        }

        try {
            List<Vehicle> vehicles = customer.getVehicles();
            if (vehicles == null) {
                vehicles = new ArrayList<>();
            }
            for (Vehicle vehicle : vehicles) {
                vehicle.setCustomer(customer);
            }
            customerService.saveCustomer(customer);
            return "redirect:/customers";
        } catch (DataIntegrityViolationException e) {
            bindingResult.rejectValue("tckn", "error.customer", "Bu TCKN veya plakaNo zaten kullanılıyor.");
            List<City> cities = cityService.getAllCities();
            model.addAttribute("cities", cities);
            model.addAttribute("customer", customer);
            return "create_customer";
        }
    

        
        /*
        //------------
        List<Vehicle> vehicles = customer.getVehicles();
        if (vehicles == null) {
            vehicles = new ArrayList<>(); 
        }
        for (Vehicle vehicle : vehicles) {
            vehicle.setCustomer(customer); 
        }
        customer.setVehicles(vehicles);
        //-----
         * 
         * 
     	customerService.saveCustomer(customer);
        return "redirect:/customers";
         */
        
        
    }
    
    @GetMapping("/customers/edit/{id}")
    public String updateCustomer(@PathVariable Long id, Model model) {
        Customer customer = customerService.getCustomerById(id);

        
        int creditScore = customer.getFirstName().length() * customer.getLastName().length();
        customer.setCreditScore(creditScore);

        model.addAttribute("customer", customer);
        return "edit_customer";
    }
    
    

    @GetMapping("/customers/calculate-policy/{id}")
    public String calculateCustomerPolice(@PathVariable Long id, Model model) {
    	Customer customer = customerService.getCustomerById(id);
    	
    	List<Vehicle> vehicles = customer.getVehicles();

       int creditScore = calculateCreditScore(customer.getCity(), customer.getFirstName().length(), customer.getLastName().length());
        customer.setCreditScore(creditScore);

        model.addAttribute("vehicles", vehicles);
        
        model.addAttribute("customer", customer);
        return "policy";
    }

    @PostMapping("/customers/{id}")
    public String updateCustomer(@PathVariable Long id,
                                 @ModelAttribute("customer") Customer customer,
                                 Model model) {
        Customer existingCustomer = customerService.getCustomerById(id);
        //existingCustomer.setId(id);
        existingCustomer.setFirstName(customer.getFirstName());
        existingCustomer.setLastName(customer.getLastName());
        existingCustomer.setEmail(customer.getEmail());
        existingCustomer.setCreditScore(customer.getCreditScore());
        
        
        if (customer.getTckn() != null) {
            
            if (customer.getTckn().length() != 11) {
                
                model.addAttribute("customer", existingCustomer);
                model.addAttribute("error", "TCKN 11 haneli olmalıdır");
                return "edit_customer";
            }
            
            existingCustomer.setTckn(customer.getTckn());
        }


        
        int creditScore = existingCustomer.getFirstName().length() * existingCustomer.getLastName().length();
        existingCustomer.setCreditScore(creditScore);

        customerService.saveCustomer(existingCustomer); 
        return "redirect:/customers";
    }
    
    @GetMapping("/customers/{id}")
    public String deleteCustomer(@PathVariable Long id) {
    	customerService.deleteCustomerById(id);
    	return "redirect:/customers";
    }
    
}

