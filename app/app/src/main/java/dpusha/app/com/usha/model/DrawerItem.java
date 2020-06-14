package dpusha.app.com.usha.model;

public class DrawerItem {
    String Name;
    Integer Icon;

    public DrawerItem(String name, Integer icon) {
        Name = name;
        Icon = icon;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getIcon() {
        return Icon;
    }

    public void setIcon(Integer icon) {
        Icon = icon;
    }
}
