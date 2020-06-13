package dpusha.app.com.usha.model;

public class AnnouncementList {
    private String Sequence;
    private String AnnouncementTitle;
    private String EffectiveFrom;
    private String EffectiveTo;
    private String AnnouncementFor;
    private String DisplayOn;
    private String AnnouncementContent;

    public void setSequence(String sequence) {
        Sequence = sequence;
    }

    public void setAnnouncementTitle(String announcementTitle) {
        AnnouncementTitle = announcementTitle;
    }

    public void setEffectiveFrom(String effectiveFrom) {
        EffectiveFrom = effectiveFrom;
    }

    public void setEffectiveTo(String effectiveTo) {
        EffectiveTo = effectiveTo;
    }

    public void setAnnouncementFor(String announcementFor) {
        AnnouncementFor = announcementFor;
    }

    public void setDisplayOn(String displayOn) {
        DisplayOn = displayOn;
    }

    public void setAnnouncementContent(String announcementContent) {
        AnnouncementContent = announcementContent;
    }

    public String getSequence() {
        return Sequence;
    }

    public String getAnnouncementTitle() {
        return AnnouncementTitle;
    }

    public String getEffectiveFrom() {
        return EffectiveFrom;
    }

    public String getEffectiveTo() {
        return EffectiveTo;
    }

    public String getAnnouncementFor() {
        return AnnouncementFor;
    }

    public String getDisplayOn() {
        return DisplayOn;
    }

    public String getAnnouncementContent() {
        return AnnouncementContent;
    }
}
