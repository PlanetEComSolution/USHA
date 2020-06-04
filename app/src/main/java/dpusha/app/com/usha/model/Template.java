
package dpusha.app.com.usha.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
    private List<Object> items = null;
    @SerializedName("DivCode")
    @Expose
    private Object divCode;
    @SerializedName("DivName")
    @Expose
    private Object divName;
    @SerializedName("TotalNetPrice")
    @Expose
    private Integer totalNetPrice;
    @SerializedName("TotalDiscounts")
    @Expose
    private Integer totalDiscounts;
    @SerializedName("TaxableValue")
    @Expose
    private Integer taxableValue;
    @SerializedName("TotalTax")
    @Expose
    private Integer totalTax;
    @SerializedName("TotalGrossPrice")
    @Expose
    private Integer totalGrossPrice;
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

    /**
     * 
     * @param divName
     * @param orderId
     * @param totalDiscounts
     * @param iP
     * @param shipToPartyId
     * @param totalGrossPrice
     * @param resultCode
     * @param description
     * @param orderStatus
     * @param isActive
     * @param callType
     * @param totalTax
     * @param schId
     * @param isDeleted
     * @param id
     * @param unavailableItems
     * @param dealerName
     * @param referenceNo
     * @param divCode
     * @param totalNetPrice
     * @param fright
     * @param createdDate
     * @param templateName
     * @param taxableValue
     * @param createdBy
     * @param items
     * @param shipToParty
     * @param requestDeliveryDate
     */
    public Template(String orderId, String templateName, Object referenceNo, Object description, Object shipToPartyId, String requestDeliveryDate, Integer fright, List<Object> items, Object divCode, Object divName, Integer totalNetPrice, Integer totalDiscounts, Integer taxableValue, Integer totalTax, Integer totalGrossPrice, Object schId, Object orderStatus, String createdDate, Object shipToParty, Object unavailableItems, Object dealerName, Integer id, Object createdBy, Boolean isActive, Boolean isDeleted, Object iP, Object callType, Object resultCode) {
        super();
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

    public List<Object> getItems() {
        return items;
    }

    public void setItems(List<Object> items) {
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

    public Integer getTotalNetPrice() {
        return totalNetPrice;
    }

    public void setTotalNetPrice(Integer totalNetPrice) {
        this.totalNetPrice = totalNetPrice;
    }

    public Integer getTotalDiscounts() {
        return totalDiscounts;
    }

    public void setTotalDiscounts(Integer totalDiscounts) {
        this.totalDiscounts = totalDiscounts;
    }

    public Integer getTaxableValue() {
        return taxableValue;
    }

    public void setTaxableValue(Integer taxableValue) {
        this.taxableValue = taxableValue;
    }

    public Integer getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(Integer totalTax) {
        this.totalTax = totalTax;
    }

    public Integer getTotalGrossPrice() {
        return totalGrossPrice;
    }

    public void setTotalGrossPrice(Integer totalGrossPrice) {
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
