package dpusha.app.com.usha.orders_home.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderList {





    @SerializedName("OrderId")
    @Expose
    private String OrderId;



    @SerializedName("TemplateName")
    @Expose
    String TemplateName;




    @SerializedName("ReferenceNo")
    @Expose
    String ReferenceNo;

    @SerializedName("Description")
    @Expose
    String Description;

    @SerializedName("ShipToPartyId")
    @Expose
    String ShipToPartyId;

    @SerializedName("RequestDeliveryDate")
    @Expose
    String RequestDeliveryDate;

    @SerializedName("Fright")
    @Expose
    String Fright;

  /*  @SerializedName("Items")
    @Expose
    String[] Items;*/

    @SerializedName("DivCode")
    @Expose
    String DivCode;

    @SerializedName("DivName")
    @Expose
    String DivName;

    @SerializedName("TotalNetPrice")
    @Expose
    String TotalNetPrice;

    @SerializedName("TotalDiscounts")
    @Expose
    String TotalDiscounts;

    @SerializedName("TaxableValue")
    @Expose
    String TaxableValue;

    @SerializedName("TotalTax")
    @Expose
    String TotalTax;

    @SerializedName("TotalGrossPrice")
    @Expose
    String TotalGrossPrice;

    @SerializedName("SchId")
    @Expose
    String SchId;

    @SerializedName("OrderStatus")
    @Expose
    String OrderStatus;

    @SerializedName("CreatedDate")
    @Expose
    String CreatedDate;

    @SerializedName("ShipToParty")
    @Expose
    String ShipToParty;

    @SerializedName("UnavailableItems")
    @Expose
    String UnavailableItems;

    @SerializedName("DealerName")
    @Expose
    String DealerName;

    @SerializedName("Id")
    @Expose
    String Id;

    @SerializedName("CreatedBy")
    @Expose
    String CreatedBy;

    @SerializedName("IsActive")
    @Expose
    String IsActive;

    @SerializedName("IsDeleted")
    @Expose
    String IsDeleted;

    @SerializedName("IP")
    @Expose
    String IP;

    @SerializedName("CallType")
    @Expose
    String CallType;

    public OrderList(String orderId, String templateName, String referenceNo, String description, String shipToPartyId, String requestDeliveryDate, String fright, /*String items*/ String divCode, String divName, String totalNetPrice, String totalDiscounts, String taxableValue, String totalTax, String totalGrossPrice, String schId, String orderStatus, String createdDate, String shipToParty, String unavailableItems, String dealerName, String id, String createdBy, String isActive, String isDeleted, String IP, String callType) {
        OrderId = orderId;
        TemplateName = templateName;
        ReferenceNo = referenceNo;
        Description = description;
        ShipToPartyId = shipToPartyId;
        RequestDeliveryDate = requestDeliveryDate;
        Fright = fright;
        //Items[] = items;
        DivCode = divCode;
        DivName = divName;
        TotalNetPrice = totalNetPrice;
        TotalDiscounts = totalDiscounts;
        TaxableValue = taxableValue;
        TotalTax = totalTax;
        TotalGrossPrice = totalGrossPrice;
        SchId = schId;
        OrderStatus = orderStatus;
        CreatedDate = createdDate;
        ShipToParty = shipToParty;
        UnavailableItems = unavailableItems;
        DealerName = dealerName;
        Id = id;
        CreatedBy = createdBy;
        IsActive = isActive;
        IsDeleted = isDeleted;
        this.IP = IP;
        CallType = callType;
    }


    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public String getShipToPartyId() {
        return ShipToPartyId;
    }

    public void setShipToPartyId(String shipToPartyId) {
        ShipToPartyId = shipToPartyId;
    }

    public String getOrderStatus() {
        return OrderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        OrderStatus = orderStatus;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }

    public String getRequestDeliveryDate() {
        return RequestDeliveryDate;
    }

    public void setRequestDeliveryDate(String requestDeliveryDate) {
        RequestDeliveryDate = requestDeliveryDate;
    }

    public String getShipToParty() {
        return ShipToParty;
    }

    public void setShipToParty(String shipToParty) {
        ShipToParty = shipToParty;
    }




}
