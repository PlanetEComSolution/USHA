
package dpusha.app.com.usha.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllClass {

    @SerializedName("CategoryCode")
    @Expose
    private String categoryCode;
    @SerializedName("CategoryName")
    @Expose
    private String categoryName;
    @SerializedName("ParentId")
    @Expose
    private Integer parentId;
    @SerializedName("Sequence")
    @Expose
    private Integer sequence;
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("CreatedBy")
    @Expose
    private String createdBy;
    @SerializedName("IsActive")
    @Expose
    private Boolean isActive;
    @SerializedName("IsDeleted")
    @Expose
    private Boolean isDeleted;
    @SerializedName("IP")
    @Expose
    private String iP;
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
    public AllClass() {
    }

    /**
     *
     * @param sequence
     * @param isDeleted
     * @param createdBy
     * @param iP
     * @param resultCode
     * @param categoryCode
     * @param id
     * @param isActive
     * @param categoryName
     * @param parentId
     * @param callType
     */
    public AllClass(String categoryCode,
                    String categoryName,
                    Integer parentId,
                    Integer sequence,
                    Integer id,
                    String createdBy,
                    Boolean isActive,
                    Boolean isDeleted,
                    String iP,
                    Object callType,
                    Object resultCode
    ) {
        super();
        this.categoryCode = categoryCode;
        this.categoryName = categoryName;
        this.parentId = parentId;
        this.sequence = sequence;
        this.id = id;
        this.createdBy = createdBy;
        this.isActive = isActive;
        this.isDeleted = isDeleted;
        this.iP = iP;
        this.callType = callType;
        this.resultCode = resultCode;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Object getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
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

    public String getIP() {
        return iP;
    }

    public void setIP(String iP) {
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

    @Override
    public String toString() {
        return categoryName ;
    }

    public AllClass(String categoryCode, String categoryName) {
        this.categoryCode = categoryCode;
        this.categoryName = categoryName;
    }
}
