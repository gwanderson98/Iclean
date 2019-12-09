package com.example.Iclean.resources;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.Iclean.dto.CredentialsDTO;
import com.example.Iclean.dto.TokenDTO;
import com.example.Iclean.services.AuthService;



@RunWith(SpringRunner.class)
@SpringBootTest
public class EnderecosResourcesTest {

	private MockMvc mockMvc;
	private String path = "http://localhost:8080/enderecos";
	
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private AuthService authService;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}
	
	private String obtainAccessToken() throws Exception {
	 
		CredentialsDTO cDto = new CredentialsDTO();
		
		cDto.setEmail("marcomala@gmail.com");
		cDto.setSenha("senha321");
		
		TokenDTO tokenDto = authService.authenticate(cDto);
	 
	    String resultString = tokenDto.getToken();//    // result.andReturn().getResponse().getContentAsString();
	 
	    
	    return resultString;
	}
	
	@Test
	public void getEnderecoTest200() throws Exception {		
	    mockMvc.perform(get(path)
	    		  .param("Authorization", "Bearer " + obtainAccessToken())
	    	      .contentType(MediaType.APPLICATION_JSON))
	    	      	.andExpect(status().isOk());
	    	} 	
	
	@Test
	public void getEnderecoTest403() throws Exception {		
		  mockMvc.perform(get(path + "/2")
	    		  .param("Authorization", "Bearer " + obtainAccessToken())
	    	      .contentType(MediaType.APPLICATION_JSON))
	    	      	.andExpect(status().isForbidden());
	    	}
	
	@Test
	public void getEnderecoTest404() throws Exception {		
		  mockMvc.perform(get(path + "/10")
	    		  .param("Authorization", "Bearer " + obtainAccessToken())
	    	      .contentType(MediaType.APPLICATION_JSON))
	    	      	.andExpect(status().isNotFound());
	    	}
	
	@Test
	public void deleteEnderecoTest204() throws Exception {		
	    mockMvc.perform(delete(path + "/2")
	    		  .param("Authorization", "Bearer " + obtainAccessToken())
	    	      .contentType(MediaType.APPLICATION_JSON))
	    	      	.andExpect(status().isNoContent());
	    	}
	
	@Test
	public void deleteEnderecoTest404() throws Exception {		
	    mockMvc.perform(delete(path + "/6")
	    	      .contentType(MediaType.APPLICATION_JSON))
	    	      	.andExpect(status().isNotFound());
	    	}
	
	
	@Test
	public void postEnderecoTest200() throws Exception {		
	    mockMvc.perform(post(path + "/1")
	    		  .param("Authorization", "Bearer " + obtainAccessToken())
	    	      .contentType(MediaType.APPLICATION_JSON))
	    	      	.andExpect(status().isOk());
	    	} 
	
	@Test
	public void postEnderecoTest403() throws Exception {		
	    mockMvc.perform(post(path + "/2")
	    		.param("Authorization", "Bearer " + obtainAccessToken())
	    	      .contentType(MediaType.APPLICATION_JSON))
	    	      	.andExpect(status().isForbidden());
	    	}
	
	

}
