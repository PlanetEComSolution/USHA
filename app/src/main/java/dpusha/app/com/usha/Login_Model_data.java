package dpusha.app.com.usha;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Login_Model_data {


    @SerializedName("UserId")
    @Expose
    private Integer userId;
    @SerializedName("vr_email")
    @Expose
    private String Email;
    @SerializedName("UserType")
    @Expose
    private String userType;

    @SerializedName("BranchName")
    @Expose
    private String UserName;

    @SerializedName("Password")
    @Expose
    private String password;


    @SerializedName("UserName")
    @Expose
    private String userName;

    /**
     * No args constructor for use in serialization
     *
     */
    public Login_Model_data() {
    }

    /**
     *
     * @param userId
     * @param UserName
     * @param userType
     * * @param password
     * * @param userName
     *
     */

    public Login_Model_data(Integer userId, String UserName, String userType, String branchName, String branchsol,String userName,String password) {
        this.userId = userId;

        this.userType = userType;
        this.userName=userName;

        password = password;

    }

    public Integer getNuLoginid() {
        return userId;
    }

    public void setNuLoginid(Integer nuLoginid) {
        this.userId = nuLoginid;
    }

    public String getVrEmail() {
        return Email;
    }

    public void setVrEmail(String vrEmail) {
        this.Email = vrEmail;
    }

    public String getvRUSERNAME() {
        return userType;
    }

    public void setvRUSERNAME(String vRUSERNAME) {
        this.userType = vRUSERNAME;
    }

  /*  public String getBranchName() {
        return UserType;
    }

    public void setBranchName(String branchName) {
        UserType = branchName;
    }*/

    public String getpassword() {
        return password;
    }

    public void setpassword(String branchsol) {
        password = branchsol;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String vrEmail) {
        this.UserName = vrEmail;
    }

}
