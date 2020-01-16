package model;

public class Topic {

    private Integer topicId;
    private String topicTitle;
    private String topicContent;
    private String topicTime;
    private Integer topicType;
    private String topicName;
    private String topicTelephone;
    private String topicEmail;

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public String getTopicContent() {
        return topicContent;
    }

    public void setTopicContent(String topicContent) {
        this.topicContent = topicContent;
    }

    public String getTopicTime() {
        return topicTime;
    }

    public void setTopicTime(String topicTime) {
        this.topicTime = topicTime;
    }

    public Integer getTopicType() {
        return topicType;
    }

    public void setTopicType(Integer topicType) {
        this.topicType = topicType;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicTelephone() {
        return topicTelephone;
    }

    public void setTopicTelephone(String topicTelephone) {
        this.topicTelephone = topicTelephone;
    }

    public String getTopicEmail() {
        return topicEmail;
    }

    public void setTopicEmail(String topicEmail) {
        this.topicEmail = topicEmail;
    }

    public Topic(Integer topicId, String topicTitle, String topicContent, String topicTime, Integer topicType, String topicName, String topicTelephone, String topicEmail) {
        this.topicId = topicId;
        this.topicTitle = topicTitle;
        this.topicContent = topicContent;
        this.topicTime = topicTime;
        this.topicType = topicType;
        this.topicName = topicName;
        this.topicTelephone = topicTelephone;
        this.topicEmail = topicEmail;
    }

    public Topic(){

    }

    @Override
    public String toString() {
        return "Topic{" +
                "topicId=" + topicId +
                ", topicTitle='" + topicTitle + '\'' +
                ", topicContent='" + topicContent + '\'' +
                ", topicTime='" + topicTime + '\'' +
                ", topicType=" + topicType +
                ", topicName='" + topicName + '\'' +
                ", topicTelephone='" + topicTelephone + '\'' +
                ", topicEmail='" + topicEmail + '\'' +
                '}';
    }
}

