
package dpusha.app.com.usha.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("CustomerCode")
    @Expose
    private String customerCode;
    @SerializedName("Division")
    @Expose
    private String division;
    @SerializedName("MaterialCode")
    @Expose
    private String materialCode;
    @SerializedName("MaterialDescription")
    @Expose
    private String materialDescription;
    @SerializedName("PriceBeforeDiscount")
    @Expose
    private Double priceBeforeDiscount;
    @SerializedName("BillDiscount")
    @Expose
    private Double billDiscount;
    @SerializedName("TAX")
    @Expose
    private Double tAX;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Result() {
    }

    /**
     * 
     * @param division
     * @param billDiscount
     * @param priceBeforeDiscount
     * @param customerCode
     * @param materialCode
     * @param tAX
     * @param materialDescription
     */
  /*  public Result(String customerCode, String division, String materialCode, String materialDescription, Double priceBeforeDiscount, Double billDiscount, Integer tAX) {
        super();
        this.customerCode = customerCode;
        this.division = division;
        this.materialCode = materialCode;
        this.materialDescription = materialDescription;
        this.priceBeforeDiscount = priceBeforeDiscount;
        this.billDiscount = billDiscount;
        this.tAX = tAX;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getMaterialDescription() {
        return materialDescription;
    }

    public void setMaterialDescription(String materialDescription) {
        this.materialDescription = materialDescription;
    }

    public Double getPriceBeforeDiscount() {
        return priceBeforeDiscount;
    }

    public void setPriceBeforeDiscount(Double priceBeforeDiscount) {
        this.priceBeforeDiscount = priceBeforeDiscount;
    }

    public Double getBillDiscount() {
        return billDiscount;
    }

    public void setBillDiscount(Double billDiscount) {
        this.billDiscount = billDiscount;
    }

    public D getTAX() {
        return tAX;
    }

    public void setTAX(Integer tAX) {
        this.tAX = tAX;
    }*/

    public Result(String customerCode, String division, String materialCode, String materialDescription, Double priceBeforeDiscount, Double billDiscount, Double tAX) {
        this.customerCode = customerCode;
        this.division = division;
        this.materialCode = materialCode;
        this.materialDescription = materialDescription;
        this.priceBeforeDiscount = priceBeforeDiscount;
        this.billDiscount = billDiscount;
        this.tAX = tAX;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getMaterialDescription() {
        return materialDescription;
    }

    public void setMaterialDescription(String materialDescription) {
        this.materialDescription = materialDescription;
    }

    public Double getPriceBeforeDiscount() {
        return priceBeforeDiscount;
    }

    public void setPriceBeforeDiscount(Double priceBeforeDiscount) {
        this.priceBeforeDiscount = priceBeforeDiscount;
    }

    public Double getBillDiscount() {
        return billDiscount;
    }

    public void setBillDiscount(Double billDiscount) {
        this.billDiscount = billDiscount;
    }

    public Double gettAX() {
        return tAX;
    }

    public void settAX(Double tAX) {
        this.tAX = tAX;
    }
}
