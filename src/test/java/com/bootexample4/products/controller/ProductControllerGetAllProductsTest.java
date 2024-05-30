package com.bootexample4.products.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doThrow;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.bootexample4.products.model.Product;
import com.bootexample4.products.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class ProductControllerGetAllProductsTest {

	@InjectMocks
	private ProductController productController;

	@Mock
	private ProductRepository productRepository;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetAllProductsReturnsAllProducts() {
		// Arrange
		Product product1 = new Product();
		Product product2 = new Product();
		List<Product> expectedProducts = Arrays.asList(product1, product2);
		when(productRepository.findAll()).thenReturn(expectedProducts);
		// Act
		List<Product> actualProducts = productController.getAllProducts();
		// Assert
		assertEquals(expectedProducts, actualProducts);
	}

	@Test
    public void testGetAllProductsReturnsEmptyListIfNoProducts() {
        // Arrange
        when(productRepository.findAll()).thenReturn(Collections.emptyList());
        // Act
        List<Product> actualProducts = productController.getAllProducts();
        // Assert
        assertEquals(Collections.emptyList(), actualProducts);
    }

	@Test
	public void testGetAllProductsHandlesRepositoryExceptions() {
		// Arrange
		doThrow(new RuntimeException()).when(productRepository).findAll();
		// Act & Assert
		assertThrows(RuntimeException.class, () -> productController.getAllProducts());
	}

}
