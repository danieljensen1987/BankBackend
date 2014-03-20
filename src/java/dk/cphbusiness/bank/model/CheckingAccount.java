package dk.cphbusiness.bank.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "CHECKINGACCOUNT")
//@DiscriminatorValue(value = "1")
@NamedQueries({
    @NamedQuery(name = "CheckingAccount.findAll", query = "SELECT c FROM CheckingAccount c")})
public class CheckingAccount extends Account 
{
    private static final long serialVersionUID = 1L;
    public CheckingAccount()
    {
    }

    public CheckingAccount(String accNumber)
    {
        super(accNumber);
    }

    @Override
    public String toString()
    {
        return "dk.cphbusiness.bank.model.Checkingaccount[ accNumber=" + getAccNumber() + " ]";
    }
    
}
