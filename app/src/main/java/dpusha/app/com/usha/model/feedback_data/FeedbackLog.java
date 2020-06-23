
package dpusha.app.com.usha.model.feedback_data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FeedbackLog {

    @SerializedName("FeedbackId")
    @Expose
    private Integer feedbackId;
    @SerializedName("FeedbackStatusId")
    @Expose
    private Integer feedbackStatusId;
    @SerializedName("FeedbackStatus")
    @Expose
    private String feedbackStatus;
    @SerializedName("ETR")
    @Expose
    private String eTR;
    @SerializedName("AssignedUserRemarks")
    @Expose
    private Object assignedUserRemarks;
    @SerializedName("PendingWithUserCode")
    @Expose
    private String pendingWithUserCode;
    @SerializedName("PendingEmployeeName")
    @Expose
    private String pendingEmployeeName;
    @SerializedName("CreatedDate")
    @Expose
    private String createdDate;
    @SerializedName("DealerMailId")
    @Expose
    private Object dealerMailId;
    @SerializedName("SpocPersonMailId")
    @Expose
    private Object spocPersonMailId;
    @SerializedName("ReportingHeadMailId")
    @Expose
    private Object reportingHeadMailId;
    @SerializedName("TicketCode")
    @Expose
    private Object ticketCode;
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
    public FeedbackLog() {
    }

    /**
     * 
     * @param eTR
     * @param assignedUserRemarks
     * @param iP
     * @param pendingEmployeeName
     * @param resultCode
     * @param feedbackId
     * @param isActive
     * @param feedbackStatusId
     * @param callType
     * @param pendingWithUserCode
     * @param spocPersonMailId
     * @param createdDate
     * @param isDeleted
     * @param createdBy
     * @param dealerMailId
     * @param id
     * @param ticketCode
     * @param feedbackStatus
     * @param reportingHeadMailId
     */
    public FeedbackLog(Integer feedbackId, Integer feedbackStatusId, String feedbackStatus, String eTR, Object assignedUserRemarks, String pendingWithUserCode, String pendingEmployeeName, String createdDate, Object dealerMailId, Object spocPersonMailId, Object reportingHeadMailId, Object ticketCode, Integer id, Object createdBy, Boolean isActive, Boolean isDeleted, Object iP, Object callType, Object resultCode) {
        super();
        this.feedbackId = feedbackId;
        this.feedbackStatusId = feedbackStatusId;
        this.feedbackStatus = feedbackStatus;
        this.eTR = eTR;
        this.assignedUserRemarks = assignedUserRemarks;
        this.pendingWithUserCode = pendingWithUserCode;
        this.pendingEmployeeName = pendingEmployeeName;
        this.createdDate = createdDate;
        this.dealerMailId = dealerMailId;
        this.spocPersonMailId = spocPersonMailId;
        this.reportingHeadMailId = reportingHeadMailId;
        this.ticketCode = ticketCode;
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

    public Integer getFeedbackStatusId() {
        return feedbackStatusId;
    }

    public void setFeedbackStatusId(Integer feedbackStatusId) {
        this.feedbackStatusId = feedbackStatusId;
    }

    public String getFeedbackStatus() {
        return feedbackStatus;
    }

    public void setFeedbackStatus(String feedbackStatus) {
        this.feedbackStatus = feedbackStatus;
    }

    public String getETR() {
        return eTR;
    }

    public void setETR(String eTR) {
        this.eTR = eTR;
    }

    public Object getAssignedUserRemarks() {
        return assignedUserRemarks;
    }

    public void setAssignedUserRemarks(Object assignedUserRemarks) {
        this.assignedUserRemarks = assignedUserRemarks;
    }

    public String getPendingWithUserCode() {
        return pendingWithUserCode;
    }

    public void setPendingWithUserCode(String pendingWithUserCode) {
        this.pendingWithUserCode = pendingWithUserCode;
    }

    public String getPendingEmployeeName() {
        return pendingEmployeeName;
    }

    public void setPendingEmployeeName(String pendingEmployeeName) {
        this.pendingEmployeeName = pendingEmployeeName;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
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

    public Object getTicketCode() {
        return ticketCode;
    }

    public void setTicketCode(Object ticketCode) {
        this.ticketCode = ticketCode;
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
