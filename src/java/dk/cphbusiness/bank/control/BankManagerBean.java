package dk.cphbusiness.bank.control;

import dk.cphbusiness.bank.contract.BankManager;
import dk.cphbusiness.bank.contract.dto.AccountDetail;
import dk.cphbusiness.bank.contract.dto.AccountIdentifier;
import dk.cphbusiness.bank.contract.dto.AccountSummary;
import dk.cphbusiness.bank.contract.dto.CheckingAccountDetail;
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
import dk.cphbusiness.bank.model.CheckingAccount;
import dk.cphbusiness.bank.model.Postal;
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
    public AccountDetail transferAmount(BigDecimal amount, AccountIdentifier source, AccountIdentifier target) throws NoSuchAccountException, TransferNotAcceptedException, InsufficientFundsException
    {

    int transferId = 1000;
    Account sourceAccount = em.find(Account.class, source.getNumber());
    Account targetAccount = em.find(Account.class, target.getNumber());
    sourceAccount.setBalance(sourceAccount.getBalance().subtract(amount));
    targetAccount.setBalance(targetAccount.getBalance().add(amount));
    Transfer t = new Transfer(String.valueOf(transferId), amount.negate(), sourceAccount, targetAccount);
    t.setTransferDate(new Date());
    transferId++;
    em.persist(t);
    return createAccountDetail(sourceAccount);
    }

    @Override
    public AccountDetail showAccountHistory(AccountIdentifier identifier)
    {
        Account account = em.find(Account.class, identifier.getNumber());
        em.refresh(account);
        return createAccountDetail(account);

    }

    @Override
    public CustomerDetail saveCustomer(CustomerDetail customer)
    {
        if(em.find(Person.class, customer.getCpr()) == null){
        Person person = new Person(customer.getCpr(),
        customer.getFirstName(),customer.getLastName(),customer.getStreet(),customer.getPhone());
        person.setTitle(customer.getTitle());
        person.setPostal(new Postal(Integer.parseInt(customer.getPostalCode()),customer.getPostalDistrict()));
        person.setEmail(customer.getEmail());
        em.persist(person);
        return createCustomerDetail(person);
        }else {
        return customer; 
        }
    }

    @Override
    public CustomerDetail showCustomer(CustomerIdentifier customerIdentifier) throws NoSuchCustomerException
    {
        Person customer = em.find(Person.class, customerIdentifier.getCpr());
        if (customer == null) {
            throw new NoSuchCustomerException(customerIdentifier);
        }
        return createCustomerDetail(customer);
    }

    @Override
    public AccountDetail createAccount(CustomerIdentifier ci, AccountDetail ad) throws NoSuchCustomerException, CustomerBannedException
    {
        Person customer = em.find(Person.class, ci.getCpr());
        if (customer == null) {
            throw new NoSuchCustomerException(ci);
        }
        if (ad instanceof CheckingAccountDetail) {
            CheckingAccount ca = createCheckingAccountEntity((CheckingAccountDetail)ad);
            em.persist(ca);
            return createAccountDetail(ca);
        }
        throw new RuntimeException("Unknown Account type");
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
