package tilak.sample.com.controller;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tilak.sample.com.model.ProfileModel;
import tilak.sample.com.model.StatusModel;
import tilak.sample.com.service.ProfileService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"file:WebContent/WEB-INF/dispatcher-servlet.xml"})
public class LoginControllerTest {


    @Mock
    private ProfileService profileServiceMock;

    @InjectMocks
    private LoginController loginController;


    public MockMvc mockMvc;



    /*@Autowired
    public ProfileService profileServiceMock;*/

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(loginController).build();

        RedirectAttributes flashAttributes;
        flashAttributes=Mockito.mock(RedirectAttributes.class);
    }


    @Test
    public void testView() throws Exception {

        ProfileModel first = new ProfileModel("111", "tilak", "tilak@gmail.com", "123456", "123", "dsfs");
        ProfileModel second = new ProfileModel("222", "tilak1", "tilak1@gmail.com", "1234561", "1231", "dsfs1");

        List<ProfileModel> profileModels = new ArrayList<>();
        profileModels.add(first);
        profileModels.add(second);

        when(profileServiceMock.listProfile()).thenReturn(profileModels);

        mockMvc.perform(get("/login/view"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(forwardedUrl("login"))
                .andExpect(model().attribute("profileTest", hasSize(2)))
                .andExpect(model().attribute("profileTest", hasItem(
                        allOf(
                                hasProperty("id", is("111")),
                                hasProperty("name", is("tilak")),
                                hasProperty("emailId", is("tilak@gmail.com")),
                                hasProperty("phoneNo", is("123456")),
                                hasProperty("password", is("123")),
                                hasProperty("createdDate", is("dsfs"))
                        )
                )))
                .andExpect(model().attribute("profileTest", hasItem(
                        allOf(
                                hasProperty("id", is("222")),
                                hasProperty("name", is("tilak1")),
                                hasProperty("emailId", is("tilak1@gmail.com")),
                                hasProperty("phoneNo", is("1234561")),
                                hasProperty("password", is("1231")),
                                hasProperty("createdDate", is("dsfs1"))
                        )
                )));

        verify(profileServiceMock, times(1)).listProfile();
        verifyNoMoreInteractions(profileServiceMock);

    }



    /*handle if login is successfull*/
    @Test
    public void testAccessSuccess() throws Exception {

     /*   { "_id" : ObjectId("58872a3510c5c38e5f0c3392"), "name" : "admin", "emailId" : "admin@gmail.com", "phoneNo" : "12345", "password" : "123", "createdDate" : "dsfs" }*/

        List<ProfileModel>  outputProfileModels=new ArrayList<>();
        outputProfileModels.add(new ProfileModel("58872a3510c5c38e5f0c3392","admin","admin@gmail.com","12345","123","dsfs"));  /*======>this is output*/

        when(profileServiceMock.loginCheck(isA(ProfileModel.class))).thenReturn(outputProfileModels);

        mockMvc.perform(post("/login/access")
                //.contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("emailId", "admin@gmail.com")
                .param("password", "123")
        )
                // .andExpect(status().isOk())   ==> no  200 because it is redirected to another url
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(view().name("redirect:../dashboard/view"))
                .andExpect(redirectedUrl("../dashboard/view"));


        verify(profileServiceMock, times(1)).loginCheck(isA(ProfileModel.class));
        verifyNoMoreInteractions(profileServiceMock);

    }


    /*handle is login is unsuccessfull*/

    @Test
    public void testAccessUnsuccess() throws Exception {

     /*   { "_id" : ObjectId("58872a3510c5c38e5f0c3392"), "name" : "admin", "emailId" : "admin@gmail.com", "phoneNo" : "12345", "password" : "123", "createdDate" : "dsfs" }*/

        List<ProfileModel>  outputProfileModels=new ArrayList<>();
        outputProfileModels.add(new ProfileModel("58872a3510c5c38e5f0c3392","admin","admin@gmail.com","12345","123","dsfs"));  /*======>this is output*/

        when(profileServiceMock.loginCheck(isA(ProfileModel.class))).thenReturn(new ArrayList<ProfileModel>());

        mockMvc.perform(post("/login/access")
                .param("emailId", "admin@gmail.com")
                .param("password", "1233445")
                .sessionAttr("profileModel", new ProfileModel())
                )
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(view().name("redirect:view"))
                .andExpect(redirectedUrl("view"))
                .andExpect(MockMvcResultMatchers.flash().attribute("statusModel", hasProperty("status", is("UNSUCCESS"))))
                .andExpect(MockMvcResultMatchers.flash().attribute("statusModel", hasProperty("message", is("Incorrect Email Id and Password"))))
                .andExpect(MockMvcResultMatchers.flash().attribute("statusModel", hasProperty("mode", is("invalidMode"))));


        verify(profileServiceMock, times(1)).loginCheck(isA(ProfileModel.class));
        verifyNoMoreInteractions(profileServiceMock);

    }


    @Test
    public void testLogOut() throws Exception {

        ProfileModel profileModel = new ProfileModel("58872a3510c5c38e5f0c3392", "admin", "admin@gmail.com", "12345", "123", "2017-01-24 11:48:18");

        mockMvc.perform(get("/login/logout")
                .sessionAttr("sessionProfile", profileModel)
        )
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(view().name("redirect:view"))
                .andExpect(redirectedUrl("view"));



    }


    @Test
    public void testRegister() throws Exception {

        StatusModel statusModel=new StatusModel();
        statusModel.setStatus("SUCCESS");


        when(profileServiceMock.addProfile(isA(ProfileModel.class))).thenReturn(statusModel);

        mockMvc.perform(post("/login/register")
                .param("name", "tilak")
                .param("emailId", "tilak@gmail.com")
                .param("phoneNo", "2424242")
                .param("password", "123")
        )
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(view().name("redirect:view"))
                .andExpect(redirectedUrl("view"))
                .andExpect(MockMvcResultMatchers.flash().attribute("statusModel", hasProperty("status", is("SUCCESS"))))
                .andExpect(MockMvcResultMatchers.flash().attribute("statusModel", hasProperty("message", is("Successfully signed up"))))
                .andExpect(MockMvcResultMatchers.flash().attribute("statusModel", hasProperty("mode", is("registerMode"))));


        verify(profileServiceMock, times(1)).addProfile(isA(ProfileModel.class));
        verifyNoMoreInteractions(profileServiceMock);

    }






}