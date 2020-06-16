package dpusha.app.com.usha.model;

public class SchemeSpinnerResponse {

   private String Label;
   private String Value;
   private String ParentId;

    public SchemeSpinnerResponse(String Value, String Label) {
        this.Value = Value;
        this.Label = Label;
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    public String getParentId() {
        return ParentId;
    }

    public void setParentId(String parentId) {
        ParentId = parentId;
    }

    @Override
    public String toString() {
        return Label;
    }
}
