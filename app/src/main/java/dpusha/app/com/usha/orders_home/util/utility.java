package dpusha.app.com.usha.orders_home.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dpusha.app.com.usha.model.AuthToken;
import dpusha.app.com.usha.model.CartItem;
import dpusha.app.com.usha.model.Item;
import dpusha.app.com.usha.model.ProductDescription;
import dpusha.app.com.usha.model.ProductSKU;
import dpusha.app.com.usha.model.draft.GetDraft;
import dpusha.app.com.usha.shared_preference.SharedPreferencesUtil;

public class utility {


//date covert

    public static String covertDateAndTimeFormat(String time) {
        String inputPattern = "yyyy-MM-dd";
        String outputPattern = "dd-MM-yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }


    // date greater than check method.

    public static boolean isToDateafterFromDate(String fromDate, String toDate, String format) {

        boolean isDate = false;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            Date _fromDate = sdf.parse(fromDate);
            Date _toDate = sdf.parse(toDate);


            if (_toDate.after(_fromDate)) {
                isDate = true;
            }


        } catch (Exception e) {
            e.getMessage();
        }
        return isDate;
    }


    // get date range between data

    public static boolean getFronDate_toDate_data(String fromDate, String toDate,String actualDate, String format) {

        boolean isDate = false;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            Date _fromDate = sdf.parse(fromDate);
            Date _toDate = sdf.parse(toDate);
            Date _actualDate=sdf.parse(actualDate);


            if (_actualDate.after(_fromDate) && _actualDate.before(_toDate)) {
                isDate = true;
            }


        } catch (Exception e) {
            e.getMessage();
        }
        return isDate;
    }

    public static String convertCartToJSONString(CartItem cartItem){
        String json="";
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            json = ow.writeValueAsString(cartItem);
        }catch (Exception e){

        }
        return json;
    }



    public static CartItem convertJSONStringToCartObject(String jsonCart){
        CartItem cartItem=null;
        try {
          //   cartItem = new Gson().fromJson(jsonCart, CartItem.class);

            ObjectMapper mapper = new ObjectMapper();
            cartItem = mapper.readValue(jsonCart, CartItem.class);

        }catch (Exception e){
            e.getMessage();
        }
        return cartItem;
    }

    public static String sampeResponseGetPrice(){
        return "{\n" +
                "    \"IsSuccess\": true,\n" +
                "    \"Result\": [\n" +
                "        {\n" +
                "            \"CustomerCode\": \"0000010151\",\n" +
                "            \"Division\": \"\",\n" +
                "            \"MaterialCode\": \"\",\n" +
                "            \"MaterialDescription\": \"INVALID MATERIAL\",\n" +
                "            \"PriceBeforeDiscount\": 0.0,\n" +
                "            \"BillDiscount\": 0.0,\n" +
                "            \"TAX\": 0\n" +
                "        },\n" +
                "        {\n" +
                "            \"CustomerCode\": \"0000010151\",\n" +
                "            \"Division\": \"\",\n" +
                "            \"MaterialCode\": \"111031513WZ\",\n" +
                "            \"MaterialDescription\": \"INVALID MATERIAL\",\n" +
                "            \"PriceBeforeDiscount\": 0.0,\n" +
                "            \"BillDiscount\": 0.0,\n" +
                "            \"TAX\": 0\n" +
                "        },\n" +
                "        {\n" +
                "            \"CustomerCode\": \"0000010151\",\n" +
                "            \"Division\": \"10\",\n" +
                "            \"MaterialCode\": \"11104GS10W\",\n" +
                "            \"MaterialDescription\": \"1200MM STRIKER W/O REG BR CF\",\n" +
                "            \"PriceBeforeDiscount\": 1696.0,\n" +
                "            \"BillDiscount\": 173.92,\n" +
                "            \"TAX\": 12\n" +
                "        },\n" +
                "        {\n" +
                "            \"CustomerCode\": \"0000010151\",\n" +
                "            \"Division\": \"10\",\n" +
                "            \"MaterialCode\": \"11104GS11W\",\n" +
                "            \"MaterialDescription\": \"1200MM STRIKER W/O REG WH CF\",\n" +
                "            \"PriceBeforeDiscount\": 1559.0,\n" +
                "            \"BillDiscount\": 171.18,\n" +
                "            \"TAX\": 12\n" +
                "        }\n" +
                "    ],\n" +
                "    \"Message\": \"Success\"\n" +
                "}\n";
    }
    public static  String getCurrentTimestamp(){
       return String.valueOf(System.currentTimeMillis());
    }
   public static void showToast(Context context,String msg){
       Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
   }

   public static CartItem getCartFromPreference(Context context){
       String strCartItem = SharedPreferencesUtil.getCartItems(context);
       Log.e("Log_Cart",strCartItem);
        if(strCartItem.equals(""))
            return null;
       CartItem cartItem= utility.convertJSONStringToCartObject(strCartItem);
       if(cartItem==null) return null;
       if(cartItem.getItems()==null) return null;
       if (cartItem.getItems().size()<1) return null;
       return cartItem;
   }

   public static boolean isCartEmpty(Context context){
       boolean empty=false;
        String strCartItem = SharedPreferencesUtil.getCartItems(context);
        if(strCartItem.equals("")) return true;
       CartItem cartObject = utility.convertJSONStringToCartObject(strCartItem);
       if (cartObject == null && cartObject.getItems()!=null && cartObject.getItems().size()>0) {
           empty=true;
       }
       return empty;
   }


    //start

    public static boolean Check_validity(String fromDate, String toDate, String format) {

        boolean isDate = false;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            Date _fromDate = sdf.parse(fromDate);
            Date _toDate = sdf.parse(toDate);



            if (_toDate.after(_fromDate) || _toDate.equals(_fromDate)) {
                isDate = true;
            }


        } catch (Exception e) {
            e.getMessage();
        }
        return isDate;
    }

    //end
    public static ProductDescription convertSKU_To_Description(ProductSKU productSKU){

       return  new ProductDescription(productSKU.getOrderId(),
                productSKU.getsKU(),
                productSKU.getDescription(),
                productSKU.getuOM(),
                productSKU.getUnitPrice(),
                productSKU.getDiscount(),
                productSKU.getTaxPercent(),
                productSKU.getAvailableInStock(),
                productSKU.getQuantity(),
                productSKU.getApprovedQuantity(),
                productSKU.getImageName(),
                productSKU.getDivCode(),
                productSKU.getShipToPartyId(),
                productSKU.getPreFix(),
                productSKU.getTotalDiscountPerSKU(),
                productSKU.getTotalPricePerSKU(),
                productSKU.getTotalAfterDiscountPerSKU(),
                productSKU.getTotalTaxPerSKU(),
                productSKU.getTotalPriceWithTaxPerSKU(),
                productSKU.getDeliveryStatus(),
                productSKU.getDeliveryStatusString(),
                productSKU.getUserId(),
                productSKU.getStatus(),
                productSKU.getId(),
                productSKU.getCreatedBy(),
                productSKU.getActive(),
                productSKU.getDeleted(),
                productSKU.getiP(),
                productSKU.getCallType(),
                productSKU.getResultCode());
    }

    public static void saveDraftToPreference(GetDraft draft,Context context) {

        if (draft != null && draft.getItems() != null && draft.getItems().size() > 0) {


            List<dpusha.app.com.usha.model.draft.Item> draftItemsFromAPI=draft.getItems();
            List<Item> items=new ArrayList<>();
            for(dpusha.app.com.usha.model.draft.Item item:draftItemsFromAPI){
                items.add(new Item(item.getsKU(),item.getDescription(),item.getuOM(),
                        item.getUnitPrice(),item.getDiscount(),item.getTaxPercent(),
                        item.getAvailableInStock(),item.getQuantity(),item.getApprovedQuantity(),item.getImageName(),
                        item.getDivCode(),
                       null,null));
            }


            CartItem cartItem=new CartItem(draft.getOrderId(),draft.getTemplateName()
                    ,draft.getReferenceNo(), draft.getDescription(),null,
                    draft.getRequestDeliveryDate(),items,draft.getDivCode(),
                    draft.getTotalNetPrice(),draft.getTotalDiscounts(),draft.getTaxableValue(),draft.getTotalTax(),
                    draft.getTotalGrossPrice(),draft.getSchId(),null);


            cartItem.setOrderId(utility.getCurrentTimestamp());
            cartItem.setOrderStatus("Created");

            String newCartJson = utility.convertCartToJSONString(cartItem);
            SharedPreferencesUtil.setCartItems(context, newCartJson);


        }



    }

}
