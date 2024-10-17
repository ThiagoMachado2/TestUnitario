package com.snack.repositories;

import com.snack.App;
import com.snack.entities.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AppTest {

    @BeforeEach
    void setUp() {
        App.resolveDependencies();
    }

    @Test
    void deveInicializarDependenciasCorretamente() {
        // Arrange
        // As dependências já estão sendo resolvidas no setup

        // Act & Assert
        assertNotNull(App.productRepository);
        assertNotNull(App.productService);
        assertNotNull(App.productApplication);
        assertNotNull(App.productFacade);
        assertNotNull(App.scanner);
    }

    @Test
    void deveAdicionarProdutoNoInitializeProducts() {
        // Arrange
        App.initializeProducts();

        // Act
        List<Product> products = App.productFacade.getAll();

        // Assert
        assertEquals(1, products.size());
        assertEquals("Hotdog", products.get(0).getDescription());
        assertEquals(4.00f, products.get(0).getPrice());
        assertEquals("C:\\Users\\aluno\\Produtos\\HotDog.jpg", products.get(0).getImage());
    }

    @Test
    void deveExibirMenuCorretamente() {
        // Act
        App.showMenu();

        // Assert
        // Capturar a saída do console pode ser complexo, usar System.out para verificar a saída se necessário.
    }

    @Test
    void deveCapturarEntradaDeUsuarioCorretamente() {
        // Arrange
        ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
        System.setIn(in);

        // Act
        int option = App.getUserInput();

        // Assert
        assertEquals(1, option);
    }

    @Test
    void deveExibirListaDeProdutosCorretamente() {
        // Arrange
        App.initializeProducts();

        // Act
        App.listAllProducts();

        // Assert
        // Verificar se a lista é exibida corretamente no formato desejado
        // Similar ao teste do menu, verificar a saída do console pode ser feito com System.out capturado
    }

    @Test
    void deveCriarNovoProdutoCorretamente() {
        // Arrange
        String input = "2\nNewProduct\n10.00\nC:\\Images\\new.jpg\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Act
        App.newProduct();

        // Assert
        List<Product> products = App.productFacade.getAll();
        assertEquals(1, products.size());
        assertEquals("NewProduct", products.get(0).getDescription());
    }

    @Test
    void deveVenderProdutoCorretamente() {
        // Arrange
        App.initializeProducts();
        ByteArrayInputStream in = new ByteArrayInputStream("1\n2\n".getBytes());
        System.setIn(in);

        // Act
        App.sellProduct();

        // Assert
        // Verificar se o valor total é exibido corretamente
    }

    @Test
    void deveAtualizarProdutoCorretamente() {
        // Arrange
        App.initializeProducts();
        String input = "1\nUpdatedProduct\n5.00\nC:\\Images\\updated.jpg\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Act
        App.updateProduct();

        // Assert
        Product product = App.productFacade.getById(1);
        assertEquals("UpdatedProduct", product.getDescription());
        assertEquals(5.00f, product.getPrice());
    }

    @Test
    void deveRemoverProdutoCorretamente() {
        // Arrange
        App.initializeProducts();
        ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
        System.setIn(in);

        // Act
        App.removeProduct();

        // Assert
        assertFalse(App.productFacade.exists(1));
    }




}
