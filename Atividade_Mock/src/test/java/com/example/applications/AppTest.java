package com.example.applications;

import com.example.entities.Product;
import com.example.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AppTest {
    @InjectMocks
    private ProductApplication productApplication;

    @Mock
    private ProductService productService;


    @BeforeEach
    void setUp(){
//       Mockito.when(product.getId()).thenReturn(1);
//       Mockito.when(product.getDescription()).thenReturn("Hamburguer");
//       Mockito.when(product.getPrice()).thenReturn(18f);
//       Mockito.when(product.getImage()).thenReturn("src/main/java/com/example/Produto");
//       Mockito.when(productService.save(product)).thenReturn(true);
    }

    @Test
    public void testSalvarImagemCorretamente() {
        Product product = any(Product.class);

        productApplication.append(product);

        Mockito.verify(productService, times(1)).save(product);

    }

    @Test
    public void testRemoverImagemCorretamente() {
        Product product = any(Product.class);

        productApplication.remove(1);

        Mockito.verify(productService,times(1)).remove(1);

    }

    @Test
    public void testAtualizarImagemCorretamente() {
        Product product = any(Product.class);

        productApplication.update(3, product);

        Mockito.verify(productService,times(1)).update(product);
    }

}