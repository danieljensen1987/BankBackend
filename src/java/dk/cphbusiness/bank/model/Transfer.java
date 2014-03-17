package dk.cphbusiness.bank.model;

import java.io.Serializable; 
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "TRANSFER")
@NamedQueries({
    @NamedQuery(name = "Transfer.findAll", query = "SELECT t FROM Transfer t")})
public class Transfer implements Serializable 
{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "TRANSFER_ID")
    private String transferId;
 
    @Basic(optional = false)
    @NotNull
    @Column(name = "TRANSFER_DATE")
    @Temporal(TemporalType.DATE)
    private Date transferDate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "AMOUNT")
    private BigDecimal amount;
    
    @JoinColumn(name = "SOURCE_ACC_NUMBER", referencedColumnName = "ACC_NUMBER")
    @ManyToOne
    private Account sourceAccount;

    @JoinColumn(name = "TARGET_ACC_NUMBER", referencedColumnName = "ACC_NUMBER")
    @ManyToOne
    private Account targetAccount;

    public Transfer()
    {
    }

    public Transfer(String transferId)
    {
        this.transferId = transferId;
    }

    public Transfer(String transferId, Date transferDate, BigDecimal amount)
    {
        this.transferId = transferId;
        this.transferDate = transferDate;
        this.amount = amount;
    }

    public String getTransferId()
    {
        return transferId;
    }

    public void setTransferId(String transferId)
    {
        this.transferId = transferId;
    }

    public Date getTransferDate()
    {
        return transferDate;
    }

    public void setTransferDate(Date transferDate)
    {
        this.transferDate = transferDate;
    }

    public BigDecimal getAmount()
    {
        return amount;
    }

    public void setAmount(BigDecimal amount)
    {
        this.amount = amount;
    }

    public Account getSourceAccount()
    {
        return sourceAccount;
    }

    public void setSourceAccount(Account sourceAccount)
    {
        this.sourceAccount = sourceAccount;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (transferId != null ? transferId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transfer)) {
            return false;
        }
        Transfer other = (Transfer) object;
        if ((this.transferId == null && other.transferId != null) || (this.transferId != null && !this.transferId.equals(other.transferId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "dk.cphbusiness.bank.model.Transfer[ transferId=" + transferId + " ]";
    }
    
}
