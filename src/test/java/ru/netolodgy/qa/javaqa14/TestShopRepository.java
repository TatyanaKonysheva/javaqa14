package ru.netolodgy.qa.javaqa14;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestShopRepository {
    private ShopRepository shopRepository;

    @BeforeEach
    public void setup() {
        shopRepository = new ShopRepository();
        shopRepository.add(new Product(11, "Конфета", 100));
        shopRepository.add(new Product(12, "Шоколад", 200));
        shopRepository.add(new Product(13, "Мороженое", 300));
    }

    @Test
    public void testRemoveExistingProduct() {
        int idToRemove = 11;

        shopRepository.removeById(idToRemove);
        Product[] products = shopRepository.findAll();

        Assertions.assertEquals(2, products.length);
        Product[] expectedProducts = {new Product(12, "Шоколад", 200), new Product(13, "Мороженое", 300)};
        Assertions.assertArrayEquals(expectedProducts, products);
    }

    @Test
    public void testRemoveNonExistingProduct() {
        int nonExistingProductId = 1;

        Assertions.assertThrows(NotFoundException.class, () -> shopRepository.removeById(nonExistingProductId));
    }

    @Test
    public void shouldAddNewProduct() {
        Product product = new Product(44, "Булка", 400);
        shopRepository.add(product);

        Product[] products = shopRepository.findAll();
        Assertions.assertEquals(4, products.length);
        Product[] expectedProducts = {
                new Product(11, "Конфета", 100),
                new Product(12, "Шоколад", 200),
                new Product(13, "Мороженое", 300),
                new Product(44, "Булка", 400)
        };
        Assertions.assertArrayEquals(expectedProducts, products);
    }

    @Test
    public void testAlreadyExistsException() {
        Product alreadyExists = new Product(11, "Конфета", 100);

        Assertions.assertThrows(AlreadyExistsException.class, () -> shopRepository.add(alreadyExists));
    }
}
