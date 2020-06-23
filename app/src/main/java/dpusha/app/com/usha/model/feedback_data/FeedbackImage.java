
package dpusha.app.com.usha.model.feedback_data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FeedbackImage {

    @SerializedName("FeedbackId")
    @Expose
    private Integer feedbackId;
    @SerializedName("ImageName")
    @Expose
    private String imageName;
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
    public FeedbackImage() {
    }

    /**
     * 
     * @param imageName
     * @param isDeleted
     * @param createdBy
     * @param iP
     * @param resultCode
     * @param feedbackId
     * @param id
     * @param isActive
     * @param callType
     */
    public FeedbackImage(Integer feedbackId, String imageName, Integer id, Object createdBy, Boolean isActive, Boolean isDeleted, Object iP, Object callType, Object resultCode) {
        super();
        this.feedbackId = feedbackId;
        this.imageName = imageName;
        this.id = id;
        this.createdBy = createdBy;
        this.isActive = isActive;
        this.isDeleted = isDeleted;
        this.iP = iP;
        this.callType = callType;
        this.resultCode = resultCode;
    }

    public Integer getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Integer feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
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
