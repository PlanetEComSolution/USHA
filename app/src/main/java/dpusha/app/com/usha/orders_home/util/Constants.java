package dpusha.app.com.usha.orders_home.util;

/**
 * Created by Jitendra on 22,March,2019
 */
public class Constants {

      public static String BASE_URL_LIVE = "http://dpwebservicesbeta.businesstowork.com/";
    public static String IMAGE_URL_LIVE = "http://beta.usha.businesstowork.com/";
   // public static String BASE_URL_LIVE = "http://dpwebservicesbeta.businesstowork.com/";

      public static enum API_TYPE {
            TOKEN("token"),
            HOME_PAGE("home_page"),
            PRODUCT_CATEGORY("PRODUCT_CATEGORY"),
            PRODUCT_DIVISION("PRODUCT_DIVISION"),
          DIVISION("DIVISION"),
            PRODUCT_SKU("PRODUCT_SKU"),
            PRODUCT_DESCRIPTION("PRODUCT_DESCRIPTION"),
            GET_DRAFT("GET_DRAFT"),
            SAVE_DRAFT("SAVE_DRAFT"),
            SAVE_TEMPLATE("SAVE_TEMPLATE"),
            GET_PRICE("GET_PRICE"),
          GET_PRODUCT_DETAILS("GET_PRODUCT_DETAILS"),
            SHIP_TO_PARTY("SHIP_TO_PARTY"),
            PLACE_ORDER("PLACE_ORDER"),
            ORDER_LIST("order_list"),
            ORDER_LIST_DETAILS("order_list_details"),
            LOGIN("LOGIN"),

            PASSWORD("FORGOT_PASSWORD"),
            CHANGEPASSWORD("CHANGE_PASSWORD"),

          TEMPLATE_DETAILS("TEMPLATE_DETAILS"),
          DELETE_TEMPLATE("DELETE_TEMPLATE"),
          CATEGORY_TYPE("CATEGORY_TYPE"),
          SUB_CATEGORY("SUB_CATEGORY"),
          CART_LIST("CART_LIST"),
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
      public static final String NO_IMAGE= "thumb-NoImage.png";

 //   http://beta.usha.businesstowork.com/assets/images/ProductImages/db6557b0-aee1-473a-b8a5-d8944d6acc4f.jpg
 //   http://beta.usha.businesstowork.com/assets/images/ProductImages/thumb-NoImage.png
    public static final String CATEGORY_IMAGE_URL_PREFIX =  Constants.IMAGE_URL_LIVE+"/assets/images/CategoryImages/";
      public static final String PRODUCT_IMAGE_URL_PREFIX =  Constants.IMAGE_URL_LIVE+"/assets/images/ProductImages/";
    public static final String DIVISION_IMAGE_URL_PREFIX =  Constants.IMAGE_URL_LIVE+"/assets/images/DivisionImages/";
}
