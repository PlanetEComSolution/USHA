
package dpusha.app.com.usha.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Feedback {

    @SerializedName("TicketCode")
    @Expose
    private String ticketCode;
    @SerializedName("UserId")
    @Expose
    private String userId;
    @SerializedName("DepartmentCode")
    @Expose
    private String departmentCode;
    @SerializedName("CategoryCode")
    @Expose
    private String categoryCode;
    @SerializedName("LocationCode")
    @Expose
    private String locationCode;
    @SerializedName("ClassCode")
    @Expose
    private String classCode;
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("Remarks")
    @Expose
    private String remarks;
    @SerializedName("Images")
    @Expose
    private Object images;
    @SerializedName("PartnerCode")
    @Expose
    private Object partnerCode;
    @SerializedName("PendingWithUserCode")
    @Expose
    private String pendingWithUserCode;
    @SerializedName("DepartmentName")
    @Expose
    private String departmentName;
    @SerializedName("CategoryName")
    @Expose
    private String categoryName;
    @SerializedName("LocationName")
    @Expose
    private String locationName;
    @SerializedName("ImageNames")
    @Expose
    private Object imageNames;
    @SerializedName("UserType")
    @Expose
    private Object userType;
    @SerializedName("CurrentFeedbackStatus")
    @Expose
    private String currentFeedbackStatus;
    @SerializedName("EstimatedDate")
    @Expose
    private String estimatedDate;
    @SerializedName("PendingEmployeeName")
    @Expose
    private Object pendingEmployeeName;
    @SerializedName("DealerMailId")
    @Expose
    private Object dealerMailId;
    @SerializedName("SpocPersonMailId")
    @Expose
    private Object spocPersonMailId;
    @SerializedName("ReportingHeadMailId")
    @Expose
    private Object reportingHeadMailId;
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
    public Feedback() {
    }

    /**
     * 
     * @param partnerCode
     * @param departmentCode
     * @param iP
     * @param estimatedDate
     * @param pendingEmployeeName
     * @param resultCode
     * @param title
     * @param isActive
     * @param categoryName
     * @param callType
     * @param currentFeedbackStatus
     * @param isDeleted
     * @param id
     * @param ticketCode
     * @param reportingHeadMailId
     * @param departmentName
     * @param classCode
     * @param images
     * @param locationName
     * @param imageNames
     * @param categoryCode
     * @param userId
     * @param pendingWithUserCode
     * @param spocPersonMailId
     * @param createdBy
     * @param dealerMailId
     * @param userType
     * @param locationCode
     * @param remarks
     */
    public Feedback(String ticketCode, String userId, String departmentCode, String categoryCode, String locationCode, String classCode, String title, String remarks, Object images, Object partnerCode, String pendingWithUserCode, String departmentName, String categoryName, String locationName, Object imageNames, Object userType, String currentFeedbackStatus, String estimatedDate, Object pendingEmployeeName, Object dealerMailId, Object spocPersonMailId, Object reportingHeadMailId, Integer id, String createdBy, Boolean isActive, Boolean isDeleted, String iP, Object callType, Object resultCode) {
        super();
        this.ticketCode = ticketCode;
        this.userId = userId;
        this.departmentCode = departmentCode;
        this.categoryCode = categoryCode;
        this.locationCode = locationCode;
        this.classCode = classCode;
        this.title = title;
        this.remarks = remarks;
        this.images = images;
        this.partnerCode = partnerCode;
        this.pendingWithUserCode = pendingWithUserCode;
        this.departmentName = departmentName;
        this.categoryName = categoryName;
        this.locationName = locationName;
        this.imageNames = imageNames;
        this.userType = userType;
        this.currentFeedbackStatus = currentFeedbackStatus;
        this.estimatedDate = estimatedDate;
        this.pendingEmployeeName = pendingEmployeeName;
        this.dealerMailId = dealerMailId;
        this.spocPersonMailId = spocPersonMailId;
        this.reportingHeadMailId = reportingHeadMailId;
        this.id = id;
        this.createdBy = createdBy;
        this.isActive = isActive;
        this.isDeleted = isDeleted;
        this.iP = iP;
        this.callType = callType;
        this.resultCode = resultCode;
    }

    public String getTicketCode() {
        return ticketCode;
    }

    public void setTicketCode(String ticketCode) {
        this.ticketCode = ticketCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Object getImages() {
        return images;
    }

    public void setImages(Object images) {
        this.images = images;
    }

    public Object getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(Object partnerCode) {
        this.partnerCode = partnerCode;
    }

    public String getPendingWithUserCode() {
        return pendingWithUserCode;
    }

    public void setPendingWithUserCode(String pendingWithUserCode) {
        this.pendingWithUserCode = pendingWithUserCode;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Object getImageNames() {
        return imageNames;
    }

    public void setImageNames(Object imageNames) {
        this.imageNames = imageNames;
    }

    public Object getUserType() {
        return userType;
    }

    public void setUserType(Object userType) {
        this.userType = userType;
    }

    public String getCurrentFeedbackStatus() {
        return currentFeedbackStatus;
    }

    public void setCurrentFeedbackStatus(String currentFeedbackStatus) {
        this.currentFeedbackStatus = currentFeedbackStatus;
    }

    public String getEstimatedDate() {
        return estimatedDate;
    }

    public void setEstimatedDate(String estimatedDate) {
        this.estimatedDate = estimatedDate;
    }

    public Object getPendingEmployeeName() {
        return pendingEmployeeName;
    }

    public void setPendingEmployeeName(Object pendingEmployeeName) {
        this.pendingEmployeeName = pendingEmployeeName;
    }

    public Object getDealerMailId() {
        return dealerMailId;
    }

    public void setDealerMailId(Object dealerMailId) {
        this.dealerMailId = dealerMailId;
    }

    public Object getSpocPersonMailId() {
        return spocPersonMailId;
    }

    public void setSpocPersonMailId(Object spocPersonMailId) {
        this.spocPersonMailId = spocPersonMailId;
    }

    public Object getReportingHeadMailId() {
        return reportingHeadMailId;
    }

    public void setReportingHeadMailId(Object reportingHeadMailId) {
        this.reportingHeadMailId = reportingHeadMailId;
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

}
