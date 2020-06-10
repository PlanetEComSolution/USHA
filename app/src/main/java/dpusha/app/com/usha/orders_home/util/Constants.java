package dpusha.app.com.usha.orders_home.util;

/**
 * Created by Jitendra on 22,March,2019
 */
public class Constants {

      public static String BASE_URL_LIVE = "http://dpwebservicesbeta.businesstowork.com/";
   // public static String BASE_URL_LIVE = "http://dpwebservicesbeta.businesstowork.com/";

      public static enum API_TYPE {
            TOKEN("token"),
            HOME_PAGE("home_page"),
            PRODUCT_CATEGORY("PRODUCT_CATEGORY"),
            PRODUCT_DIVISION("PRODUCT_DIVISION"),
            PRODUCT_SKU("PRODUCT_SKU"),
            PRODUCT_DESCRIPTION("PRODUCT_DESCRIPTION"),
            GET_DRAFT("GET_DRAFT"),
            SAVE_DRAFT("SAVE_DRAFT"),
            SAVE_TEMPLATE("SAVE_TEMPLATE"),
            GET_PRICE("GET_PRICE"),
            SHIP_TO_PARTY("SHIP_TO_PARTY"),
            PLACE_ORDER("PLACE_ORDER"),
            ORDER_LIST("order_list"),
            ORDER_LIST_DETAILS("order_list_details"),
            LOGIN("LOGIN"),
            PASSWORD("FORGOT_PASSWORD"),
            CHANGEPASSWORD("CHANGE_PASSWORD"),
            TEMPLATE("TEMPLATE");
            private String url;

            API_TYPE(String url) {
                  this.url = url;
            }

            public String url() {
                  return url;
            }
      }

      public static final String AUTH_TOKEN = "auth_token";

}

