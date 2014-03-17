package dk.cphbusiness.bank.model;

import java.io.Serializable; 
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "ACCOUNT")
@Inheritance (strategy = InheritanceType.JOINED)
@NamedQueries({
    @NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a")})
public abstract class Account implements Serializable 
{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "ACC_NUMBER")
    private String accNumber;

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "INTEREST")
    private BigDecimal interest;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "BALANCE")
    private BigDecimal balance;
    
    @OneToMany(mappedBy = "sourceAccount")
    private Collection<Transfer> incomingTransfers;
    
    @OneToMany(mappedBy = "targetAccount")
    private Collection<Transfer> outgoingTransfers;
    
    @JoinColumn(name = "CPR", referencedColumnName = "CPR")
    @ManyToOne
    private Person person;

    public Account()
    {
    }

    public Account(String accNumber)
    {
        this.accNumber = accNumber;
    }

    public Account(String accNumber, BigDecimal interest, BigDecimal balance)
    {
        this.accNumber = accNumber;
        this.interest = interest;
        this.balance = balance;
    }

    public String getAccNumber()
    {
        return accNumber;
    }

    public void setAccNumber(String accNumber)
    {
        this.accNumber = accNumber;
    }

    public BigDecimal getInterest()
    {
        return interest;
    }

    public void setInterest(BigDecimal interest)
    {
        this.interest = interest;
    }

    public BigDecimal getBalance()
    {
        return balance;
    }

    public void setBalance(BigDecimal balance)
    {
        this.balance = balance;
    }

    public Collection<Transfer> getIncomingTransfers()
    {
        return incomingTransfers;
    }

    public void setIncomingTransfers(Collection<Transfer> incomingTransfers)
    {
        this.incomingTransfers = incomingTransfers;
    }

    public Collection<Transfer> getOutgoingTransfers()
    {
        return outgoingTransfers;
    }

    public void setOutgoingTransfers(Collection<Transfer> outgoingTransfers)
    {
        this.outgoingTransfers = outgoingTransfers;
    }

    public Person getPerson()
    {
        return person;
    }

    public void setPerson(Person person)
    {
        this.person = person;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (accNumber != null ? accNumber.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        if ((this.accNumber == null && other.accNumber != null) || (this.accNumber != null && !this.accNumber.equals(other.accNumber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "dk.cphbusiness.bank.model.Account[ accNumber=" + accNumber + " ]";
    }
    
}
