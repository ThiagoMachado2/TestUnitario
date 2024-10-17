package com.snack.repositories;

import com.snack.applications.ProductApplication;
import com.snack.entities.Product;
import com.snack.facade.ProductFacade;
import com.snack.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.snack.App.*;
import static org.junit.jupiter.api.Assertions.*;

public class ProductFacedeTest {

    private ProductRepository productRepository;
    private ProductService productService;
    private ProductFacade productFacade;
    private ProductApplication productApplication;
    private Product product1;
    private Product product2;


    @BeforeEach
    public void setUp() {
        productRepository = new ProductRepository();
        productService = new ProductService();
        productApplication = new ProductApplication(productRepository, productService);
        productFacade = new ProductFacade(productApplication);

        product1 = new Product(1, "Hotdog", 5.00f, "HotDog.jpg");
        product2 = new Product(2, "Hamburger", 10.00f, "Hamburguer.jpg");

        productFacade.append(product1);
        productFacade.append(product2);
    }

    @Test
    void testarGetAllRetornaListaCompletaDeProdutos() {
        // Act
        List<Product> produtos = productFacade.getAll();

        // Assert
        assertEquals(2, produtos.size());
        assertEquals("Hotdog", produtos.get(0).getDescription());
        assertEquals("Hamburger", produtos.get(1).getDescription());
    }

    @Test
    void testarGetByIdComIdValidoRetornaProdutoCorreto() {
        // Act
        Product produtoRecuperado = productFacade.getById(1);

        // Assert
        assertNotNull(produtoRecuperado);
        assertEquals(1, produtoRecuperado.getId());
        assertEquals("Hotdog", produtoRecuperado.getDescription());
    }

    @Test
    void testarExistsParaIdExistenteERetornaCorreto() {
        // Act
        boolean existeProduto = productFacade.exists(1);
        boolean naoExisteProduto = productFacade.exists(99);

        // Assert
        assertTrue(existeProduto);
        assertFalse(naoExisteProduto);
    }

    @Test
    void testarAppendAdicionaProdutoCorretamente() {
        // Arrange
        Product newProduct = new Product(3, "Pizza", 15.00f, "BancoDeImagem/Pizza.jpg");

        // Act
        productFacade.append(newProduct);

        // Assert
        Product produtoAdicionado = productFacade.getById(3);
        assertNotNull(produtoAdicionado);
        assertEquals("Pizza", produtoAdicionado.getDescription());
        assertEquals(15.00f, produtoAdicionado.getPrice());
    }

    @Test
    void testarRemoveProdutoComIdValido() {
        // Act
        productFacade.remove(1);

        // Assert
        assertFalse(productFacade.exists(1));
    }
}

