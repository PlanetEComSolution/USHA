
package dpusha.app.com.usha.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Material {

    @SerializedName("Customercode")
    @Expose
    private String customercode;
    @SerializedName("MaterialCode")
    @Expose
    private List<String> materialCode = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Material() {
    }

    /**
     * 
     * @param customercode
     * @param materialCode
     */
    public Material(String customercode, List<String> materialCode) {
        super();
        this.customercode = customercode;
        this.materialCode = materialCode;
    }

    public String getCustomercode() {
        return customercode;
    }

    public void setCustomercode(String customercode) {
        this.customercode = customercode;
    }

    public List<String> getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(List<String> materialCode) {
        this.materialCode = materialCode;
    }

}
