package dk.cphbusiness.bank.control;

import dk.cphbusiness.bank.contract.dto.AccountSummary;
import dk.cphbusiness.bank.contract.dto.CustomerSummary;
import dk.cphbusiness.bank.model.Account;
import dk.cphbusiness.bank.model.Person;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

public class Assembler
{

    public static CustomerSummary createCustomerSummary(Person customer)
    {
        return new CustomerSummary(
                customer.getCpr(),
                customer.getFirstname() + " " + customer.getLastname(),
                customer.getStreet() + ", " + customer.getPostal().getCode() + " " + customer.getPostal().getDistrict(),
                customer.getPhone(),
                customer.getEmail());
    }

    public static Collection<CustomerSummary> createCustomerSummaries(Collection<Person> customers)
    {
        Collection<CustomerSummary> summaries = new ArrayList<>();
        if (customers == null) return summaries;
        for (Person customer : customers) {
            summaries.add(createCustomerSummary(customer));
        }
        return summaries;
    }
    
    public static AccountSummary createAccountSummary(Account account) {
    return new AccountSummary(
        account.getAccNumber(),
        "Checking Account",
        new BigDecimal(100000)
        );
    }
  
  public static Collection<AccountSummary> createAccountSummaries(Collection<Account> accounts) {
    Collection<AccountSummary> summaries = new ArrayList<>();
    if (accounts == null) return summaries;
    for (Account account : accounts) summaries.add(createAccountSummary(account));
    return summaries;
    }
}
