
package dpusha.app.com.usha.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubCategory {

    @SerializedName("CatCode")
    @Expose
    private String catCode;
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
    private Object divCode;
    @SerializedName("DivName")
    @Expose
    private Object divName;
    @SerializedName("CatType")
    @Expose
    private Object catType;
    @SerializedName("Category")
    @Expose
    private Object category;
    @SerializedName("SubCategory")
    @Expose
    private String subCategory;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SubCategory() {
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
    public SubCategory(String catCode, Object catName, Object imageName, Object callType, Object userId, Object divCode, Object divName, Object catType, Object category, String subCategory) {
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

    public void setCatCode(String catCode) {
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

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    @Override
    public String toString() {
        return  subCategory;
    }

    public SubCategory(String catCode, String subCategory) {
        this.catCode = catCode;
        this.subCategory = subCategory;
    }
}
