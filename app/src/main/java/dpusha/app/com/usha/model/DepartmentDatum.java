
package dpusha.app.com.usha.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DepartmentDatum {

    @SerializedName("DepartmentCode")
    @Expose
    private String departmentCode;
    @SerializedName("Dept_LongDesc")
    @Expose
    private String deptLongDesc;
    @SerializedName("SalesOfficeCode")
    @Expose
    private Object salesOfficeCode;
    @SerializedName("SalesofficeName")
    @Expose
    private Object salesofficeName;
    @SerializedName("EmployeeId")
    @Expose
    private Object employeeId;
    @SerializedName("EmpCode")
    @Expose
    private Object empCode;
    @SerializedName("EmpName")
    @Expose
    private Object empName;
    @SerializedName("ProfileCode")
    @Expose
    private Object profileCode;
    @SerializedName("ProfileName")
    @Expose
    private Object profileName;
    @SerializedName("PreFix")
    @Expose
    private Object preFix;
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("CreatedBy")
    @Expose
    private Object createdBy;
    @SerializedName("IsActive")
    @Expose
    private Boolean isActive;
    @SerializedName("IsDeleted")
    @Expose
    private Boolean isDeleted;
    @SerializedName("IP")
    @Expose
    private Object iP;
    @SerializedName("CallType")
    @Expose
    private Object callType;
    @SerializedName("ResultCode")
    @Expose
    private Object resultCode;

    /**
     * No args constructor for use in serialization
     * 
     */
    public DepartmentDatum() {
    }

    /**
     * 
     * @param profileName
     * @param deptLongDesc
     * @param preFix
     * @param departmentCode
     * @param iP
     * @param resultCode
     * @param salesofficeName
     * @param employeeId
     * @param isActive
     * @param callType
     * @param salesOfficeCode
     * @param isDeleted
     * @param empCode
     * @param createdBy
     * @param empName
     * @param profileCode
     * @param id
     */
    public DepartmentDatum(String departmentCode, String deptLongDesc, Object salesOfficeCode, Object salesofficeName, Object employeeId, Object empCode, Object empName, Object profileCode, Object profileName, Object preFix, Integer id, Object createdBy, Boolean isActive, Boolean isDeleted, Object iP, Object callType, Object resultCode) {
        super();
        this.departmentCode = departmentCode;
        this.deptLongDesc = deptLongDesc;
        this.salesOfficeCode = salesOfficeCode;
        this.salesofficeName = salesofficeName;
        this.employeeId = employeeId;
        this.empCode = empCode;
        this.empName = empName;
        this.profileCode = profileCode;
        this.profileName = profileName;
        this.preFix = preFix;
        this.id = id;
        this.createdBy = createdBy;
        this.isActive = isActive;
        this.isDeleted = isDeleted;
        this.iP = iP;
        this.callType = callType;
        this.resultCode = resultCode;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getDeptLongDesc() {
        return deptLongDesc;
    }

    public void setDeptLongDesc(String deptLongDesc) {
        this.deptLongDesc = deptLongDesc;
    }

    public Object getSalesOfficeCode() {
        return salesOfficeCode;
    }

    public void setSalesOfficeCode(Object salesOfficeCode) {
        this.salesOfficeCode = salesOfficeCode;
    }

    public Object getSalesofficeName() {
        return salesofficeName;
    }

    public void setSalesofficeName(Object salesofficeName) {
        this.salesofficeName = salesofficeName;
    }

    public Object getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Object employeeId) {
        this.employeeId = employeeId;
    }

    public Object getEmpCode() {
        return empCode;
    }

    public void setEmpCode(Object empCode) {
        this.empCode = empCode;
    }

    public Object getEmpName() {
        return empName;
    }

    public void setEmpName(Object empName) {
        this.empName = empName;
    }

    public Object getProfileCode() {
        return profileCode;
    }

    public void setProfileCode(Object profileCode) {
        this.profileCode = profileCode;
    }

    public Object getProfileName() {
        return profileName;
    }

    public void setProfileName(Object profileName) {
        this.profileName = profileName;
    }

    public Object getPreFix() {
        return preFix;
    }

    public void setPreFix(Object preFix) {
        this.preFix = preFix;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Object getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Object createdBy) {
        this.createdBy = createdBy;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Object getIP() {
        return iP;
    }

    public void setIP(Object iP) {
        this.iP = iP;
    }

    public Object getCallType() {
        return callType;
    }

    public void setCallType(Object callType) {
        this.callType = callType;
    }

    public Object getResultCode() {
        return resultCode;
    }

    public void setResultCode(Object resultCode) {
        this.resultCode = resultCode;
    }

    @Override
    public String toString() {
        return deptLongDesc ;
    }
}
