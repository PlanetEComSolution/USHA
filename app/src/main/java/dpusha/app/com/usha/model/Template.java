
package dpusha.app.com.usha.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import dpusha.app.com.usha.model.draft.Item;

public class Template {

    @SerializedName("OrderId")
    @Expose
    private String orderId;
    @SerializedName("TemplateName")
    @Expose
    private String templateName;
    @SerializedName("ReferenceNo")
    @Expose
    private Object referenceNo;
    @SerializedName("Description")
    @Expose
    private Object description;
    @SerializedName("ShipToPartyId")
    @Expose
    private Object shipToPartyId;
    @SerializedName("RequestDeliveryDate")
    @Expose
    private String requestDeliveryDate;
    @SerializedName("Fright")
    @Expose
    private Integer fright;
    @SerializedName("Items")
    @Expose
    private List<Item> items = null;
    @SerializedName("DivCode")
    @Expose
    private Object divCode;
    @SerializedName("DivName")
    @Expose
    private Object divName;
    @SerializedName("TotalNetPrice")
    @Expose
    private Double totalNetPrice;
    @SerializedName("TotalDiscounts")
    @Expose
    private Double totalDiscounts;
    @SerializedName("TaxableValue")
    @Expose
    private Double taxableValue;
    @SerializedName("TotalTax")
    @Expose
    private Double totalTax;
    @SerializedName("TotalGrossPrice")
    @Expose
    private Double totalGrossPrice;
    @SerializedName("SchId")
    @Expose
    private Object schId;
    @SerializedName("OrderStatus")
    @Expose
    private Object orderStatus;
    @SerializedName("CreatedDate")
    @Expose
    private String createdDate;
    @SerializedName("ShipToParty")
    @Expose
    private Object shipToParty;
    @SerializedName("UnavailableItems")
    @Expose
    private Object unavailableItems;
    @SerializedName("DealerName")
    @Expose
    private Object dealerName;
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
    public Template() {
    }

    public Template(String orderId, String templateName, Object referenceNo, Object description, Object shipToPartyId, String requestDeliveryDate, Integer fright, List<Item> items, Object divCode, Object divName, Double totalNetPrice, Double totalDiscounts, Double taxableValue, Double totalTax, Double totalGrossPrice, Object schId, Object orderStatus, String createdDate, Object shipToParty, Object unavailableItems, Object dealerName, Integer id, Object createdBy, Boolean isActive, Boolean isDeleted, Object iP, Object callType, Object resultCode) {
        this.orderId = orderId;
        this.templateName = templateName;
        this.referenceNo = referenceNo;
        this.description = description;
        this.shipToPartyId = shipToPartyId;
        this.requestDeliveryDate = requestDeliveryDate;
        this.fright = fright;
        this.items = items;
        this.divCode = divCode;
        this.divName = divName;
        this.totalNetPrice = totalNetPrice;
        this.totalDiscounts = totalDiscounts;
        this.taxableValue = taxableValue;
        this.totalTax = totalTax;
        this.totalGrossPrice = totalGrossPrice;
        this.schId = schId;
        this.orderStatus = orderStatus;
        this.createdDate = createdDate;
        this.shipToParty = shipToParty;
        this.unavailableItems = unavailableItems;
        this.dealerName = dealerName;
        this.id = id;
        this.createdBy = createdBy;
        this.isActive = isActive;
        this.isDeleted = isDeleted;
        this.iP = iP;
        this.callType = callType;
        this.resultCode = resultCode;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public Object getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(Object referenceNo) {
        this.referenceNo = referenceNo;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public Object getShipToPartyId() {
        return shipToPartyId;
    }

    public void setShipToPartyId(Object shipToPartyId) {
        this.shipToPartyId = shipToPartyId;
    }

    public String getRequestDeliveryDate() {
        return requestDeliveryDate;
    }

    public void setRequestDeliveryDate(String requestDeliveryDate) {
        this.requestDeliveryDate = requestDeliveryDate;
    }

    public Integer getFright() {
        return fright;
    }

    public void setFright(Integer fright) {
        this.fright = fright;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Object getDivCode() {
        return divCode;
    }

    public void setDivCode(Object divCode) {
        this.divCode = divCode;
    }

    public Object getDivName() {
        return divName;
    }

    public void setDivName(Object divName) {
        this.divName = divName;
    }

    public Double getTotalNetPrice() {
        return totalNetPrice;
    }

    public void setTotalNetPrice(Double totalNetPrice) {
        this.totalNetPrice = totalNetPrice;
    }

    public Double getTotalDiscounts() {
        return totalDiscounts;
    }

    public void setTotalDiscounts(Double totalDiscounts) {
        this.totalDiscounts = totalDiscounts;
    }

    public Double getTaxableValue() {
        return taxableValue;
    }

    public void setTaxableValue(Double taxableValue) {
        this.taxableValue = taxableValue;
    }

    public Double getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(Double totalTax) {
        this.totalTax = totalTax;
    }

    public Double getTotalGrossPrice() {
        return totalGrossPrice;
    }

    public void setTotalGrossPrice(Double totalGrossPrice) {
        this.totalGrossPrice = totalGrossPrice;
    }

    public Object getSchId() {
        return schId;
    }

    public void setSchId(Object schId) {
        this.schId = schId;
    }

    public Object getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Object orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Object getShipToParty() {
        return shipToParty;
    }

    public void setShipToParty(Object shipToParty) {
        this.shipToParty = shipToParty;
    }

    public Object getUnavailableItems() {
        return unavailableItems;
    }

    public void setUnavailableItems(Object unavailableItems) {
        this.unavailableItems = unavailableItems;
    }

    public Object getDealerName() {
        return dealerName;
    }

    public void setDealerName(Object dealerName) {
        this.dealerName = dealerName;
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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Object getiP() {
        return iP;
    }

    public void setiP(Object iP) {
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

    public Template(String templateName, Integer id) {
        this.templateName = templateName;
        this.id = id;
    }

    @Override
    public String toString() {
        return  templateName;
    }
}
