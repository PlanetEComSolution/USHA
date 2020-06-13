package dpusha.app.com.usha;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CRG_ForgetPasswordApi_Response {

    @SerializedName("errormessage")
    @Expose
    private String errormessage;
    @SerializedName("Mail Sent Successfully")
    @Expose
    private String status;
    @SerializedName("UserId")
    @Expose
    private Object uderid;

    /**
     * No args constructor for use in serialization
     *
     */
    public CRG_ForgetPasswordApi_Response() {
    }

    /**
     *
     * @param uderid
     * @param status
     */
    public CRG_ForgetPasswordApi_Response( String status, Object uderid) {
        super();

        this.uderid = uderid;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getEmail() {
        return uderid;
    }

    public void sentUerId(Object email) {
        this.uderid = uderid;
    }

}
