package dk.cphbusiness.bank.control;

import dk.cphbusiness.bank.contract.dto.CustomerSummary;
import dk.cphbusiness.bank.model.Person;
import java.util.ArrayList;
import java.util.Collection;

public class Assembler 
{
    public static CustomerSummary createCustomerSummary(Person person){
        
        return new CustomerSummary(person.getCpr(), person.getFirstname()+ " " + person.getLastname(),person.getStreet(), person.getPhone(), person.getEmail());
        
    }
    
    public static Collection<CustomerSummary> createCustomerSummaries(Collection<Person> persons){
        Collection<CustomerSummary> summaries = new ArrayList<>();
        for (Person person : persons) summaries.add(createCustomerSummary(person));
        return summaries;
    }
}
