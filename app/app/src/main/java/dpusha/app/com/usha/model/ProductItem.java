package dpusha.app.com.usha.model;

public class ProductItem {
    String image;
    String SKU;
    String Description;
    String Quantity;
    double UnitPrice;
    double Discount;
    double TaxPercent;
    boolean AvailableInStock;
    String DivCode;
    int Id;

    public ProductItem(String image, String SKU, String description, String quantity, double unitPrice, double discount, double taxPercent, boolean availableInStock, String divCode, int id) {
        this.image = image;
        this.SKU = SKU;
        Description = description;
        Quantity = quantity;
        UnitPrice = unitPrice;
        Discount = discount;
        TaxPercent = taxPercent;
        AvailableInStock = availableInStock;
        DivCode = divCode;
        Id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public double getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        UnitPrice = unitPrice;
    }

    public double getDiscount() {
        return Discount;
    }

    public void setDiscount(double discount) {
        Discount = discount;
    }

    public double getTaxPercent() {
        return TaxPercent;
    }

    public void setTaxPercent(double taxPercent) {
        TaxPercent = taxPercent;
    }

    public boolean isAvailableInStock() {
        return AvailableInStock;
    }

    public void setAvailableInStock(boolean availableInStock) {
        AvailableInStock = availableInStock;
    }

    public String getDivCode() {
        return DivCode;
    }

    public void setDivCode(String divCode) {
        DivCode = divCode;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}
