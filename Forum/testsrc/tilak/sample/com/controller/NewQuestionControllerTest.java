package tilak.sample.com.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tilak.sample.com.model.ProfileModel;
import tilak.sample.com.model.QuestionModel;
import tilak.sample.com.model.StatusModel;
import tilak.sample.com.service.QuestionService;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class NewQuestionControllerTest {

    @Mock
    private QuestionService questionService;

    @InjectMocks
    private NewQuestionController newQuestionController;

    MockHttpSession session;


    public MockMvc mockMvc;



    /*@Autowired
    public ProfileService profileServiceMock;*/

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(newQuestionController).build();

        RedirectAttributes flashAttributes;
        flashAttributes = Mockito.mock(RedirectAttributes.class);
    }


    @Test
    public void testViewQuestion() throws Exception {

        mockMvc.perform(get("/newQuestion/view"))
                .andExpect(status().isOk())
                .andExpect(view().name("newQuestion"))
                .andExpect(forwardedUrl("newQuestion"));

       /* verify(questionService, times(1)).listProfile();
        verifyNoMoreInteractions(questionService);*/


    }

    @Test
    public void testAddQuestion() throws Exception {

        // this value is used to set session data
        ProfileModel profileModel = new ProfileModel("58872a3510c5c38e5f0c3392", "admin", "admin@gmail.com", "12345", "123", "2017-01-24 11:48:18");


        when(questionService.addQuestion(isA(QuestionModel.class))).thenReturn(new StatusModel("SUCCESS", "Question Posted Successfully", "questionUpdate"));

        mockMvc.perform(post("/newQuestion/add")
                .param("topic", "Java testing ")
                .param("content", "This is java content")
                .sessionAttr("sessionProfile", profileModel)  /*==>session has set with 'sessionProfile' name*/
        )

                .andExpect(status().isMovedTemporarily())
                .andExpect(view().name("redirect:../dashboard/view"))
                .andExpect(redirectedUrl("../dashboard/view"))
                .andExpect(MockMvcResultMatchers.flash().attribute("statusModel", hasProperty("status", is("SUCCESS"))))
                .andExpect(MockMvcResultMatchers.flash().attribute("statusModel", hasProperty("message", is("Question Posted Successfully"))))
                .andExpect(MockMvcResultMatchers.flash().attribute("statusModel", hasProperty("mode", is("questionUpdate"))));
        ;

        verify(questionService, times(1)).addQuestion(isA(QuestionModel.class));
        verifyNoMoreInteractions(questionService);


    }
}