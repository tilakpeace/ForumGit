package tilak.sample.com.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Dell on 1/27/2017.
 */
public class IndexControllerTest {


    @InjectMocks
    private IndexController indexController;


    public MockMvc mockMvc;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(indexController).build();

        RedirectAttributes flashAttributes;
        flashAttributes= Mockito.mock(RedirectAttributes.class);
    }

    @Test
    public void testHome() throws Exception {

        mockMvc.perform(get("/"))
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(view().name("redirect:login/view"))
                .andExpect(redirectedUrl("login/view"));


    }
}