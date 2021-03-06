
package dpusha.app.com.usha.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductDescription {

    @SerializedName("OrderId")
    @Expose
    private Object orderId;
    @SerializedName("SKU")
    @Expose
    private String sKU;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("UOM")
    @Expose
    private String uOM;
    @SerializedName("UnitPrice")
    @Expose
    private Double unitPrice;
    @SerializedName("Discount")
    @Expose
    private Double discount;
    @SerializedName("TaxPercent")
    @Expose
    private Double taxPercent;
    @SerializedName("AvailableInStock")
    @Expose
    private Boolean availableInStock;
    @SerializedName("Quantity")
    @Expose
    private Integer quantity;
    @SerializedName("ApprovedQuantity")
    @Expose
    private Integer approvedQuantity;
    @SerializedName("ImageName")
    @Expose
    private Object imageName;
    @SerializedName("DivCode")
    @Expose
    private Object divCode;
    @SerializedName("ShipToPartyId")
    @Expose
    private Object shipToPartyId;
    @SerializedName("PreFix")
    @Expose
    private Object preFix;
    @SerializedName("TotalDiscountPerSKU")
    @Expose
    private Double totalDiscountPerSKU;
    @SerializedName("TotalPricePerSKU")
    @Expose
    private Double totalPricePerSKU;
    @SerializedName("TotalAfterDiscountPerSKU")
    @Expose
    private Double totalAfterDiscountPerSKU;
    @SerializedName("TotalTaxPerSKU")
    @Expose
    private Double totalTaxPerSKU;
    @SerializedName("TotalPriceWithTaxPerSKU")
    @Expose
    private Double totalPriceWithTaxPerSKU;
    @SerializedName("DeliveryStatus")
    @Expose
    private Object deliveryStatus;
    @SerializedName("DeliveryStatusString")
    @Expose
    private Object deliveryStatusString;
    @SerializedName("UserId")
    @Expose
    private Object userId;
    @SerializedName("Status")
    @Expose
    private Object status;
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
    public ProductDescription() {
    }

    public ProductDescription(Object orderId, String sKU, String description, String uOM, Double unitPrice, Double discount, Double taxPercent, Boolean availableInStock, Integer quantity, Integer approvedQuantity, Object imageName, Object divCode, Object shipToPartyId, Object preFix, Double totalDiscountPerSKU, Double totalPricePerSKU, Double totalAfterDiscountPerSKU, Double totalTaxPerSKU, Double totalPriceWithTaxPerSKU, Object deliveryStatus, Object deliveryStatusString, Object userId, Object status, Integer id, Object createdBy, Boolean isActive, Boolean isDeleted, Object iP, Object callType, Object resultCode) {
        this.orderId = orderId;
        this.sKU = sKU;
        this.description = description;
        this.uOM = uOM;
        this.unitPrice = unitPrice;
        this.discount = discount;
        this.taxPercent = taxPercent;
        this.availableInStock = availableInStock;
        this.quantity = quantity;
        this.approvedQuantity = approvedQuantity;
        this.imageName = imageName;
        this.divCode = divCode;
        this.shipToPartyId = shipToPartyId;
        this.preFix = preFix;
        this.totalDiscountPerSKU = totalDiscountPerSKU;
        this.totalPricePerSKU = totalPricePerSKU;
        this.totalAfterDiscountPerSKU = totalAfterDiscountPerSKU;
        this.totalTaxPerSKU = totalTaxPerSKU;
        this.totalPriceWithTaxPerSKU = totalPriceWithTaxPerSKU;
        this.deliveryStatus = deliveryStatus;
        this.deliveryStatusString = deliveryStatusString;
        this.userId = userId;
        this.status = status;
        this.id = id;
        this.createdBy = createdBy;
        this.isActive = isActive;
        this.isDeleted = isDeleted;
        this.iP = iP;
        this.callType = callType;
        this.resultCode = resultCode;
    }

    public Object getOrderId() {
        return orderId;
    }

    public void setOrderId(Object orderId) {
        this.orderId = orderId;
    }

    public String getsKU() {
        return sKU;
    }

    public void setsKU(String sKU) {
        this.sKU = sKU;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getuOM() {
        return uOM;
    }

    public void setuOM(String uOM) {
        this.uOM = uOM;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getTaxPercent() {
        return taxPercent;
    }

    public void setTaxPercent(Double taxPercent) {
        this.taxPercent = taxPercent;
    }

    public Boolean getAvailableInStock() {
        return availableInStock;
    }

    public void setAvailableInStock(Boolean availableInStock) {
        this.availableInStock = availableInStock;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getApprovedQuantity() {
        return approvedQuantity;
    }

    public void setApprovedQuantity(Integer approvedQuantity) {
        this.approvedQuantity = approvedQuantity;
    }

    public Object getImageName() {
        return imageName;
    }

    public void setImageName(Object imageName) {
        this.imageName = imageName;
    }

    public Object getDivCode() {
        return divCode;
    }

    public void setDivCode(Object divCode) {
        this.divCode = divCode;
    }

    public Object getShipToPartyId() {
        return shipToPartyId;
    }

    public void setShipToPartyId(Object shipToPartyId) {
        this.shipToPartyId = shipToPartyId;
    }

    public Object getPreFix() {
        return preFix;
    }

    public void setPreFix(Object preFix) {
        this.preFix = preFix;
    }

    public Double getTotalDiscountPerSKU() {
        return totalDiscountPerSKU;
    }

    public void setTotalDiscountPerSKU(Double totalDiscountPerSKU) {
        this.totalDiscountPerSKU = totalDiscountPerSKU;
    }

    public Double getTotalPricePerSKU() {
        return totalPricePerSKU;
    }

    public void setTotalPricePerSKU(Double totalPricePerSKU) {
        this.totalPricePerSKU = totalPricePerSKU;
    }

    public Double getTotalAfterDiscountPerSKU() {
        return totalAfterDiscountPerSKU;
    }

    public void setTotalAfterDiscountPerSKU(Double totalAfterDiscountPerSKU) {
        this.totalAfterDiscountPerSKU = totalAfterDiscountPerSKU;
    }

    public Double getTotalTaxPerSKU() {
        return totalTaxPerSKU;
    }

    public void setTotalTaxPerSKU(Double totalTaxPerSKU) {
        this.totalTaxPerSKU = totalTaxPerSKU;
    }

    public Double getTotalPriceWithTaxPerSKU() {
        return totalPriceWithTaxPerSKU;
    }

    public void setTotalPriceWithTaxPerSKU(Double totalPriceWithTaxPerSKU) {
        this.totalPriceWithTaxPerSKU = totalPriceWithTaxPerSKU;
    }

    public Object getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(Object deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public Object getDeliveryStatusString() {
        return deliveryStatusString;
    }

    public void setDeliveryStatusString(Object deliveryStatusString) {
        this.deliveryStatusString = deliveryStatusString;
    }

    public Object getUserId() {
        return userId;
    }

    public void setUserId(Object userId) {
        this.userId = userId;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
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


  /*  public ProductDescription(Object orderId, String sKU, String description, String uOM, Integer unitPrice, Integer discount, Integer taxPercent, Boolean availableInStock, Integer quantity, Integer approvedQuantity, Object imageName, Object divCode, Object shipToPartyId, Object preFix, Integer totalDiscountPerSKU, Integer totalPricePerSKU, Integer totalAfterDiscountPerSKU, Integer totalTaxPerSKU, Integer totalPriceWithTaxPerSKU, Object deliveryStatus, Object deliveryStatusString, Object userId, Object status, Integer id, Object createdBy, Boolean isActive, Boolean isDeleted, Object iP, Object callType, Object resultCode) {
        super();
        this.orderId = orderId;
        this.sKU = sKU;
        this.description = description;
        this.uOM = uOM;
        this.unitPrice = unitPrice;
        this.discount = discount;
        this.taxPercent = taxPercent;
        this.availableInStock = availableInStock;
        this.quantity = quantity;
        this.approvedQuantity = approvedQuantity;
        this.imageName = imageName;
        this.divCode = divCode;
        this.shipToPartyId = shipToPartyId;
        this.preFix = preFix;
        this.totalDiscountPerSKU = totalDiscountPerSKU;
        this.totalPricePerSKU = totalPricePerSKU;
        this.totalAfterDiscountPerSKU = totalAfterDiscountPerSKU;
        this.totalTaxPerSKU = totalTaxPerSKU;
        this.totalPriceWithTaxPerSKU = totalPriceWithTaxPerSKU;
        this.deliveryStatus = deliveryStatus;
        this.deliveryStatusString = deliveryStatusString;
        this.userId = userId;
        this.status = status;
        this.id = id;
        this.createdBy = createdBy;
        this.isActive = isActive;
        this.isDeleted = isDeleted;
        this.iP = iP;
        this.callType = callType;
        this.resultCode = resultCode;
    }

    public Object getOrderId() {
        return orderId;
    }

    public void setOrderId(Object orderId) {
        this.orderId = orderId;
    }

    public String getSKU() {
        return sKU;
    }

    public void setSKU(String sKU) {
        this.sKU = sKU;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUOM() {
        return uOM;
    }

    public void setUOM(String uOM) {
        this.uOM = uOM;
    }

    public Integer getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getTaxPercent() {
        return taxPercent;
    }

    public void setTaxPercent(Integer taxPercent) {
        this.taxPercent = taxPercent;
    }

    public Boolean getAvailableInStock() {
        return availableInStock;
    }

    public void setAvailableInStock(Boolean availableInStock) {
        this.availableInStock = availableInStock;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getApprovedQuantity() {
        return approvedQuantity;
    }

    public void setApprovedQuantity(Integer approvedQuantity) {
        this.approvedQuantity = approvedQuantity;
    }

    public Object getImageName() {
        return imageName;
    }

    public void setImageName(Object imageName) {
        this.imageName = imageName;
    }

    public Object getDivCode() {
        return divCode;
    }

    public void setDivCode(Object divCode) {
        this.divCode = divCode;
    }

    public Object getShipToPartyId() {
        return shipToPartyId;
    }

    public void setShipToPartyId(Object shipToPartyId) {
        this.shipToPartyId = shipToPartyId;
    }

    public Object getPreFix() {
        return preFix;
    }

    public void setPreFix(Object preFix) {
        this.preFix = preFix;
    }

    public Integer getTotalDiscountPerSKU() {
        return totalDiscountPerSKU;
    }

    public void setTotalDiscountPerSKU(Integer totalDiscountPerSKU) {
        this.totalDiscountPerSKU = totalDiscountPerSKU;
    }

    public Integer getTotalPricePerSKU() {
        return totalPricePerSKU;
    }

    public void setTotalPricePerSKU(Integer totalPricePerSKU) {
        this.totalPricePerSKU = totalPricePerSKU;
    }

    public Integer getTotalAfterDiscountPerSKU() {
        return totalAfterDiscountPerSKU;
    }

    public void setTotalAfterDiscountPerSKU(Integer totalAfterDiscountPerSKU) {
        this.totalAfterDiscountPerSKU = totalAfterDiscountPerSKU;
    }

    public Integer getTotalTaxPerSKU() {
        return totalTaxPerSKU;
    }

    public void setTotalTaxPerSKU(Integer totalTaxPerSKU) {
        this.totalTaxPerSKU = totalTaxPerSKU;
    }

    public Integer getTotalPriceWithTaxPerSKU() {
        return totalPriceWithTaxPerSKU;
    }

    public void setTotalPriceWithTaxPerSKU(Integer totalPriceWithTaxPerSKU) {
        this.totalPriceWithTaxPerSKU = totalPriceWithTaxPerSKU;
    }

    public Object getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(Object deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public Object getDeliveryStatusString() {
        return deliveryStatusString;
    }

    public void setDeliveryStatusString(Object deliveryStatusString) {
        this.deliveryStatusString = deliveryStatusString;
    }

    public Object getUserId() {
        return userId;
    }

    public void setUserId(Object userId) {
        this.userId = userId;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
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
*/




    @Override
    public String toString() {
        return description;
    }
}
