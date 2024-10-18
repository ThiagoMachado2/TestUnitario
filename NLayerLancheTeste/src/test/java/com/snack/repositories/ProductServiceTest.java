package com.snack.repositories;

import com.snack.entities.Product;
import com.snack.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;


public class ProductServiceTest {
    private ProductService productService;
    private Product product;

    @BeforeEach
    public void setUp() {
        productService = new ProductService();
        product = new Product(1, "HotDog", 5.00f, "C:\\Users\\thiag\\OneDrive\\Área de Trabalho\\GitHub\\TestUnitario\\NLayerLancheTeste\\src\\Produtos\\HotDog.jpg");
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
        assertEquals("C:\\Users\\thiag\\OneDrive\\Área de Trabalho\\GitHub\\TestUnitario\\NLayerLancheTeste\\src\\BancoDeImagem\\1.jpg", product.getImage());
    }

    @Test
    void testarSalvarProdutoComImagemInexistente() {
        // Arrange
        ProductService service = new ProductService();
        Product product = new Product(1, "HotDog", 10.0f, "");

        // Act
        boolean resultado = service.save(product);

        // Assert
        assertFalse(resultado);
    }

    @Test
    void testarAtualizarProdutoExistente() {
        // Arrange
        ProductService service = new ProductService();
        Product product = new Product(2, "Hamburguer", 10.0f, "C:\\Users\\thiag\\OneDrive\\Área de Trabalho\\GitHub\\TestUnitario\\NLayerLancheTeste\\src\\Produtos\\Hamburguer.jpg");

        Product updatedProduct = new Product(1, "HotDog2", 20.0f, "C:\\Users\\thiag\\OneDrive\\Área de Trabalho\\GitHub\\TestUnitario\\NLayerLancheTeste\\src\\Produtos\\HotDog.jpg");

        // Act
        service.update(updatedProduct);

        // Assert
        assertEquals("HotDog2", updatedProduct.getDescription());
        assertEquals(20.0f, updatedProduct.getPrice());
        assertEquals("C:\\Users\\thiag\\OneDrive\\Área de Trabalho\\GitHub\\TestUnitario\\NLayerLancheTeste\\src\\BancoDeImagem\\1.jpg", updatedProduct.getImage());
    }


    @Test
    void testarRemoverProdutoExistente() {
        // Arrange
        ProductService service = new ProductService();
        Product product = new Product(3, "Pizza Teste", 10.0f, "C:\\Users\\thiag\\OneDrive\\Área de Trabalho\\GitHub\\TestUnitario\\NLayerLancheTeste\\src\\Produtos\\pizza.jpg");
        service.save(product);

        // Act
        service.remove(1);

        // Assert
        assertNull(service.getImagePathById(1));
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
