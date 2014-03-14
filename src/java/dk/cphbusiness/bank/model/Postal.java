package dk.cphbusiness.bank.model;

import java.io.Serializable; 
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "POSTAL")
@NamedQueries({
    @NamedQuery(name = "Postal.findAll", query = "SELECT p FROM Postal p")})
public class Postal implements Serializable 
{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODE")
    private Integer code;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "DISTRICT")
    private String district;
    @OneToMany(mappedBy = "postal")
    private Collection<Person> personCollection;

    public Postal()
    {
    }

    public Postal(Integer code)
    {
        this.code = code;
    }

    public Postal(Integer code, String district)
    {
        this.code = code;
        this.district = district;
    }

    public Integer getCode()
    {
        return code;
    }

    public void setCode(Integer code)
    {
        this.code = code;
    }

    public String getDistrict()
    {
        return district;
    }

    public void setDistrict(String district)
    {
        this.district = district;
    }

    public Collection<Person> getPersonCollection()
    {
        return personCollection;
    }

    public void setPersonCollection(Collection<Person> personCollection)
    {
        this.personCollection = personCollection;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (code != null ? code.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Postal)) {
            return false;
        }
        Postal other = (Postal) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "dk.cphbusiness.bank.model.Postal[ code=" + code + " ]";
    }
    
}
