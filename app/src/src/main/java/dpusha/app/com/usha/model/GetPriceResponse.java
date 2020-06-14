
package dpusha.app.com.usha.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetPriceResponse {

    @SerializedName("IsSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("Result")
    @Expose
    private List<Result> result = null;
    @SerializedName("Message")
    @Expose
    private String message;

    /**
     * No args constructor for use in serialization
     * 
     */
    public GetPriceResponse() {
    }

    /**
     * 
     * @param result
     * @param message
     * @param isSuccess
     */
    public GetPriceResponse(Boolean isSuccess, List<Result> result, String message) {
        super();
        this.isSuccess = isSuccess;
        this.result = result;
        this.message = message;
    }

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
