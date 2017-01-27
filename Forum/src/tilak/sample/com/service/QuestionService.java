package tilak.sample.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import tilak.sample.com.model.ProfileModel;
import tilak.sample.com.model.QuestionModel;
import tilak.sample.com.model.StatusModel;

import java.util.List;

@Repository
public class QuestionService {

    @Autowired
    private MongoTemplate mongoTemplate;

    private StatusModel statusModel;

    public static final String collectionName = "question";

    public List<QuestionModel> questionSortByView() {

        Query query = new Query();
        query.with(new Sort(Sort.Direction.ASC, "view"));

        return mongoTemplate.find(query, QuestionModel.class, collectionName);
    }

    public List<QuestionModel> listQuestionByProfileId(ProfileModel profileModel) {

        Query query = new Query();
        query.addCriteria(Criteria.where("profileId").is(profileModel.getId()));

        return mongoTemplate.find(query, QuestionModel.class, collectionName);
    }

    public StatusModel addQuestion(QuestionModel questionModel) {
        if (!mongoTemplate.collectionExists(collectionName)) {
            mongoTemplate.createCollection(collectionName);
        }

        mongoTemplate.insert(questionModel, collectionName);

        statusModel = new StatusModel();   /*initialize status model*/
        statusModel.setStatus("SUCCESS");

        return statusModel;
    }

    public List<QuestionModel> searchByTopic(String topic) {

        Query query = new Query();
        query.addCriteria(Criteria.where("topic").regex(topic));
        query.with(new Sort(Sort.Direction.ASC, "view"));

        return mongoTemplate.find(query, QuestionModel.class, collectionName);
    }


    public List<QuestionModel> topicList(String topic) {

        Query query = new Query();
        query.limit(10);
        query.addCriteria(Criteria.where("topic").regex(topic));
        query.fields().include("topic");

        return mongoTemplate.find(query, QuestionModel.class, collectionName);
    }


    public QuestionModel getById(QuestionModel  questionModel) {

        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(questionModel.getId()));

        return mongoTemplate.find(query, QuestionModel.class, collectionName).get(0);
    }

    public void updateView(QuestionModel  questionModel) {

        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(questionModel.getId()));

        QuestionModel questionModel1= mongoTemplate.find(query, QuestionModel.class, collectionName).get(0);
        int view= Integer.parseInt(questionModel1.getView()) ;

        query=new Query();
        query.addCriteria(Criteria.where("id").is(questionModel.getId()));

        Update update=new Update();
        update.set("view",view+1);

        mongoTemplate.updateFirst(query,update,QuestionModel.class,collectionName);

    }

    public void updatePost(QuestionModel  questionModel) {

        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(questionModel.getId()));

        QuestionModel questionModel1= mongoTemplate.find(query, QuestionModel.class, collectionName).get(0);
        int post= Integer.parseInt(questionModel1.getPost()) ;

        query=new Query();
        query.addCriteria(Criteria.where("id").is(questionModel.getId()));

        Update update=new Update();
        update.set("post",post+1);

        mongoTemplate.updateFirst(query,update,QuestionModel.class,collectionName);

    }




}
