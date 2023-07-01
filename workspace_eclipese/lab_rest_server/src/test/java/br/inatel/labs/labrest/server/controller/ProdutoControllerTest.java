package br.inatel.labs.labrest.server.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import br.inatel.labs.labrest.server.model.Produto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProdutoControllerTest {
	
	@Autowired
	private WebTestClient webTestClient;

	@Test
	void deveListarProdutos() {
		
		webTestClient.get().uri("/produto").exchange().expectStatus().isOk().expectBody().returnResult();
		
	}
	
	@Test
	void dadoProdutoIdValido_quandoGetProdutoPeloId_entaoRespondeComProdutoValido() {
		
		Long produtoIdValido = 1L;
		
		Produto produtoRespondido = webTestClient.get().uri("/produto/" + produtoIdValido).exchange().expectStatus().isOk().expectBody(Produto.class).returnResult().getResponseBody();
		
		assertNotNull(produtoRespondido);
		assertEquals(produtoRespondido.getId(), produtoIdValido);
		
	}
	
	@Test
	void dadoProdutoIdInvalido_quandoGetProdutoPeloId_entaoRespondeComStatusNotFound() {
		
		Long idInvalido = 99L;
		
		webTestClient.get().uri("/produto/" + idInvalido).exchange().expectStatus();
		
	}

}
