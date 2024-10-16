package com.snack.repositories;

import com.snack.entities.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.NoSuchElementException;

public class ProductRepositoryTest {

    private ProductRepository productRepository;
    private Product product1, product2;

    @BeforeEach
    public void setUp() {
        productRepository = new ProductRepository();
        product1 = new Product(1, "Hotdog", 5.00f, "BancoDeImagem/HotDog.jpg");
        product2 = new Product(2, "Hamburger", 10.00f, "BancoDeImagem/Hamburguer.jpg");
        productRepository.append(product1);
    }

    @Test
    public void testarAdicionarProdutoCorretamente() {
        productRepository.append(product2);
        assertEquals(2, productRepository.getAll().size());
    }

    @Test
    public void testarRecuperarProdutoPorId() {
        Product product = productRepository.getById(1);
        assertEquals(product1.getDescription(), product.getDescription());
    }

    @Test
    public void testarProdutoExisteNoRepositorio() {
        assertTrue(productRepository.exists(1));
        assertFalse(productRepository.exists(99));
    }

    @Test
    public void testarRemoverProdutoCorretamente() {
        productRepository.remove(1);
        assertFalse(productRepository.exists(1));
    }

    @Test
    public void testarAtualizarProdutoCorretamente() {
        product1.setDescription("Updated Hotdog");
        productRepository.update(1, product1);
        assertEquals("Updated Hotdog", productRepository.getById(1).getDescription());
    }

    @Test
    public void testarRecuperarTodosOsProdutos() {
        List<Product> products = productRepository.getAll();
        assertEquals(1, products.size());
    }

    @Test
    public void testarRemoverProdutoInexistente() {
        productRepository.remove(99);
        assertEquals(1, productRepository.getAll().size());
    }

    @Test
    public void testarAtualizarProdutoInexistente() {
        Product product = new Product(99, "NaoExiste", 10.0f, "BancoDeImagem/naoexiste.jpg");
        assertThrows(NoSuchElementException.class, () -> productRepository.update(99, product));
    }

    @Test
    public void testarAdicionarProdutoComIdDuplicado() {
        productRepository.append(new Product(1, "Duplicado", 4.00f, "BancoDeImagem/duplicado.jpg"));
        assertEquals(2, productRepository.getAll().size());
    }

    @Test
    public void testarListaVaziaAoInicializar() {
        ProductRepository emptyRepo = new ProductRepository();
        assertTrue(emptyRepo.getAll().isEmpty());
    }
}
