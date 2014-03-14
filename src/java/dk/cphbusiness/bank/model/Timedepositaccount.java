package dk.cphbusiness.bank.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TIMEDEPOSITACCOUNT")
@NamedQueries({
    @NamedQuery(name = "Timedepositaccount.findAll", query = "SELECT t FROM Timedepositaccount t")})
public class Timedepositaccount extends Account 
{
    private static final long serialVersionUID = 1L;
    @Column(name = "RELEASEDATE")
    @Temporal(TemporalType.DATE)
    private Date releasedate;    

    public Timedepositaccount()
    {
    }

    public Timedepositaccount(String accNumber)
    {
        super(accNumber);
    }

    public Timedepositaccount(String accNumber, Date releasedate)
    {
        super(accNumber);
        this.releasedate = releasedate;
    }

    public Date getReleasedate()
    {
        return releasedate;
    }

    public void setReleasedate(Date releasedate)
    {
        this.releasedate = releasedate;
    }
    
    @Override
    public String toString()
    {
        return "dk.cphbusiness.bank.model.Timedepositaccount[ accNumber=" + getAccNumber() + " ]";
    }
    
}
