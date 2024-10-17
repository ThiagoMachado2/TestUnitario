package com.snack.repositories;

import com.snack.entities.Product;
import com.snack.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;


public class ProductServiceTest {
    private ProductService productService;
    private Product product;

    @BeforeEach
    public void setUp() {
        productService = new ProductService();
        product = new Product(1, "HotDog", 5.00f, "HotDog.jpg");
    }

    @Test
    void testarSalvarProdutoComImagemValida() {
        // Arrange
        ProductService service = new ProductService();
        Product product = new Product(1, "teste", 10.0f, "src/test/resources/validImage.jpg");

        // Act
        boolean resultado = service.save(product);

        // Assert
        assertTrue(resultado);
        assertEquals("C:\\Users\\thiag\\Downloads\\NLayerLancheTeste\\bancodeimagem1.jpg", product.getImage());
    }

    @Test
    void testarSalvarProdutoComImagemInexistente() {
        // Arrange
        ProductService service = new ProductService();
        Product product = new Product(1, "teste", 10.0f, "naoexiste.png");

        // Act
        boolean resultado = service.save(product);

        // Assert
        assertFalse(resultado);
    }

    @Test
    void testarAtualizarProdutoExistente() {
        // Arrange
        ProductService service = new ProductService();
        Product product = new Product(1, "Hamburguer", 10.0f, "Hamburguer.jpg");

        Product updatedProduct = new Product(1, "HotDog2", 20.0f, "HotDog.jpg");

        // Act
        service.update(updatedProduct);

        // Assert
        assertEquals("Produto Atualizado", updatedProduct.getDescription());
        assertEquals(20.0f, updatedProduct.getPrice());
        assertEquals("bancodeimagem1.jpg", updatedProduct.getImage());
    }


    @Test
    void testarRemoverProdutoExistente() {
        // Arrange
        ProductService service = new ProductService();
        Product product = new Product(1, "Produto Teste", 10.0f, "validImage.jpg");
        service.save(product);

        // Act
        service.remove(1);

        // Assert
        assertThrows(NoSuchElementException.class, () -> service.getImagePathById(1));
    }

    @Test
    void testarObterCaminhoDaImagemPorId() {
        // Arrange
        ProductService service = new ProductService();
        Product product = new Product(1, "Produto Teste", 10.0f, "src/test/resources/validImage.jpg");
        service.save(product);

        // Act
        String imagePath = service.getImagePathById(1);

        // Assert
        assertEquals("C:\\Users\\thiag\\Downloads\\NLayerLancheTeste\\bancodeimagem1.jpg", imagePath);
    }
}
