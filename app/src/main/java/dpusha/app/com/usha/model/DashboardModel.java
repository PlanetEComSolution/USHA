package dpusha.app.com.usha.model;

public class DashboardModel {
    String title;
    String subTitle1;
    String subTitle2;
    Integer image;

    public DashboardModel(String title, String subTitle1, String subTitle2, Integer image) {
        this.title = title;
        this.subTitle1 = subTitle1;
        this.subTitle2 = subTitle2;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle1() {
        return subTitle1;
    }

    public void setSubTitle1(String subTitle1) {
        this.subTitle1 = subTitle1;
    }

    public String getSubTitle2() {
        return subTitle2;
    }

    public void setSubTitle2(String subTitle2) {
        this.subTitle2 = subTitle2;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }
}
