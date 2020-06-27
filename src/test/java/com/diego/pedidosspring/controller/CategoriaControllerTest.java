package com.diego.pedidosspring.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.diego.pedidosspring.PedidosSpringApplication;
import com.diego.pedidosspring.controllers.CategoriaController;
import com.diego.pedidosspring.controllers.exceptions.ControllerExceptionHandler;
import com.diego.pedidosspring.model.Categoria;
import com.diego.pedidosspring.security.JWTUtil;
import com.diego.pedidosspring.services.CategoriaService;
import com.diego.pedidosspring.services.exceptions.ObjectNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.net.HttpHeaders;

@WithMockUser(username = "admin")
@WebMvcTest(CategoriaController.class)
@ContextConfiguration(classes = {
		PedidosSpringApplication.class,
		JWTUtil.class
})
public class CategoriaControllerTest {

	private final String BASE_URL = "/categorias";
	private ObjectMapper objectMapper;
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private CategoriaController categoriaController;
	
	@MockBean
	private CategoriaService categoriaService;
	
	@BeforeEach
	public void setUp() {
		objectMapper = new ObjectMapper();
		mockMvc = MockMvcBuilders.standaloneSetup(categoriaController)
				.setControllerAdvice(new ControllerExceptionHandler()).build();
	}
	
	
	@Test
	public void itShouldReturnOk_WhenFindById() throws Exception {
		when(this.categoriaService.findById(1L)).thenReturn(new Categoria(1L, "Esporte"));
		
		mockMvc.perform(get(BASE_URL + "/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.descricao", is("Esporte")));
	}
	
	@WithMockUser(username = "admin")
	@Test
	public void itShouldReturnNotFound_WhenFindById() throws Exception {
		when(this.categoriaService.findById(2L)).thenThrow(new ObjectNotFoundException("Not Found!"));
		
		mockMvc.perform(get("/2")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}
	
	@WithMockUser(username = "admin")
	@Test
	public void itShouldReturnOk_WhenFindAll() throws Exception {
		
		when(this.categoriaService.findAll()).thenReturn(
				Arrays.asList(new Categoria(1L, "Esporte"), new Categoria(2L, "Futebol")));
		
		mockMvc.perform(get(BASE_URL)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void itShouldReturnOk_WhenFyndByDescricao() throws Exception {
		
		when(this.categoriaService.findByDescricaoContainingIgnoreCase("Esporte"))
			.thenReturn(Arrays.asList(new Categoria(1L, "Esporte")));
		
		mockMvc.perform(get(BASE_URL + "/search/Esporte")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void itShouldReturnCreated_WhenSave() throws Exception {
		
		mockMvc.perform(post(BASE_URL)
				.content(objectMapper.writeValueAsString(new Categoria(1L, "Esporte")))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
		
	}
	
	@Test
	public void itShouldReturnBadRequest_WhenSave() throws Exception {
		mockMvc.perform(post(BASE_URL)
				.content(objectMapper.writeValueAsString(new Categoria()))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
		
	}
	
	@Test
	public void itShouldReturnOk_WhenUpdate() throws Exception {
		
		mockMvc.perform(put(BASE_URL)
				.content(objectMapper.writeValueAsString(new Categoria(1L, "New name")))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void itShouldReturnNoContent_WhenDelete() throws Exception {		
		mockMvc.perform(delete(BASE_URL + "/1"))
				.andExpect(status().isNoContent());
	}
	
}