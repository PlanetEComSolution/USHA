
package dpusha.app.com.usha.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductDivision {

    @SerializedName("CatCode")
    @Expose
    private Object catCode;
    @SerializedName("CatName")
    @Expose
    private Object catName;
    @SerializedName("ImageName")
    @Expose
    private Object imageName;
    @SerializedName("CallType")
    @Expose
    private Object callType;
    @SerializedName("UserId")
    @Expose
    private Object userId;
    @SerializedName("DivCode")
    @Expose
    private String divCode;
    @SerializedName("DivName")
    @Expose
    private String divName;
    @SerializedName("CatType")
    @Expose
    private Object catType;
    @SerializedName("Category")
    @Expose
    private Object category;
    @SerializedName("SubCategory")
    @Expose
    private Object subCategory;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ProductDivision() {
    }

    /**
     * 
     * @param catCode
     * @param subCategory
     * @param imageName
     * @param divName
     * @param divCode
     * @param catName
     * @param catType
     * @param category
     * @param userId
     * @param callType
     */
    public ProductDivision(Object catCode, Object catName, Object imageName, Object callType, Object userId, String divCode, String divName, Object catType, Object category, Object subCategory) {
        super();
        this.catCode = catCode;
        this.catName = catName;
        this.imageName = imageName;
        this.callType = callType;
        this.userId = userId;
        this.divCode = divCode;
        this.divName = divName;
        this.catType = catType;
        this.category = category;
        this.subCategory = subCategory;
    }

    public Object getCatCode() {
        return catCode;
    }

    public void setCatCode(Object catCode) {
        this.catCode = catCode;
    }

    public Object getCatName() {
        return catName;
    }

    public void setCatName(Object catName) {
        this.catName = catName;
    }

    public Object getImageName() {
        return imageName;
    }

    public void setImageName(Object imageName) {
        this.imageName = imageName;
    }

    public Object getCallType() {
        return callType;
    }

    public void setCallType(Object callType) {
        this.callType = callType;
    }

    public Object getUserId() {
        return userId;
    }

    public void setUserId(Object userId) {
        this.userId = userId;
    }

    public String getDivCode() {
        return divCode;
    }

    public void setDivCode(String divCode) {
        this.divCode = divCode;
    }

    public String getDivName() {
        return divName;
    }

    public void setDivName(String divName) {
        this.divName = divName;
    }

    public Object getCatType() {
        return catType;
    }

    public void setCatType(Object catType) {
        this.catType = catType;
    }

    public Object getCategory() {
        return category;
    }

    public void setCategory(Object category) {
        this.category = category;
    }

    public Object getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(Object subCategory) {
        this.subCategory = subCategory;
    }

    @Override
    public String toString() {
        return  divName ;
    }

    public ProductDivision(String divCode, String divName) {
        this.divCode = divCode;
        this.divName = divName;
    }
}
