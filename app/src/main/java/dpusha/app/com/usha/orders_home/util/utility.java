package dpusha.app.com.usha.orders_home.util;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dpusha.app.com.usha.listeners.MainListner;
import dpusha.app.com.usha.model.AuthToken;
import dpusha.app.com.usha.model.Cart;
import dpusha.app.com.usha.model.CartItem;
import dpusha.app.com.usha.model.Item;
import dpusha.app.com.usha.model.ProductDescription;
import dpusha.app.com.usha.model.ProductSKU;
import dpusha.app.com.usha.model.Template;
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

    public static boolean getFronDate_toDate_data(String fromDate, String toDate, String actualDate, String format) {

        boolean isDate = false;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            Date _fromDate = sdf.parse(fromDate);
            Date _toDate = sdf.parse(toDate);
            Date _actualDate = sdf.parse(actualDate);


            if (_actualDate.after(_fromDate) && _actualDate.before(_toDate)) {
                isDate = true;
            }


        } catch (Exception e) {
            e.getMessage();
        }
        return isDate;
    }

    public static String convertCartToJSONString(CartItem cartItem) {
        String json = "";
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            json = ow.writeValueAsString(cartItem);
        } catch (Exception e) {

        }
        return json;
    }


    public static CartItem convertJSONStringToCartObject(String jsonCart) {
        CartItem cartItem = null;
        try {
            //   cartItem = new Gson().fromJson(jsonCart, CartItem.class);

            ObjectMapper mapper = new ObjectMapper();
            cartItem = mapper.readValue(jsonCart, CartItem.class);

        } catch (Exception e) {
            e.getMessage();
        }
        return cartItem;
    }


    public static int getCartItemCount(Context context) {
        String strCartItem = SharedPreferencesUtil.getCartItems(context);
        CartItem cartObject = utility.convertJSONStringToCartObject(strCartItem);
        if (cartObject == null || cartObject.getItems() == null || cartObject.getItems().size() == 0) {
            return 0;
        } else return cartObject.getItems().size();


    }


    public static String sampeResponseGetPrice() {
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

    public static String getCurrentTimestamp() {
        return String.valueOf(System.currentTimeMillis());
    }

    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static CartItem getCartFromPreference(Context context) {
        String strCartItem = SharedPreferencesUtil.getCartItems(context);
        Log.e("Log_Cart", strCartItem);
        if (strCartItem.equals(""))
            return null;
        CartItem cartItem = utility.convertJSONStringToCartObject(strCartItem);
        if (cartItem == null) return null;
        if (cartItem.getItems() == null) return null;
        if (cartItem.getItems().size() < 1) return null;
        return cartItem;
    }

    public static boolean isCartEmpty(Context context) {
        boolean empty = false;
        String strCartItem = SharedPreferencesUtil.getCartItems(context);
        if (strCartItem.equals("")) return true;
        CartItem cartObject = utility.convertJSONStringToCartObject(strCartItem);
        if (cartObject == null && cartObject.getItems() != null && cartObject.getItems().size() > 0) {
            empty = true;
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
    public static ProductDescription convertSKU_To_Description(ProductSKU productSKU) {

        return new ProductDescription(productSKU.getOrderId(),
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

    public static ProductDescription convertCart_To_Description(Cart items) {
        ProductDescription item = new ProductDescription();

        item.setsKU(items.getSKU());
        item.setDescription(items.getDescription());
        item.setImageName(items.getImageName());
        item.setQuantity(items.getQuantity());
        item.setActive(items.getIsActive());
        item.setuOM(items.getUOM());

        return item;
    }

    public static void addItemToCartPreference(ProductDescription item, Context context, MainListner mainListner) {
        String strCartItem = SharedPreferencesUtil.getCartItems(context);
        CartItem cartObject = utility.convertJSONStringToCartObject(strCartItem);
        if (cartObject == null) {
            cartObject = new CartItem();
        }
        cartObject.setOrderId(utility.getCurrentTimestamp());
        cartObject.setOrderStatus(null);
        cartObject.setShipToPartyId(null);
        // cartObject.setId(SharedPreferencesUtil.getUserId(getActivity()));

        String imageName = "";
        if (item.getImageName() == null || item.getImageName().equals("") || item.getImageName().equals("null")) {
            imageName = "NoImage.png";
        } else {
            imageName = String.valueOf(item.getImageName());
        }

        Item item1 = new Item(item.getsKU(),
                item.getDescription(), item.getuOM(), item.getUnitPrice(), item.getDiscount(),
                item.getTaxPercent(), true, item.getQuantity(),
                0,
                imageName, null, null,
                null);
        List<Item> list = cartObject.getItems();
        if (list == null || list.isEmpty()) {
            list = new ArrayList<>();
            list.add(item1);

        } else {

            // check item already in cart ?
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getsKU().equals(item.getsKU())) {  // item exist inn cart already

                    list.set(i, item1);
                    break;
                   /*int quantityInCart= list.get(i).getQuantity();
                    int newQuantity= item.getQuantity();
                    if(newQuantity>quantityInCart){
                        list.set(i,item1);
                    }else {
                        item1.setQuantity(quantityInCart+newQuantity);
                        list.set(i,item1);
                    }*/

                } else if (i == list.size() - 1) {   //  item does not exist in cart
                    list.add(item1);
                }
            }
        }


        cartObject.setItems(list);
        String newCartJson = utility.convertCartToJSONString(cartObject);
        // SharedPreferencesUtil.setCartItems(context, newCartJson);
        utility.setCartItems(context, newCartJson, mainListner);


    }

    public static void setCartItems(Context context, String newCartJson, MainListner mainListner) {
        SharedPreferencesUtil.setCartItems(context, newCartJson);
        mainListner.refreshCartCount(context);
    }

    public static void saveDraftToCartPreference(GetDraft draft, Context context, MainListner mainListner) {

        if (draft != null && draft.getItems() != null && draft.getItems().size() > 0) {


            List<dpusha.app.com.usha.model.draft.Item> draftItemsFromAPI = draft.getItems();
            List<Item> items = new ArrayList<>();
            for (dpusha.app.com.usha.model.draft.Item item : draftItemsFromAPI) {
                items.add(new Item(item.getsKU(), item.getDescription(), item.getuOM(),
                        item.getUnitPrice(), item.getDiscount(), item.getTaxPercent(),
                        item.getAvailableInStock(), item.getQuantity(), 0, item.getImageName(),
                        item.getDivCode(),
                        null, null));
            }


            CartItem cartItem = new CartItem(draft.getOrderId(), draft.getTemplateName()
                    , draft.getReferenceNo(), draft.getDescription(), null,
                    draft.getRequestDeliveryDate(), items, draft.getDivCode(),
                    draft.getTotalNetPrice(), draft.getTotalDiscounts(), draft.getTaxableValue(), draft.getTotalTax(),
                    draft.getTotalGrossPrice(), draft.getSchId(), null);


            cartItem.setOrderId(utility.getCurrentTimestamp());
            cartItem.setOrderStatus("Created");

            String newCartJson = utility.convertCartToJSONString(cartItem);
            utility.setCartItems(context, newCartJson, mainListner);


        }


    }


    public static void saveTemplateToCartPreference(Template template, Context context, MainListner mainListner) {

        if (template != null && template.getItems() != null && template.getItems().size() > 0) {

            List<dpusha.app.com.usha.model.draft.Item> draftItemsFromAPI = template.getItems();
            List<Item> items = new ArrayList<>();
            for (dpusha.app.com.usha.model.draft.Item item : draftItemsFromAPI) {
                items.add(new Item(item.getsKU(), item.getDescription(), item.getuOM(),
                        item.getUnitPrice(), item.getDiscount(), item.getTaxPercent(),
                        item.getAvailableInStock(), item.getQuantity(), 0, item.getImageName(),
                        item.getDivCode(),
                        null, null));
            }


            CartItem cartItem = new CartItem(template.getOrderId(), template.getTemplateName()
                    , template.getReferenceNo(), template.getDescription(), null,
                    template.getRequestDeliveryDate(), items, template.getDivCode(),
                    template.getTotalNetPrice(), template.getTotalDiscounts(), template.getTaxableValue(), template.getTotalTax(),
                    template.getTotalGrossPrice(), template.getSchId(), null);


            cartItem.setOrderId(utility.getCurrentTimestamp());
            cartItem.setOrderStatus("Created");

            String newCartJson = utility.convertCartToJSONString(cartItem);

            utility.setCartItems(context, newCartJson, mainListner);


        }


    }

    public static String chageDateFormat(String inputDateFormat, String dateStr, String outputDateFormat) {
        String formattedDate = "";
        try {
            DateFormat fromFormat = new SimpleDateFormat(inputDateFormat);
            fromFormat.setLenient(false);
            DateFormat toFormat = new SimpleDateFormat(outputDateFormat);
            toFormat.setLenient(false);
            Date date = fromFormat.parse(dateStr);
            formattedDate = toFormat.format(date);
        } catch (ParseException e) {
            e.getMessage();
        }
        return formattedDate;
    }

    public static String formatMonth(String month) {
        String mnth = "";
        try {
            SimpleDateFormat monthParse = new SimpleDateFormat("MM");
            SimpleDateFormat monthDisplay = new SimpleDateFormat("MMMM");
            mnth = monthDisplay.format(monthParse.parse(month));
        } catch (ParseException e) {
            e.getMessage();
        }
        return mnth;
    }

    public static int formatMonthNameToNumber(String month) {
        int mnth = 0;
        try {
            SimpleDateFormat monthParse = new SimpleDateFormat("MMMM");
            SimpleDateFormat monthDisplay = new SimpleDateFormat("MM");
            String str_mnth = monthDisplay.format(monthParse.parse(month));
            mnth = Integer.parseInt(str_mnth);
        } catch (ParseException e) {
            e.getMessage();
        }
        return mnth;
    }
}
