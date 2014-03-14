package dk.cphbusiness.bank.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "EMPLOYEE")
@NamedQueries({
    @NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e")})
public class Employee extends Person 
{
    private static final long serialVersionUID = 1L;
    @Column(name = "SALARY")
    private BigDecimal salary;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATEOFEMPLOYMENT")
    @Temporal(TemporalType.DATE)
    private Date dateofemployment;

    public Employee()
    {
    }

    public Employee(String cpr)
    {
        super(cpr);
    }

    public Employee(String cpr, BigDecimal salary, Date dateofemployment)
    {
        super(cpr);
        this.salary = salary;
        this.dateofemployment = dateofemployment;
    }

    public BigDecimal getSalary()
    {
        return salary;
    }

    public void setSalary(BigDecimal salary)
    {
        this.salary = salary;
    }

    public Date getDateofemployment()
    {
        return dateofemployment;
    }

    public void setDateofemployment(Date dateofemployment)
    {
        this.dateofemployment = dateofemployment;
    }
    @Override
    public String toString()
    {
        return "dk.cphbusiness.bank.model.Employee[ cpr=" + getCpr() + " ]";
    }
    
}
