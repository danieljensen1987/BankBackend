package dk.cphbusiness.bank.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "MONNEYMARKETACCOUNT")
@NamedQueries({
    @NamedQuery(name = "Monneymarketaccount.findAll", query = "SELECT m FROM Monneymarketaccount m")})
public class Monneymarketaccount extends Account 
{
    private static final long serialVersionUID = 1L;
    
    @Column(name = "MINIMUMBALANCE")
    private BigDecimal minimumbalance;
    
    

    public Monneymarketaccount()
    {
    }

    public Monneymarketaccount(String accNumber)
    {
        super(accNumber);
    }

    public Monneymarketaccount(String accNumber, BigDecimal minimumbalance)
    {
        super(accNumber);
        this.minimumbalance = minimumbalance;
    }

    public BigDecimal getMinimumbalance()
    {
        return minimumbalance;
    }

    public void setMinimumbalance(BigDecimal minimumbalance)
    {
        this.minimumbalance = minimumbalance;
    }

    @Override
    public String toString()
    {
        return "dk.cphbusiness.bank.model.Monneymarketaccount[ accNumber=" + getAccNumber() + " ]";
    }
    
}
