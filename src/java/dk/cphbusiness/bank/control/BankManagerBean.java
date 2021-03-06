package dk.cphbusiness.bank.control;

import dk.cphbusiness.bank.contract.BankManager;
//import dk.cphbusiness.bank.contract.extended.BankManager;
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

        //int transferId;
        Account sourceAccount = em.find(Account.class, source.getNumber());
        Account targetAccount = em.find(Account.class, target.getNumber());
        sourceAccount.setBalance(sourceAccount.getBalance().subtract(amount));
        targetAccount.setBalance(targetAccount.getBalance().add(amount));
        Transfer t = new Transfer(null, amount.negate(), sourceAccount, targetAccount);
        t.setTransferDate(new Date());
        //transferId++;
        em.persist(t);
        System.out.println("TRANSFER");
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
        CustomerDetail cd = null;
        if (em.find(Person.class, customer.getCpr()) == null) {
            Person p = new Person(customer.getCpr(),
                    customer.getFirstName(), customer.getLastName(), customer.getStreet(), customer.getPhone());
            p.setTitle(customer.getTitle());
            p.setPostal(new Postal(Integer.parseInt(customer.getPostalCode()), customer.getPostalDistrict()));
            p.setEmail(customer.getEmail());
            em.persist(p);
            cd = createCustomerDetail(p);
        }
        if (em.find(Person.class, customer.getCpr()) != null) {
            Person p = em.find(Person.class, customer.getCpr());
            p.setTitle(customer.getTitle());
            p.setFirstname(customer.getFirstName());
            p.setLastname(customer.getLastName());
            p.setStreet(customer.getStreet());
            p.setPhone(customer.getPhone());
            p.setPostal(new Postal(Integer.parseInt(customer.getPostalCode()), customer.getPostalDistrict()));
            p.setEmail(customer.getEmail());
            em.persist(p);
            cd = createCustomerDetail(p);
        }
        return cd;
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
            CheckingAccount ca = createCheckingAccountEntity((CheckingAccountDetail) ad);
            System.out.println("blab albabl: " + ca);
            em.persist(ca);
            customer.getAccountCollection().add(ca);
            em.persist(customer);
            return createAccountDetail(ca);
        }
        throw new RuntimeException("Unknown Account type");
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
//    @Override
//    public boolean doesUserExist(CustomerIdentifier ci)
//    {
//        boolean res = false;
//        Person customer = em.find(Person.class, ci.getCpr());
//        if (customer == null) {
//            res = true;
//            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//        return res;
//    }
//
//    @Override
//    public CustomerDetail saveEmployee(CustomerDetail customer) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

 
}
