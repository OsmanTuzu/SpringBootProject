package net.javaguides.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import net.javaguides.sms.entity.Customer;
import net.javaguides.sms.repository.CustomerRepository;

import net.javaguides.sms.entity.City;
import net.javaguides.sms.repository.CityRepository;

@SpringBootApplication
public class SbmManagementSystemApplication implements CommandLineRunner {
 
	public static void main(String[] args) {
		SpringApplication.run(SbmManagementSystemApplication.class, args);
	}

	@Autowired
	private CustomerRepository customerRepository;
	//-------
	@Autowired
    private CityRepository cityRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		/*
		Customer customer1 = new Customer("Nazif", "İlbek", "nazif@gmail.com");
		customerRepository.save(customer1);
		
		Employee employee2 = new Employee("Mehmet", "Barutçu", "mehmet@gmail.com");
		employeeRepository.save(employee2);
		
		Employee employee3 = new Employee("Oğuzhan", "Unur", "oguzhan@gmail.com");
		employeeRepository.save(employee3);*/ 
		
		/*City city = new City("Eskişehir", 5);
		cityRepository.save(city);*/

		
	}

}
