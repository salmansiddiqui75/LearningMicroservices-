package com.sidtech.accounts.mapper;

import com.sidtech.accounts.dto.AccountDto;
import com.sidtech.accounts.entity.Accounts;

public class AccountMapper
{
    public static AccountDto mapToAccountsDto(Accounts account, AccountDto accountDto)
    {
        accountDto.setAccountNumber(account.getAccountNumber());
        accountDto.setAccountType(account.getAccountType());
        accountDto.setBranchAddress(account.getBranchAddress());
        return accountDto;
    }
    public static Accounts mapToAccounts(AccountDto accountsDto, Accounts accounts)
    {
        accounts.setAccountNumber(accountsDto.getAccountNumber());
        accounts.setAccountType(accountsDto.getAccountType());
        accounts.setBranchAddress(accountsDto.getBranchAddress());
        return accounts;
    }
}
