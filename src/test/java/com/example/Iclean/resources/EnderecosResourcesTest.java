package com.example.Iclean.resources;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.h2.bnf.context.DbTableOrView;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import com.example.Iclean.dto.CredentialsDTO;
import com.example.Iclean.dto.TokenDTO;
import com.example.Iclean.dto.UsuarioDTO;
import com.example.Iclean.entities.Endereco;
import com.example.Iclean.entities.Usuario;
import com.example.Iclean.repositories.UsuarioRepository;
import com.example.Iclean.services.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EnderecosResourcesTest {

	private MockMvc mockMvc;
	private String path = "http://localhost:8080/enderecos";

	@Autowired
	private BCryptPasswordEncoder passwordEncode;

	@Autowired
	private UsuarioRepository usuarioRepository;

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

		String resultString = tokenDto.getToken();

		return resultString;
	}

	@Test
	public void getEnderecoTest200() throws Exception {
		mockMvc.perform(get(path).param("Authorization", "Bearer " + obtainAccessToken())
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void getEnderecoTest403() throws Exception {
		mockMvc.perform(get(path + "/2").param("Authorization", "Bearer " + obtainAccessToken())
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden());
	}

	@Test
	public void getEnderecoTest404() throws Exception {
		mockMvc.perform(get(path + "/10").param("Authorization", "Bearer " + obtainAccessToken())
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}

	@Test
	public void deleteEnderecoTest204() throws Exception {
		mockMvc.perform(delete(path + "/2").param("Authorization", "Bearer " + obtainAccessToken())
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());
	}

	@Test
	public void deleteEnderecoTest404() throws Exception {
		mockMvc.perform(delete(path + "/6").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}

	@Test
	public void postEnderecoTest200() throws Exception {
		
		Usuario ux = new Usuario(null, "Usuario X", "123456789", passwordEncode.encode("senha456"),
				"usuario@gmail.com");
		

		mockMvc.perform(post(path).param("Authorization", "Bearer " + obtainAccessToken())
				.content(asJsonString(
						new Endereco(null, "Teste Post ", 300, "Bairro Teste", "Cidade Teste", "MG", "38400000", ux)))
				.contentType(MediaType.APPLICATION_JSON))						        
				.andExpect(status().isCreated());
		 
		
	}

	@Test
	public void postEnderecoTest403() throws Exception {
		mockMvc.perform(post(path + "/2").param("Authorization", "Bearer " + obtainAccessToken())
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden());
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
