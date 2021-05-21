package bookshelf.models.services;

import bookshelf.models.entities.Product_type;
import bookshelf.models.repository.Product_typeRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class Product_typeServiceTest {

    @Mock
    private Product_typeRepo mockProduct_typeRepo;

    private Product_typeService product_typeServiceUnderTest;

    @BeforeEach
    void setUp() {
        product_typeServiceUnderTest = new Product_typeService(mockProduct_typeRepo);
    }

    @Test
    void testFindAll() {
        // Setup
        when(mockProduct_typeRepo.findAll()).thenReturn(List.of(new Product_type(0L, "name")));

        // Run the test
        final List<Product_type> result = product_typeServiceUnderTest.findAll();

        // Verify the results
    }

    @Test
    void testFindAll_Product_typeRepoReturnsNoItems() {
        // Setup
        when(mockProduct_typeRepo.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<Product_type> result = product_typeServiceUnderTest.findAll();

        // Verify the results
    }
}
