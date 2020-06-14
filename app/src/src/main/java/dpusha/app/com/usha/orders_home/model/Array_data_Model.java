package dpusha.app.com.usha.orders_home.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Array_data_Model {


    public Array_data_Model(String SKU, String description) {
        this.SKU = SKU;
        Description = description;
    }

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    @SerializedName("SKU")
    @Expose
    String  SKU;

    @SerializedName("Description")
    @Expose
    String  Description;

    public Array_data_Model(String quantity) {
        Quantity = quantity;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    @SerializedName("Quantity")
    @Expose
    String  Quantity;


}
