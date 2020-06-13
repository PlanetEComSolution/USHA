package dpusha.app.com.usha.model;

public class Changpassword {
    private String UserId;

    private Object NewPassword;
    private Object OldPassword;


    public String getUserId() {
        return UserId;
    }

    public void setUserId(String UserId) {
        this.UserId = UserId;
    }


    public Object getNewPassword() {
        return NewPassword;
    }

    public void setNewPassword(Object NewPassword) {
        this.NewPassword = NewPassword;
    }

    public Object getOldPassword() {
        return OldPassword;
    }

    public void setOldPassword(Object OldPassword) {
        this.OldPassword = OldPassword;
    }


}
