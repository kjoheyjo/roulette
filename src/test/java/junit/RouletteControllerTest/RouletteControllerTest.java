package junit.RouletteControllerTest;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.gt.controller.RouletteController;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = RouletteController.class)
public class RouletteControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup () {
        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
        this.mockMvc = builder.build();
    }

    @Test
    public void testUserController () throws Exception {
    	MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "xml", java.nio.charset.Charset.forName("UTF-8"));

        ResultMatcher ok = MockMvcResultMatchers.status()
                                                .isOk();

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/init").contentType(MEDIA_TYPE_JSON_UTF8).accept(MEDIA_TYPE_JSON_UTF8).
                            content(createUserInXml( "joe","joe@example.com","abc"));
        

        this.mockMvc.perform(builder)
        			.andDo(print())
                    .andExpect(MockMvcResultMatchers.status()
                                                    .isOk());
    }

    private static String createUserInXml (String name, String email, String password) {
        return  "<GameRequest><Header><GameDetails class=\"GBet\" name=\"RouletteV3\" channel=\"I\" free_play=\"No\"/>" + 
        		"<Customer cookie=\"aTXT3wrn0c/9Ctd7mGZullM1JNTxpL3MTPqMCke/NPQ1cgELzEjQL8o33 1ihsa0CsSR\" is_guest=\"No\" csrf_token=\"SXjTmkWSxrcXUFrsh6EULWpM7ffZhz7yz rynTs5CiKmAj50ev1As92ounJYBso=\"  affiliate=\"\"  />" + 
        		"</Header><Init definition=\"Yes\" payout=\"Yes\" promotions=\"Yes\" freebets=\"Yes\" config=\"Yes\" />" + 
        		"<Stats><StatsAttribute name=\"FavouriteBet\" parameter=\"200\"/></Stats>" + 
        		"</GameRequest>";
    }
}