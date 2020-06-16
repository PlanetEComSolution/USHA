package dpusha.app.com.usha.model;

public class DivisionResponse {
    private String DivCode;
    private String DivName;
    private String SchemeName;
    private String FileName;
    private String File;
    private String Id;
    private String CreatedBy;
    private String IsActive;
    private String IsDeleted;
    private String IP;
    private String CallType;
    private String ResultCode;

    public String getDivCode() {
        return DivCode;
    }

    public void setDivCode(String divCode) {
        DivCode = divCode;
    }

    public String getDivName() {
        return DivName;
    }

    public void setDivName(String divName) {
        DivName = divName;
    }

    public String getSchemeName() {
        return SchemeName;
    }

    public void setSchemeName(String schemeName) {
        SchemeName = schemeName;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    public String getFile() {
        return File;
    }

    public void setFile(String file) {
        File = file;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
    }

    public String getIsActive() {
        return IsActive;
    }

    public void setIsActive(String isActive) {
        IsActive = isActive;
    }

    public String getIsDeleted() {
        return IsDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        IsDeleted = isDeleted;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getCallType() {
        return CallType;
    }

    public void setCallType(String callType) {
        CallType = callType;
    }

    public String getResultCode() {
        return ResultCode;
    }

    public void setResultCode(String resultCode) {
        ResultCode = resultCode;
    }
}
