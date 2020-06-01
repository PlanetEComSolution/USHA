
package dpusha.app.com.usha.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

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
    private String imageName;
    @SerializedName("DivCode")
    @Expose
    private Object divCode;
    @SerializedName("Status")
    @Expose
    private String status;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Item() {
    }

    /**
     * 
     * @param unitPrice
     * @param uOM
     * @param quantity
     * @param imageName
     * @param divCode
     * @param availableInStock
     * @param description
     * @param discount
     * @param taxPercent
     * @param sKU
     * @param approvedQuantity
     * @param status
     */
    public Item(String sKU, String description, String uOM, Double unitPrice, Double discount, Double taxPercent, Boolean availableInStock, Integer quantity, Integer approvedQuantity, String imageName, Object divCode, String status) {
        super();
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
        this.status = status;
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

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Object getDivCode() {
        return divCode;
    }

    public void setDivCode(Object divCode) {
        this.divCode = divCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
