package com.diego.pedidosspring.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.diego.pedidosspring.PedidosSpringApplication;
import com.diego.pedidosspring.controllers.ProdutoController;
import com.diego.pedidosspring.controllers.exceptions.ControllerExceptionHandler;
import com.diego.pedidosspring.model.Categoria;
import com.diego.pedidosspring.model.Produto;
import com.diego.pedidosspring.security.JWTUtil;
import com.diego.pedidosspring.services.CategoriaService;
import com.diego.pedidosspring.services.ProdutoService;
import com.diego.pedidosspring.services.exceptions.ObjectNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WithMockUser(username = "admin")
@WebMvcTest(ProdutoController.class)
@ContextConfiguration(classes = {
		PedidosSpringApplication.class,
		JWTUtil.class
})
public class ProdutoControllerTest {

	private final String BASE_URL = "/produtos";
	private ObjectMapper objectMapper;
	private Produto produto;
	private Categoria categoria;
	private List<Produto> produtos;
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ProdutoController produtoController;
	@MockBean
	private ProdutoService produtoService;
	@MockBean
	private CategoriaService categoriaService;
	
	@BeforeEach
	public void setUp() {
		objectMapper = new ObjectMapper();
		categoria = new Categoria(1L, "Esporte");
		produto = new Produto("001", "Camisa", 90.00, categoria);
		produtos = Arrays.asList(produto);
		mockMvc = MockMvcBuilders.standaloneSetup(produtoController)
				.setControllerAdvice(new ControllerExceptionHandler()).build();
	}
	
	@Test
	public void itShouldReturnOk_WhenFindById() throws Exception {
		when(this.produtoService.findById(1L)).thenReturn(produto);
		
		mockMvc.perform(get(BASE_URL + "/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.descricao", is("Camisa")));
	}
	
	@Test
	public void itShouldReturnNotFound_WhenFindById() throws Exception {
		when(this.produtoService.findById(2L)).thenThrow(new ObjectNotFoundException("Not Found!"));
		
		mockMvc.perform(get("/2")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}
	
	@Test
	public void itShouldReturnOk_WhenFindAll() throws Exception {
		
		when(this.produtoService.findAll()).thenReturn(produtos);
		
		mockMvc.perform(get(BASE_URL)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void itShouldReturnOk_WhenFyndByDescricao() throws Exception {
		
		when(this.produtoService.findByDescricaoContainsIgnoreCaseOrCodigo("Camisa", null)).thenReturn(produtos);
		
		mockMvc.perform(get(BASE_URL + "/search/Camisa")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void itShouldReturnCreated_WhenSave() throws Exception {
		
		mockMvc.perform(post(BASE_URL)
				.content(objectMapper.writeValueAsString(produto))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
		
	}
	
	@Test
	public void itShouldReturnOk_WhenUpdate() throws Exception {
		
		mockMvc.perform(put(BASE_URL)
				.content(objectMapper.writeValueAsString(new Produto(1L, "001", "New name", 40.00, categoria)))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void itShouldReturnNoContent_WhenDelete() throws Exception {		
		mockMvc.perform(delete(BASE_URL + "/5"))
				.andExpect(status().isNoContent());
	}
}
