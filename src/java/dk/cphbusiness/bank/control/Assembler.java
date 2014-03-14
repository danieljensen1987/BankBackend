package dk.cphbusiness.bank.control;

import dk.cphbusiness.bank.contract.dto.PersonSummary;
import dk.cphbusiness.bank.model.Person;
import java.util.ArrayList;
import java.util.Collection;

public class Assembler 
{
    public static PersonSummary createPersonSummary(Person person){
        
        return new PersonSummary(person.getCpr(), person.getFirstname(), person.getLastname(), person.getPhone());
        
    }
    
    public static Collection<PersonSummary> createPersonSummaries(Collection<Person> persons){
        Collection<PersonSummary> summaries = new ArrayList<>();
        for (Person person : persons) summaries.add(createPersonSummary(person));
        return summaries;
    }
}
