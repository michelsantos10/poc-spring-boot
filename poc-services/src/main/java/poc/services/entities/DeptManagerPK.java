/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poc.services.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author miche
 */
@Embeddable
public class DeptManagerPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "emp_no")
    private int empNo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "dept_no")
    private String deptNo;

    public DeptManagerPK() {
    }

    public DeptManagerPK(int empNo, String deptNo) {
        this.empNo = empNo;
        this.deptNo = deptNo;
    }

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) empNo;
        hash += (deptNo != null ? deptNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DeptManagerPK)) {
            return false;
        }
        DeptManagerPK other = (DeptManagerPK) object;
        if (this.empNo != other.empNo) {
            return false;
        }
        if ((this.deptNo == null && other.deptNo != null) || (this.deptNo != null && !this.deptNo.equals(other.deptNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "poc.services.entities.DeptManagerPK[ empNo=" + empNo + ", deptNo=" + deptNo + " ]";
    }
    
}
