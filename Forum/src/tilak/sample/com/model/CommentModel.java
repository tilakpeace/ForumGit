package tilak.sample.com.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class CommentModel {

    @Id
    private String id;

    private String questionId;
    private String profileId;
    private String comment;
    private String createdDate;

    private ProfileModel profile;

    public CommentModel() {
    }

    public CommentModel(String id, String questionId, String profileId, String comment, String createdDate) {
        this.id = id;
        this.questionId = questionId;
        this.profileId = profileId;
        this.comment = comment;
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

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "CommentModel{" +
                "id='" + id + '\'' +
                ", questionId='" + questionId + '\'' +
                ", profileId='" + profileId + '\'' +
                ", comment='" + comment + '\'' +
                ", createdDate='" + createdDate + '\'' +
                '}';
    }
}
