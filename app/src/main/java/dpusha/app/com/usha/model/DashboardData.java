
package dpusha.app.com.usha.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DashboardData {

    @SerializedName("Sales")
    @Expose
    private Object sales;
    @SerializedName("CreditLimits")
    @Expose
    private Object creditLimits;
    @SerializedName("TotalOutstanding")
    @Expose
    private Integer totalOutstanding;
    @SerializedName("TotalOrdersPending")
    @Expose
    private Integer totalOrdersPending;
    @SerializedName("TotalKAMs")
    @Expose
    private Integer totalKAMs;
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
    public DashboardData() {
    }

    /**
     * 
     * @param totalKAMs
     * @param totalOutstanding
     * @param isDeleted
     * @param createdBy
     * @param iP
     * @param resultCode
     * @param creditLimits
     * @param id
     * @param totalOrdersPending
     * @param isActive
     * @param sales
     * @param callType
     */
    public DashboardData(Object sales, Object creditLimits, Integer totalOutstanding, Integer totalOrdersPending, Integer totalKAMs, Integer id, Object createdBy, Boolean isActive, Boolean isDeleted, Object iP, Object callType, Object resultCode) {
        super();
        this.sales = sales;
        this.creditLimits = creditLimits;
        this.totalOutstanding = totalOutstanding;
        this.totalOrdersPending = totalOrdersPending;
        this.totalKAMs = totalKAMs;
        this.id = id;
        this.createdBy = createdBy;
        this.isActive = isActive;
        this.isDeleted = isDeleted;
        this.iP = iP;
        this.callType = callType;
        this.resultCode = resultCode;
    }

    public Object getSales() {
        return sales;
    }

    public void setSales(Object sales) {
        this.sales = sales;
    }

    public Object getCreditLimits() {
        return creditLimits;
    }

    public void setCreditLimits(Object creditLimits) {
        this.creditLimits = creditLimits;
    }

    public Integer getTotalOutstanding() {
        return totalOutstanding;
    }

    public void setTotalOutstanding(Integer totalOutstanding) {
        this.totalOutstanding = totalOutstanding;
    }

    public Integer getTotalOrdersPending() {
        return totalOrdersPending;
    }

    public void setTotalOrdersPending(Integer totalOrdersPending) {
        this.totalOrdersPending = totalOrdersPending;
    }

    public Integer getTotalKAMs() {
        return totalKAMs;
    }

    public void setTotalKAMs(Integer totalKAMs) {
        this.totalKAMs = totalKAMs;
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

}
