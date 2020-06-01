
package dpusha.app.com.usha.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CartItem {

    @SerializedName("OrderId")
    @Expose
    private String orderId;
    @SerializedName("TemplateName")
    @Expose
    private Object templateName;
    @SerializedName("ReferenceNo")
    @Expose
    private Object referenceNo;
    @SerializedName("Description")
    @Expose
    private Object description;
    @SerializedName("ShipToPartyId")
    @Expose
    private String shipToPartyId;
    @SerializedName("RequestDeliveryDate")
    @Expose
    private String requestDeliveryDate;
    @SerializedName("Items")
    @Expose
    private List<Item> items = null;
    @SerializedName("DivCode")
    @Expose
    private Object divCode;
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
    private String orderStatus;

    @SerializedName("Id")
    @Expose
    private String Id;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CartItem() {
    }

    /**
     * 
     * @param referenceNo
     * @param divCode
     * @param orderId
     * @param totalDiscounts
     * @param shipToPartyId
     * @param totalGrossPrice
     * @param description
     * @param totalNetPrice
     * @param orderStatus
     * @param totalTax
     * @param schId
     * @param templateName
     * @param taxableValue
     * @param items
     * @param requestDeliveryDate
     */
    public CartItem(String orderId, Object templateName, Object referenceNo, Object description, String shipToPartyId, String requestDeliveryDate, List<Item> items, Object divCode, Double totalNetPrice, Double totalDiscounts, Double taxableValue, Double totalTax, Double totalGrossPrice, Object schId, String orderStatus, String id) {
        this.orderId = orderId;
        this.templateName = templateName;
        this.referenceNo = referenceNo;
        this.description = description;
        this.shipToPartyId = shipToPartyId;
        this.requestDeliveryDate = requestDeliveryDate;
        this.items = items;
        this.divCode = divCode;
        this.totalNetPrice = totalNetPrice;
        this.totalDiscounts = totalDiscounts;
        this.taxableValue = taxableValue;
        this.totalTax = totalTax;
        this.totalGrossPrice = totalGrossPrice;
        this.schId = schId;
        this.orderStatus = orderStatus;
        Id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Object getTemplateName() {
        return templateName;
    }

    public void setTemplateName(Object templateName) {
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

    public String getShipToPartyId() {
        return shipToPartyId;
    }

    public void setShipToPartyId(String shipToPartyId) {
        this.shipToPartyId = shipToPartyId;
    }

    public String getRequestDeliveryDate() {
        return requestDeliveryDate;
    }

    public void setRequestDeliveryDate(String requestDeliveryDate) {
        this.requestDeliveryDate = requestDeliveryDate;
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

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}
