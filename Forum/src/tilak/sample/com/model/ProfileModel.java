package tilak.sample.com.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ProfileModel {

	@Id
	private String id;

	private String name;
	private String emailId;
	private String phoneNo;
	private String password;
	private String createdDate;

	public ProfileModel() {
	}

	public ProfileModel(String id, String name, String emailId, String phoneNo, String password, String createdDate) {
		this.id = id;
		this.name = name;
		this.emailId = emailId;
		this.phoneNo = phoneNo;
		this.password = password;
		this.createdDate = createdDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "ProfileModel{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", emailId='" + emailId + '\'' +
				", phoneNo='" + phoneNo + '\'' +
				", password='" + password + '\'' +
				", createdDate='" + createdDate + '\'' +
				'}';
	}
}
