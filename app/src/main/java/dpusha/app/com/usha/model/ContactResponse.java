package dpusha.app.com.usha.model;

public class ContactResponse {

    private String Id;
    private String HeadOfficeAddress;
    private String RegisteredOffice;
    private String CIN;
    private String Contact1;
    private String Contact2;
    private String Email;
    private String GrievanceEmail;
    private String CustomerCareNo;
    private String CallType;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getHeadOfficeAddress() {
        return HeadOfficeAddress;
    }

    public void setHeadOfficeAddress(String headOfficeAddress) {
        HeadOfficeAddress = headOfficeAddress;
    }

    public String getRegisteredOffice() {
        return RegisteredOffice;
    }

    public void setRegisteredOffice(String registeredOffice) {
        RegisteredOffice = registeredOffice;
    }

    public String getCIN() {
        return CIN;
    }

    public void setCIN(String CIN) {
        this.CIN = CIN;
    }

    public String getContact1() {
        return Contact1;
    }

    public void setContact1(String contact1) {
        Contact1 = contact1;
    }

    public String getContact2() {
        return Contact2;
    }

    public void setContact2(String contact2) {
        Contact2 = contact2;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getGrievanceEmail() {
        return GrievanceEmail;
    }

    public void setGrievanceEmail(String grievanceEmail) {
        GrievanceEmail = grievanceEmail;
    }

    public String getCustomerCareNo() {
        return CustomerCareNo;
    }

    public void setCustomerCareNo(String customerCareNo) {
        CustomerCareNo = customerCareNo;
    }

    public String getCallType() {
        return CallType;
    }

    public void setCallType(String callType) {
        CallType = callType;
    }
}
