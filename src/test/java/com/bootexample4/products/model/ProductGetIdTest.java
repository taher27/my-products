package com.bootexample4.products.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.bootexample4.products.model.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class ProductGetIdTest {

	@Test
	public void testGetIdWhenIdIsNull() {
		// Arrange
		Product product = new Product();
		// Act
		Long id = product.getId();
		// Assert
		assertNull(id, "Id should be null when not set.");
	}

	@Test
	public void testGetIdWhenIdIsSet() {
		// Arrange
		Product product = new Product();
		Long expectedId = 123L;
		product.setId(expectedId);

		// Act
		Long actualId = product.getId();
		// Assert
		assertEquals(expectedId, actualId, "The returned id should be the same as the set id.");
	}

	@Test
	public void testGetIdWhenIdIsLarge() {
		// Arrange
		Product product = new Product();
		Long expectedId = Long.MAX_VALUE;
		product.setId(expectedId);

		// Act
		Long actualId = product.getId();
		// Assert
		assertEquals(expectedId, actualId,
				"The returned id should be the same as the set id, even when the id is large.");
	}

}