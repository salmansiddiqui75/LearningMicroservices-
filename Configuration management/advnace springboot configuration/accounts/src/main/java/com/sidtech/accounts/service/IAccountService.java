package com.sidtech.accounts.service;

import com.sidtech.accounts.dto.CustomerDto;

public interface IAccountService
{
    void createAccount(CustomerDto customerDto);

    public CustomerDto fetchAccount(String mobileNumber);
    boolean updateAcount(CustomerDto customerDto);
    boolean deleteAccount(String mobileNumber);
}
