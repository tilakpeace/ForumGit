package tilak.sample.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import tilak.sample.com.model.CommentModel;
import tilak.sample.com.model.QuestionModel;
import tilak.sample.com.model.StatusModel;

import java.util.List;

@Repository
public class CommentService {

    @Autowired
    public MongoTemplate mongoTemplate;

    private StatusModel statusModel;

    public static final String collectionName = "comment";



    public List<CommentModel> getByQuestion(CommentModel commentModel) {

        Query query = new Query();
        query.addCriteria(Criteria.where("questionId").is(commentModel.getQuestionId()));

        return mongoTemplate.find(query, CommentModel.class, collectionName);
    }


    public StatusModel addComment(CommentModel commentModel) {
        if (!mongoTemplate.collectionExists(collectionName)) {
            mongoTemplate.createCollection(collectionName);
        }

        mongoTemplate.insert(commentModel, collectionName);

        statusModel = new StatusModel();   /*initialize status model*/
        statusModel.setStatus("SUCCESS");

        return statusModel;
    }



}
