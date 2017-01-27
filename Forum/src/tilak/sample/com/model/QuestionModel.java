package tilak.sample.com.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class QuestionModel {

    @Id
    private String id;

    private String topic;
    private String content;
    private String profileId;
    private String view;
    private String post;
    private String createdDate;

    private ProfileModel profile;

    public QuestionModel() {
    }

    public QuestionModel(String id, String topic, String content, String profileId, String view, String post, String createdDate) {
        this.id = id;
        this.topic = topic;
        this.content = content;
        this.profileId = profileId;
        this.view = view;
        this.post = post;
        this.createdDate = createdDate;
    }

    public ProfileModel getProfile() {
        return profile;
    }

    public void setProfile(ProfileModel profile) {
        this.profile = profile;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "QuestionModel{" +
                "id='" + id + '\'' +
                ", topic='" + topic + '\'' +
                ", content='" + content + '\'' +
                ", profileId='" + profileId + '\'' +
                ", view='" + view + '\'' +
                ", createdDate='" + createdDate + '\'' +
                '}';
    }
}
