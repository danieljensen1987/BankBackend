package dk.cphbusiness.bank.model;

import java.io.Serializable; 
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "ROLE")
@NamedQueries({
    @NamedQuery(name = "Role.findAll", query = "SELECT r FROM Role r")})
public class Role implements Serializable 
{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "CPR")
    private String cpr;
    @Size(max = 30)
    @Column(name = "NAME")
    private String name;
    @JoinColumn(name = "CPR", referencedColumnName = "CPR", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Person person;

    public Role()
    {
    }

    public Role(String cpr)
    {
        this.cpr = cpr;
    }

    public String getCpr()
    {
        return cpr;
    }

    public void setCpr(String cpr)
    {
        this.cpr = cpr;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
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
        hash += (cpr != null ? cpr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Role)) {
            return false;
        }
        Role other = (Role) object;
        if ((this.cpr == null && other.cpr != null) || (this.cpr != null && !this.cpr.equals(other.cpr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "dk.cphbusiness.bank.model.Role[ cpr=" + cpr + " ]";
    }
    
}
