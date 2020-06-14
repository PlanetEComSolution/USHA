package dpusha.app.com.usha.Apiclint;

public class Changepasswordclint {


    private String CategoryCode;
    private String UserType;
    private String CategoryName;
    private String IsActive;
    private String IP;
    private int ParentId;
    private int Sequence;
    private Object CreatedBy;

    private boolean IsDeleted;
  /*  private int IP;*/
    private Object CallType;

    public String getCategoryCode() {
        return CategoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        CategoryCode = categoryCode;
    }

    public String getUserType() {
        return UserType;
    }

    public void setUserType(String userType) {
        UserType = userType;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public String getIsActive() {
        return IsActive;
    }

    public void setIsActive(String isActive) {
        IsActive = isActive;
    }

  /*  public String getIP() {
        return IP;
    }

    public void setIP(int IP) {
        this.IP = IP;
    }*/

    public Object getCallType() {
        return CallType;
    }

    public void setCallType(Object callType) {
        CallType = callType;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public int getParentId() {
        return ParentId;
    }

    public void setParentId(int parentId) {
        ParentId = parentId;
    }

    public int getSequence() {
        return Sequence;
    }

    public void setSequence(int sequence) {
        Sequence = sequence;
    }

    public Object getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(Object createdBy) {
        CreatedBy = createdBy;
    }

    public boolean isDeleted() {
        return IsDeleted;
    }

    public void setDeleted(boolean deleted) {
        IsDeleted = deleted;
    }
}
