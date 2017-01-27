package tilak.sample.com.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tilak.sample.com.model.ProfileModel;
import tilak.sample.com.model.QuestionModel;
import tilak.sample.com.service.QuestionService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class DashboardControllerTest {

    @Mock
    private QuestionService questionServiceMock;

    @InjectMocks
    private DashboardController dashboardController;


    public MockMvc mockMvc;



    /*@Autowired
    public ProfileService profileServiceMock;*/

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(dashboardController).build();

    }


    @Test
    public void testDashboard() throws Exception {

        // this value is used to set session data
        ProfileModel profileModel = new ProfileModel("58872a3510c5c38e5f0c3392", "admin", "admin@gmail.com", "12345", "123", "2017-01-24 11:48:18");


        QuestionModel questionModel = new QuestionModel();
        List<QuestionModel> questionModels = new ArrayList<>();

        questionModel.setId("5887918d8833b2ab4994ec74");
        questionModel.setTopic("java");
        questionModel.setContent("java content");
        questionModel.setProfileId("5887918d8833b2ab4994ec66");
        questionModel.setView("0");
        questionModel.setPost("0");
        questionModel.setCreatedDate("2017-01-24 11:25:28");

        questionModels.add(questionModel);


        questionModel = new QuestionModel();
        questionModel.setId("5887918d8833b2ab49942233");
        questionModel.setTopic("php");
        questionModel.setContent("php content");
        questionModel.setProfileId("5887918d8833b2ab4994ec66");
        questionModel.setView("0");
        questionModel.setPost("0");
        questionModel.setCreatedDate("2017-01-24 11:25:28");

        questionModels.add(questionModel);


        when(questionServiceMock.listQuestionByProfileId(isA(ProfileModel.class))).thenReturn(questionModels);

        mockMvc.perform(get("/dashboard/view")
                .sessionAttr("sessionProfile", profileModel)
        )
                .andExpect(status().isOk())
                .andExpect(view().name("dashboard"))
                .andExpect(forwardedUrl("dashboard"))
                .andExpect(model().attribute("questions", hasSize(2)))
                .andExpect(model().attribute("questions", hasItem(
                        allOf(
                                hasProperty("id", is("5887918d8833b2ab4994ec74")),
                                hasProperty("topic", is("java")),
                                hasProperty("content", is("java content")),
                                hasProperty("profileId", is("5887918d8833b2ab4994ec66")),
                                hasProperty("view", is("0")),
                                hasProperty("post", is("0")),
                                hasProperty("createdDate", is("2017-01-24 11:25:28"))
                        )
                )))
                .andExpect(model().attribute("questions", hasItem(
                        allOf(
                                hasProperty("id", is("5887918d8833b2ab49942233")),
                                hasProperty("topic", is("php")),
                                hasProperty("content", is("php content")),
                                hasProperty("profileId", is("5887918d8833b2ab4994ec66")),
                                hasProperty("view", is("0")),
                                hasProperty("post", is("0")),
                                hasProperty("createdDate", is("2017-01-24 11:25:28"))
                        )
                )));

        ;

        verify(questionServiceMock, times(1)).listQuestionByProfileId(isA(ProfileModel.class));
        verifyNoMoreInteractions(questionServiceMock);


    }
}