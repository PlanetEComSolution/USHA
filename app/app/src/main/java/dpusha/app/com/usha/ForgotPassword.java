package dpusha.app.com.usha;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForgotPassword {

    private String UserId;
    @SerializedName("Mail Sent Successfully")
    @Expose
    private String errormessage;

    public ForgotPassword() {
    }

    /**
     *
     * @param errormessage
     * @param status
     */
    public ForgotPassword(String errormessage, String status) {
        super();
        this.errormessage = errormessage;

    }

    public String getErrormessage() {
        return errormessage;
    }

    public void setErrormessage(String errormessage) {
        this.errormessage = errormessage;
    }



}
