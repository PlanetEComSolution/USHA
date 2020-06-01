package dpusha.app.com.usha.orders_home.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderListDetails_Model {

    @SerializedName("OrderId")
    @Expose
    private String OrderId;

    @SerializedName("RequestDeliveryDate")
    @Expose
    private String RequestDeliveryDate;

    public OrderListDetails_Model(List<Array_data_Model> items) {
        Items = items;
    }

    public List<Array_data_Model> getItems() {
        return Items;
    }

    public void setItems(List<Array_data_Model> items) {
        Items = items;
    }

    @SerializedName("Items")
    @Expose
    private List<Array_data_Model> Items;





    public OrderListDetails_Model(String orderId, String requestDeliveryDate) {
        OrderId = orderId;
        RequestDeliveryDate = requestDeliveryDate;
    }

    public String getRequestDeliveryDate() {
        return RequestDeliveryDate;
    }

    public void setRequestDeliveryDate(String requestDeliveryDate) {
        RequestDeliveryDate = requestDeliveryDate;
    }








    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }





}
