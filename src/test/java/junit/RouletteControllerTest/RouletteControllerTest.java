package junit.RouletteControllerTest;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.net.URI;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
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


import config.TestContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = TestContext.class)
@ActiveProfiles("local")
public class RouletteControllerTest {

	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;

	@Before
	public void setup () {
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
		this.mockMvc = builder.build();
	}
	
	
	public void testUserController () throws Exception {
		
    	MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "xml", java.nio.charset.Charset.forName("UTF-8"));

        ResultMatcher ok = MockMvcResultMatchers.status()
                                                .isOk();

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/testInit").contentType(MEDIA_TYPE_JSON_UTF8).accept(MEDIA_TYPE_JSON_UTF8).
                content(createInitBody());


        this.mockMvc.perform(builder)
        			.andDo(print())
                    .andExpect(MockMvcResultMatchers.status()
                                                    .isOk());
		 
	}
	
	@Test
	public void testInit() throws Exception {/*
		MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "xml", java.nio.charset.Charset.forName("UTF-8"));

        ResultMatcher ok = MockMvcResultMatchers.status()
                                                .isOk();

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(new URI("locahost:9000/roulette/init")).contentType(MEDIA_TYPE_JSON_UTF8).accept(MEDIA_TYPE_JSON_UTF8).
                            content(createInitBody());

        System.out.println(builder.toString() + mockMvc.toString());
        this.mockMvc.perform(builder)
        			.andExpect(content().contentType("application/xml"));
                    
	*/}

	private static String createInitBody () {
		return  "<GameRequest><Header><GameDetails class=\"GBet\" name=\"RouletteV3\" channel=\"I\" free_play=\"No\"/>\r\n" + 
				"<Customer cookie=\"ff0cc14d8e677f7622591afd667f0ea9\" is_guest=\"No\" csrf_token=\"SXjTmkWSxrcXUFrsh6EULWpM7ffZhz7yz rynTs5CiKmAj50ev1As92ounJYBso=\"  affiliate=\"\"  />\r\n" + 
				"</Header><Init definition=\"Yes\" payout=\"Yes\" promotions=\"Yes\" freebets=\"Yes\" config=\"Yes\" />\r\n" + 
				"<Stats><StatsAttribute name=\"FavouriteBet\" parameter=\"200\"/></Stats>\r\n" + 
				"</GameRequest>";
	}
	
}