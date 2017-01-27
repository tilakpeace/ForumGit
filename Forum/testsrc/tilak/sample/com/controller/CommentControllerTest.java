package tilak.sample.com.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tilak.sample.com.model.CommentModel;
import tilak.sample.com.model.ProfileModel;
import tilak.sample.com.model.QuestionModel;
import tilak.sample.com.model.StatusModel;
import tilak.sample.com.service.CommentService;
import tilak.sample.com.service.ProfileService;
import tilak.sample.com.service.QuestionService;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Adler32;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

/**
 * Created by Dell on 1/27/2017.
 */
public class CommentControllerTest {


    @Mock
    private QuestionService questionServiceMock;

    @Mock
    CommentService commentServiceMock;

    @Mock
    ProfileService profileServiceMock;

    @InjectMocks
    private commentController commentController;


    public MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(commentController).build();

    }

    @Test
    public void testSearchByQuest() throws Exception {

        QuestionModel questionModel=new QuestionModel("12345","java","this is java content","58872a3510c5c38e5f0c3392","0","0","2017-01-24 11:48:18");
        ProfileModel profileModel = new ProfileModel("58872a3510c5c38e5f0c3392", "admin", "admin@gmail.com", "12345", "123", "2017-01-24 11:48:18");

        CommentModel commentModel=new CommentModel("666666","12345","58872a3510c5c38e5f0c3392","comment for java","2017-01-24 11:48:18");
        List<CommentModel> commentModels=new ArrayList<>();
        commentModels.add(commentModel);


        when(questionServiceMock.getById(isA(QuestionModel.class))).thenReturn(questionModel);
        when(profileServiceMock.getById(isA(ProfileModel.class))).thenReturn(profileModel);
        when(commentServiceMock.getByQuestion(isA(CommentModel.class))).thenReturn(commentModels);


        mockMvc.perform(get("/comment/question/{questionId}","12345"))
                .andExpect(status().isOk())
                .andExpect(view().name("comment"))
                .andExpect(forwardedUrl("comment"))
                .andExpect(model().attribute("question", hasProperty("id", is("12345"))))
                .andExpect(model().attribute("question", hasProperty("topic", is("java"))))
                .andExpect(model().attribute("question", hasProperty("content", is("this is java content"))))
                .andExpect(model().attribute("question", hasProperty("profileId", is("58872a3510c5c38e5f0c3392"))))
                .andExpect(model().attribute("question", hasProperty("view", is("0"))))
                .andExpect(model().attribute("question", hasProperty("post", is("0"))))
                .andExpect(model().attribute("question", hasProperty("createdDate", is("2017-01-24 11:48:18"))))
                .andExpect(model().attribute("comment", hasSize(1)))
                .andExpect(model().attribute("comment", hasItem(
                        allOf(
                                hasProperty("id", is("666666")),
                                hasProperty("questionId", is("12345")),
                                hasProperty("profileId", is("58872a3510c5c38e5f0c3392")),
                                hasProperty("comment", is("comment for java")),
                                hasProperty("createdDate", is("2017-01-24 11:48:18"))
                        )
                )));

        verify(questionServiceMock, times(1)).getById(isA(QuestionModel.class));
        verify(profileServiceMock, times(2)).getById(isA(ProfileModel.class));
        verify(commentServiceMock, times(1)).getByQuestion(isA(CommentModel.class));
        verifyNoMoreInteractions(profileServiceMock);
        verifyNoMoreInteractions(commentServiceMock);






    }

    @Test
    public void testAdd() throws Exception {

        // this value is used to set session data
        ProfileModel profileModel = new ProfileModel("58872a3510c5c38e5f0c3392", "admin", "admin@gmail.com", "12345", "123", "2017-01-24 11:48:18");


        when(commentServiceMock.addComment(isA(CommentModel.class))).thenReturn(new StatusModel("SUCCESS", "Comment Posted Successfully", "commentUpdate"));

        mockMvc.perform(post("/comment/add")
                .param("questionId", "2432432")
                .param("comment", "This is java comment")
                .sessionAttr("sessionProfile", profileModel)  /*==>session has set with 'sessionProfile' name*/
        )

                .andExpect(status().isMovedTemporarily())
                .andExpect(view().name("redirect:/comment/question/2432432"))
                .andExpect(redirectedUrl("/comment/question/2432432"))
                .andExpect(MockMvcResultMatchers.flash().attribute("statusModel", hasProperty("status", is("SUCCESS"))))
                .andExpect(MockMvcResultMatchers.flash().attribute("statusModel", hasProperty("message", is("Comment Posted Successfully"))))
                .andExpect(MockMvcResultMatchers.flash().attribute("statusModel", hasProperty("mode", is("commentUpdate"))));
        ;

        verify(commentServiceMock, times(1)).addComment(isA(CommentModel.class));
        verifyNoMoreInteractions(commentServiceMock);

    }
}