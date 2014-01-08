package ru.koleslena.studen.test.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 *  @author koleslena
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:TestWebApplicationContext.xml", 
		"classpath:TestWebServletContext.xml"})
@WebAppConfiguration
public class LoginTest {

 	@Autowired
 	private WebApplicationContext wac;

  	private MockMvc mockMvc;
	
    @Before
    public void setup() {
      this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }
    
	@Test
	public void loginPageTest() {
	    try {
			this.mockMvc.perform(get("/login"))
			  .andExpect(status().isOk())
			  .andExpect(content().contentType(MediaType.TEXT_HTML))
			  .andExpect(view().name("login"));
		} catch (Exception e) {
			assertNull("exception during test login page", e);
		}
	}
	
	public void examplejsonTest() {
		String keyword = "Very Nice Shoes";
		try {
			this.mockMvc.perform(get("/product/search")
			      .param("q", keyword)
			      .accept(MediaType.APPLICATION_JSON))
			  .andExpect(status().isOk())
			  .andExpect(content().contentType(MediaType.APPLICATION_JSON))
			  .andExpect(jsonPath("$.name").value(keyword));
		} catch (Exception e) {
			assertNull("exception during test json", e);
		}
		
		/* mockMvc.perform(post("/form"))
		   .andExpect(status().isOk())
		   .andExpect(redirectedUrl("/person/1"))
		   .andExpect(model().size(1))
		   .andExpect(model().attributeExists("person"))
		   .andExpect(flash().attributeCount(1))
		   .andExpect(flash().attribute("message", "success!"));
		   
		   forwardedUrl("/WEB-INF/layouts/main.jsp")
		   
		   String notExistingId = "999";
			String newUserName = "newUser";
			this.mockMvc.perform(post("/customer/register")
			        .param("id", notExistingId)
			        .param("username", newUserName)
			        .param("password", "123")
			        .param("emailAddress", "testIgnored@gmail.com")
			        .param("address.country", "RU")
			        .param("address.city", "Nsk")
			        .param("address.street", "Lenin"))
			        .andExpect(model().hasNoErrors())
			        .andExpect(view().name("redirect:/index.htm"));
			Optional<Account> account = accountService.getAccount(newUserName);
			assertTrue( "Account with the username should exist", account.isPresent());
			assertNotSame("Account id should not be equal to the id we try to pass with parameters",
	        Long.parseLong(notExistingId),
	        account.get().getId());
        
         .andExpect(status().isMovedTemporarily()).andExpect(model().hasNoErrors())
		   */
	}
}
