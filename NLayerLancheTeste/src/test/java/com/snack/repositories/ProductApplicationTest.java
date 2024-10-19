package com.snack.repositories;

import com.snack.applications.ProductApplication;
import com.snack.entities.Product;
import com.snack.services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class ProductApplicationTest {

    private ProductRepository productRepository;
    private ProductService productService;
    private ProductApplication productApplication;
    private Product product1;
    private Product product2;

    @BeforeEach
    public void setUp() {
        // Inicializa o repositório de produtos
        productRepository = new ProductRepository();
        productService = new ProductService();
        productApplication = new ProductApplication(productRepository, productService);

        // Cria dois produtos de exemplo
        product1 = new Product(1, "Hotdog", 5.00f, "C:\\Users\\thiag\\OneDrive\\Área de Trabalho\\GitHub\\TestUnitario\\NLayerLancheTeste\\src\\Produtos\\HotDog.jpg");
        product2 = new Product(2, "Hamburguer", 10.00f, "C:\\Users\\thiag\\OneDrive\\Área de Trabalho\\GitHub\\TestUnitario\\NLayerLancheTeste\\src\\Produtos\\Hamburguer.jpg");

        // Adiciona os produtos ao repositório
        productRepository.append(product1);
        productRepository.append(product2);
    }

    @Test
    void testarObterProdutoPorIdValido() {
        // Act
        Product produtoRecuperado = productApplication.getById(1);

        // Assert
        assertNotNull(produtoRecuperado);
        assertEquals("Hotdog", produtoRecuperado.getDescription());
    }

    @Test
    void testarObterProdutoPorIdInvalido() {
        // Assert e Act
        assertThrows(NoSuchElementException.class, () -> {productApplication.getById(99);
        });
    }

    @Test
    void testarListarTodosOsProdutos() {
        // Act
        List<Product> produtos = productApplication.getAll();

        // Assert
        assertEquals(2, produtos.size());
        assertEquals("Hotdog", produtos.get(0).getDescription());
        assertEquals("Hamburguer", produtos.get(1).getDescription());
    }

    @Test
    void testarAdicionarProduto() {
        // Arrange
        Product novoProduto = new Product(3, "Pizza", 15.00f, "C:\\Users\\thiag\\OneDrive\\Área de Trabalho\\GitHub\\TestUnitario\\NLayerLancheTeste\\src\\Produtos\\Hamburguer.jpg");

        // Act
        productApplication.append(novoProduto);
        List<Product> produtos = productApplication.getAll();

        // Assert
        assertEquals(3, produtos.size());
        assertEquals("Pizza", produtos.get(2).getDescription());
    }

    @Test
    void testarRemoverProdutoExistente() {
        // Act
        productApplication.remove(1);
        List<Product> produtos = productApplication.getAll();

        // Assert
        assertEquals(1, produtos.size());
        assertEquals("Hamburguer", produtos.get(0).getDescription());
    }

    @Test
    void testarRemoverProdutoInexistente() {
        // Assert e Act
        assertThrows(NoSuchElementException.class, () -> {productApplication.remove(99);
        });
    }

    @Test
    void testarAtualizarProduto() {
        // Arrange
        Product produtoOriginal = new Product(10, "HotDog", 10.4f, "C:\\Users\\thiag\\OneDrive\\Área de Trabalho\\GitHub\\TestUnitario\\NLayerLancheTeste\\src\\Produtos\\HotDog.jpg");
        productApplication.append(produtoOriginal);


        // Act
        Product produtoAtualizar = new Product(10, "Hamburguer", 10.4f, "C:\\Users\\thiag\\OneDrive\\Área de Trabalho\\GitHub\\TestUnitario\\NLayerLancheTeste\\src\\Produtos\\Hamburguer.jpg");
        productApplication.update(10, produtoAtualizar);
        String imagem = productService.getImagePathById(10);

        // Assert
        Assertions.assertTrue(imagem.contains("10.jpg"));
    }

    @Test
    void testarVendaDeProduto() {
        // Act
        float total = productApplication.sellProduct(2, 3);

        // Assert
        assertEquals(30.00f, total);
    }
}