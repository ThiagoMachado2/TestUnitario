package com.snack.repositories;

import com.snack.entities.Product;
import com.snack.services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;


public class ProductServiceTest {
    private ProductService productService;
    private Product product;

    @BeforeEach
    public void setUp() {
        productService = new ProductService();
        product = new Product(1, "HotDog", 5.00f, "C:\\Users\\thiag\\OneDrive\\Área de Trabalho\\GitHub\\TestUnitario\\NLayerLancheTeste\\src\\Produtos\\HotDog.jpg");
        productService.save(product);
    }

    @Test
    void testarSalvarProdutoComImagemValida() {
        // Arrange
        ProductService service = new ProductService();
        Product product = new Product(1, "teste", 10.0f, "C:\\Users\\thiag\\OneDrive\\Área de Trabalho\\GitHub\\TestUnitario\\NLayerLancheTeste\\src\\Produtos\\HotDog.jpg");

        // Act
        boolean resultado = service.save(product);

        // Assert
        assertTrue(resultado);
    }

    @Test
    void testarSalvarProdutoComImagemInexistente() {
        // Arrange
        ProductService service = new ProductService();
        Product product = new Product(1, "HotDog", 10.0f, "C:\\Users\\aluno\\Downloads\\TestUnitario\\TestUnitario\\NLayerLancheTeste\\src\\Produtos\\nada.jpg");

        // Act
        boolean resultado = service.save(product);

        // Assert
        assertFalse(resultado);
    }

    @Test
    void testarAtualizarProdutoExistente() {
        Product produtoOriginal = new Product(10,"HotDog",10.4f, "C:\\Users\\thiag\\OneDrive\\Área de Trabalho\\GitHub\\TestUnitario\\NLayerLancheTeste\\src\\Produtos\\HotDog.jpg");
        productService.save(produtoOriginal);

        Product produtoAtualizar = new Product(10,"Hamburguer",10.4f, "C:\\Users\\thiag\\OneDrive\\Área de Trabalho\\GitHub\\TestUnitario\\NLayerLancheTeste\\src\\Produtos\\Hamburguer.jpg");
        productService.update(produtoAtualizar);

        String imagem = productService.getImagePathById(10);

        Assertions.assertTrue(imagem.contains("10.jpg"));

    }


    @Test
    void testarRemoverProdutoExistente() {
        // Arrange
        ProductService service = new ProductService();
        Product product = new Product(3, "Pizza Teste", 10.0f, "C:\\Users\\thiag\\OneDrive\\Área de Trabalho\\GitHub\\TestUnitario\\NLayerLancheTeste\\src\\Produtos\\pizza.jpg");
        service.save(product);

        // Act
        service.remove(3);

        // Assert
        assertThrows(NoSuchElementException.class, ()-> service.getImagePathById(3));
    }

    @Test
    void testarObterCaminhoDaImagemPorId() {
        // Arrange
        ProductService service = new ProductService();
        Product product = new Product(2, "Hamburguer", 10.0f, "C:\\Users\\thiag\\OneDrive\\Área de Trabalho\\GitHub\\TestUnitario\\NLayerLancheTeste\\src\\Produtos\\Hamburguer.jpg");
        service.save(product);

        // Act
        String imagePath = service.getImagePathById(2);

        // Assert
        assertEquals("C:\\Users\\thiag\\OneDrive\\Área de Trabalho\\GitHub\\TestUnitario\\NLayerLancheTeste\\src\\BancoDeImagem\\2.jpg", imagePath);
    }
}