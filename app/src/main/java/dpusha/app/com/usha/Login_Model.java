package dpusha.app.com.usha;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Login_Model {




    @SerializedName("data")
    @Expose
    private List<Login_Model_data> data = null;







    /**
     *


     * @param data
     */
    public Login_Model(  List<Login_Model_data> data) {
        super();


        this.data = data;
    }




    public List<Login_Model_data> getData() {
        return data;
    }

    public void setData(List<Login_Model_data> data) {
        this.data = data;
    }


}
