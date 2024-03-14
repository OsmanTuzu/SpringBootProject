package net.javaguides.sms.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import net.javaguides.sms.entity.Customer;
import net.javaguides.sms.repository.CustomerRepository;
import net.javaguides.sms.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{

	private CustomerRepository customerRepository;
	

	public CustomerServiceImpl(CustomerRepository customerRepository) {
		super();
		this.customerRepository = customerRepository;
	}

	

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public List<Customer> getAllCustomersWithCreditScore() {
		List<Customer> customers = customerRepository.findAll();
		
		for (Customer customer : customers) {
			int creditScore = customer.getFirstName().length()*customer.getLastName().length();
			customer.setCreditScore(creditScore);
					
		}
		return customers;
	}

	@Override
	public Customer saveCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Customer getCustomerById(Long id) {
		return customerRepository.findById(id).get();
	}

	@Override
	public Customer calculateCustomerPolice(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public void deleteCustomerById(Long id) {
		customerRepository.deleteById(id);
		
	}
}
