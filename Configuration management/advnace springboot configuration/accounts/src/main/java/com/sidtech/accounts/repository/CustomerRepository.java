package com.sidtech.accounts.repository;

import com.sidtech.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long>
{
    Optional<Customer> findByMobileNumber(String mobilNumber);
}
