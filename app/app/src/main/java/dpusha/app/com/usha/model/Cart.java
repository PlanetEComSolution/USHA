
package dpusha.app.com.usha.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cart {

    @SerializedName("SKU")
    @Expose
    private String sKU;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("ImageName")
    @Expose
    private String imageName;
    @SerializedName("Quantity")
    @Expose
    private Integer quantity;
    @SerializedName("IsActive")
    @Expose
    private Boolean isActive;
    @SerializedName("PageIndex")
    @Expose
    private Integer pageIndex;
    @SerializedName("TotalRecord")
    @Expose
    private Integer totalRecord;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Cart() {
    }

    /**
     * 
     * @param imageName
     * @param quantity
     * @param pageIndex
     * @param description
     * @param sKU
     * @param isActive
     * @param totalRecord
     */
    public Cart(String sKU, String description, String imageName, Integer quantity, Boolean isActive, Integer pageIndex, Integer totalRecord) {
        super();
        this.sKU = sKU;
        this.description = description;
        this.imageName = imageName;
        this.quantity = quantity;
        this.isActive = isActive;
        this.pageIndex = pageIndex;
        this.totalRecord = totalRecord;
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

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(Integer totalRecord) {
        this.totalRecord = totalRecord;
    }

}
