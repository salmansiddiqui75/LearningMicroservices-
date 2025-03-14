package com.sidtech.accounts.service.impl;

import com.sidtech.accounts.constant.AccountConstant;
import com.sidtech.accounts.dto.AccountDto;
import com.sidtech.accounts.dto.CustomerDto;
import com.sidtech.accounts.entity.Accounts;
import com.sidtech.accounts.entity.Customer;
import com.sidtech.accounts.exception.CustomerAlreadyExistsException;
import com.sidtech.accounts.exception.ResourceNotFoundException;
import com.sidtech.accounts.mapper.AccountMapper;
import com.sidtech.accounts.mapper.CustomerMapper;
import com.sidtech.accounts.repository.AccountsRepository;
import com.sidtech.accounts.repository.CustomerRepository;
import com.sidtech.accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService
{
    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto)
    {
        Customer customer=CustomerMapper.mapToCustomer(customerDto,new Customer());
        Optional<Customer> byMobileNumber = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if(byMobileNumber.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer already registered with given mobileNumber " + customerDto.getMobileNumber());
        }
        Customer saveCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(saveCustomer));
    }

    private Accounts createNewAccount(Customer customer)
    {
        Accounts accounts=new Accounts();
        accounts.setCustomerId(customer.getCustomerId());
        long randomAccNumber=1000000000L+new Random().nextInt(900000000);
        accounts.setAccountNumber(randomAccNumber);
        accounts.setAccountType(AccountConstant.SAVINGS);
        accounts.setBranchAddress(AccountConstant.ADDRESS);
        return accounts;
    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber)
    {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("Customer", "MobileNumber", mobileNumber));
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(() -> new ResourceNotFoundException("Account", "Customer Id", customer.getCustomerId().toString()));
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountDto(AccountMapper.mapToAccountsDto(accounts,new AccountDto()));
        return customerDto ;
    }

    @Override
    public boolean updateAcount(CustomerDto customerDto)
    {
        boolean isUpdate=false;
        AccountDto accountDto = customerDto.getAccountDto();
        if (accountDto!=null)
        {
            Accounts accounts = accountsRepository.findById(accountDto.getAccountNumber()).
                    orElseThrow(() -> new ResourceNotFoundException("Account", "AccountNumber", accountDto.getAccountNumber().toString()));
            AccountMapper.mapToAccounts(accountDto,accounts);
            accounts=accountsRepository.save(accounts);
            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer", "Customer id", customerId.toString()));
            CustomerMapper.mapToCustomer(customerDto,customer);
            customerRepository.save(customer);
            isUpdate=true;
        }
        return isUpdate;
    }

    @Override
    public boolean deleteAccount(String mobileNumber)
    {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("Customer", "MobileNumber", mobileNumber.toString()));
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }
}
