package net.javaguides.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguides.sms.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
