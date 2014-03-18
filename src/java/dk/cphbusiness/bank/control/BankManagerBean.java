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
import dk.cphbusiness.bank.model.Transfer;
import java.util.Date;

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
    public Collection<AccountSummary> listCustomerAccounts(CustomerIdentifier customerIdentifier)
    {
        System.out.println("xxxxxxxxxxxxxxxxxxxxxx" + customerIdentifier.getCpr());
        Person customer = em.find(Person.class, customerIdentifier.getCpr());
        Collection<Account> accounts = customer.getAccountCollection();

//        Collection<Account> accounts2
//                = em.createNamedQuery("Account.findByCustomerCpr")
//                .setParameter("cpr", customerIdentifier.getCpr())
//                .getResultList();
       return createAccountSummaries(accounts);
    }

    @Override
    public Collection<String> listAccountTypes()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   @Override
  public AccountDetail transferAmount(BigDecimal amount, AccountIdentifier source, AccountIdentifier target) throws NoSuchAccountException, TransferNotAcceptedException, InsufficientFundsException {
       
//    Account sourceAccount = em.find(Account.class, source.getNumber());
//    Account targetAccount = em.find(Account.class, target.getNumber());
//    Transfer tTransfer =  new Transfer("3", new Date(), amount, sourceAccount, targetAccount);
//    em.persist(tTransfer);
//    return createAccountDetail(sourceAccount);
     Account sourceAccount = em.find(Account.class, source.getNumber());
    Account targetAccount = em.find(Account.class, target.getNumber());
    sourceAccount.setBalance(sourceAccount.getBalance().subtract(amount));
    targetAccount.setBalance(targetAccount.getBalance().add(amount));
    Transfer t = new Transfer("5", amount.negate(), sourceAccount, targetAccount);
    t.setTransferDate(new Date());
    em.persist(t);
    return createAccountDetail(sourceAccount);
    
      
     
    }

    @Override
    public AccountDetail showAccountHistory(AccountIdentifier identifier)
    {
        Account account = em.find(Account.class, identifier.getNumber());
        return createAccountDetail(account);
       
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
