
package dpusha.app.com.usha.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShipToParty {

    @SerializedName("ColumnName")
    @Expose
    private String columnName;
    @SerializedName("ColumnValue")
    @Expose
    private String columnValue;
    @SerializedName("TableName")
    @Expose
    private Object tableName;
    @SerializedName("LookupType")
    @Expose
    private Object lookupType;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ShipToParty() {
    }

    /**
     * 
     * @param lookupType
     * @param columnValue
     * @param columnName
     * @param tableName
     */
    public ShipToParty(String columnName, String columnValue, Object tableName, Object lookupType) {
        super();
        this.columnName = columnName;
        this.columnValue = columnValue;
        this.tableName = tableName;
        this.lookupType = lookupType;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnValue() {
        return columnValue;
    }

    public void setColumnValue(String columnValue) {
        this.columnValue = columnValue;
    }

    public Object getTableName() {
        return tableName;
    }

    public void setTableName(Object tableName) {
        this.tableName = tableName;
    }

    public Object getLookupType() {
        return lookupType;
    }

    public void setLookupType(Object lookupType) {
        this.lookupType = lookupType;
    }

    @Override
    public String toString() {
        return  columnName ;
    }

    public ShipToParty(String columnName, String columnValue) {
        this.columnName = columnName;
        this.columnValue = columnValue;
    }
}
