package dk.cphbusiness.bank.control;

import dk.cphbusiness.bank.contract.BankManager;
import dk.cphbusiness.bank.contract.dto.AccountDetail;
import dk.cphbusiness.bank.contract.dto.AccountIdentifier;
import dk.cphbusiness.bank.contract.dto.AccountSummary;
import dk.cphbusiness.bank.contract.dto.CustomerDetail;
import dk.cphbusiness.bank.contract.dto.CustomerIdentifier;
import dk.cphbusiness.bank.contract.dto.CustomerSummary;
import dk.cphbusiness.bank.contract.eto.CustomerBannedException;
import dk.cphbusiness.bank.contract.eto.InsufficientFundsException;
import dk.cphbusiness.bank.contract.eto.NoSuchAccountException;
import dk.cphbusiness.bank.contract.eto.NoSuchCustomerException;
import dk.cphbusiness.bank.contract.eto.TransferNotAcceptedException;
import dk.cphbusiness.bank.model.Person;
import java.math.BigDecimal;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import static dk.cphbusiness.bank.control.Assembler.*;
import dk.cphbusiness.bank.model.Account;

@Stateless
public class BankManagerBean implements BankManager
{

    @PersistenceContext(unitName = "BankBackendPU")
    private EntityManager em;

    @Override
    public String sayHello(String name)
    {
        return "hello backend" + name;
    }

    @Override
    public Collection<CustomerSummary> listCustomers()
    {
        Query query = em.createNamedQuery("Person.findAll");
        Collection<Person> persons = query.getResultList();
        return createCustomerSummaries(persons);
    }

    @Override
    public Collection<AccountSummary> listAccounts()
    {
        Query query = em.createNamedQuery("Account.findAll");
        Collection<Account> accounts = query.getResultList();
        return createAccountSummaries(accounts);
    }

    @Override
    public Collection<AccountSummary> listCustomerAccounts(CustomerIdentifier customer)
    {
        System.out.println("xxxxxxxxxxxxxxxxxxxxxx" + customer.getCpr());
        String cpr = customer.getCpr();
        Query query = em.createNativeQuery("SELECT * FROM Account WHERE customer_cpr = " + customer.getCpr() );
        Collection<Account> accounts = query.getResultList();
        return createAccountSummaries(accounts);
    }

    @Override
    public Collection<String> listAccountTypes()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AccountDetail transferAmount(BigDecimal amount, AccountIdentifier source, AccountIdentifier target) throws NoSuchAccountException, TransferNotAcceptedException, InsufficientFundsException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AccountDetail showAccountHistory(AccountIdentifier account)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CustomerDetail saveCustomer(CustomerDetail customer)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CustomerDetail showCustomer(CustomerIdentifier customer) throws NoSuchCustomerException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AccountDetail createAccount(CustomerIdentifier customer, AccountDetail account) throws NoSuchCustomerException, CustomerBannedException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
