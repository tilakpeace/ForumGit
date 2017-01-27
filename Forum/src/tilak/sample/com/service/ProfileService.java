package tilak.sample.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import tilak.sample.com.model.ProfileModel;
import tilak.sample.com.model.QuestionModel;
import tilak.sample.com.model.StatusModel;

import java.util.List;

@Repository
public class ProfileService {
	
	@Autowired
	public MongoTemplate mongoTemplate;
	
	public static final String collectionName = "profile";

	private  StatusModel statusModel;
	


	public List<ProfileModel> listProfile() {
		return mongoTemplate.findAll(ProfileModel.class, collectionName);
	}

	public List<ProfileModel> loginCheck(ProfileModel profileModel) {

		Query query=new Query();
		query.addCriteria(Criteria.where("emailId").is(profileModel.getEmailId()).and("password").is(profileModel.getPassword()));
		// List<ProfileModel> profileModels=mongoTemplate.find(query,ProfileModel.class,COLLECTION_NAME);

		return mongoTemplate.find(query,ProfileModel.class,collectionName);
	}

	public ProfileModel getById(ProfileModel  profileModel) {

		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(profileModel.getId()));

		return mongoTemplate.find(query, ProfileModel.class, collectionName).get(0);
	}

	public StatusModel addProfile(ProfileModel profileModel) {
		if (!mongoTemplate.collectionExists(collectionName)) {
			mongoTemplate.createCollection(collectionName);
		}

		mongoTemplate.insert(profileModel, collectionName);

		statusModel = new StatusModel();   /*initialize status model*/
		statusModel.setStatus("SUCCESS");

		return statusModel;
	}
}
