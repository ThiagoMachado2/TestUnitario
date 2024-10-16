package com.snack.repositories;

import com.snack.entities.Product;
import com.snack.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;


public class ProductServiceTest {
    private ProductService productService;
    private Product product;

    @BeforeEach
    public void setUp() {
        productService = new ProductService();
        product = new Product(1, "HotDog", 5.00f, "BancoDeImagem/HotDog.jpg");
    }

    @Test
    public void testarSalvarProdutoComImagemValida() {
        boolean resultado = productService.save(product);
        assertTrue(resultado, "Produto com imagem válida deveria ser salvo com sucesso.");
    }

    @Test
    public void testarSalvarProdutoComImagemInexistente() {
        product.setImage("BancoDeImagem/naoexiste.jpg");
        boolean resultado = productService.save(product);
        assertFalse(resultado, "Produto com imagem inexistente não deveria ser salvo.");
    }

    @Test
    public void testarAtualizarProdutoExistente() {
        productService.save(product);
        Product produtoAtualizado = new Product(1, "Hamburguer", 10.00f, "BancoDeImagem/Hamburguer.jpg");

        productService.update(produtoAtualizado);

        Path imagePath = Paths.get(productService.getImagePathById(1));
        assertTrue(Files.exists(imagePath), "A imagem do produto atualizado deveria existir.");
    }

    @Test
    public void testarRemoverProdutoExistente() {
        productService.save(product);
        productService.remove(product.getId());

        Path imagePath = Paths.get(productService.getImagePathById(product.getId()));
        assertFalse(Files.exists(imagePath), "A imagem do produto removido não deveria existir.");
    }

    @Test
    public void testarObterCaminhoDaImagemPorId() {
        productService.save(product);
        String caminhoImagem = productService.getImagePathById(product.getId());

        assertNotNull(caminhoImagem, "O caminho da imagem não deveria ser nulo.");
        assertTrue(caminhoImagem.contains(String.valueOf(product.getId())), "O caminho deveria conter o ID do produto.");
    }
}
