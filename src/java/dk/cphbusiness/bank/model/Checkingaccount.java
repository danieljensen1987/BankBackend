package dk.cphbusiness.bank.model;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "CHECKINGACCOUNT")
@NamedQueries({
    @NamedQuery(name = "Checkingaccount.findAll", query = "SELECT c FROM Checkingaccount c")})
public class Checkingaccount extends Account 
{
    private static final long serialVersionUID = 1L;
    public Checkingaccount()
    {
    }

    public Checkingaccount(String accNumber)
    {
        super(accNumber);
    }

    @Override
    public String toString()
    {
        return "dk.cphbusiness.bank.model.Checkingaccount[ accNumber=" + getAccNumber() + " ]";
    }
    
}
