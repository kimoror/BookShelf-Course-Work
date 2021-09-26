package bookshelf.models.services;

import bookshelf.exceptions.EntityNotFoundException;
import bookshelf.models.entities.Maker;
import bookshelf.models.entities.Product;
import bookshelf.models.entities.Product_type;
import bookshelf.models.repository.ProductRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepo mockProductRepo;

    private ProductService productServiceUnderTest;

    @BeforeEach
    void setUp() {
        productServiceUnderTest = new ProductService(mockProductRepo);
    }

    @Test
    void testFindAll() {

        // Configure ProductRepo.findAll(...).
        final List<Product> productList = List.of(new Product(0L, "name", 0, "img_path", "description", "short_description", new Maker(0L, "name"), new Product_type(0L, "name")));
        when(mockProductRepo.findAll()).thenReturn(productList);

        // Run the test
        final List<Product> result = productServiceUnderTest.findAll();
    }

    @Test
    void testFindAll_ProductRepoReturnsNoItems() {
        // Setup
        when(mockProductRepo.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<Product> result = productServiceUnderTest.findAll();

        // Verify the results
        verify(mockProductRepo).findAll();
    }

    @Test
    void testFindById() {
        // Configure ProductRepo.findById(...).
        final Optional<Product> product = Optional.of(new Product(0L, "name", 0, "img_path", "description", "short_description", new Maker(0L, "name"), new Product_type(0L, "name")));
        when(mockProductRepo.findById(0L)).thenReturn(product);

        // Run the test
        final Product result = productServiceUnderTest.findById(0L);

    }

    @Test
    void testFindByIdNotOk() {
        final String expectedMessage = "Product with id '0' not found";

        // Run the test
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> productServiceUnderTest.findById(0L));
        String message = exception.getMessage();
        // Verify the results
        assertEquals(message, expectedMessage);
    }

    @Test
    void testAddProduct() {
        // Setup
        final Product product = new Product(0L, "name", 0, "img_path", "description", "short_description", new Maker(0L, "name"), new Product_type(0L, "name"));

        // Configure ProductRepo.save(...).
        final Product product1 = new Product(0L, "name", 0, "img_path", "description", "short_description", new Maker(0L, "name"), new Product_type(0L, "name"));
        when(mockProductRepo.save(any(Product.class))).thenReturn(product1);

        // Run the test
        productServiceUnderTest.addProduct(product);

        // Verify the results
        verify(mockProductRepo).save(any(Product.class));
    }

    @Test
    void testGetProductByProduct_typeId() {
        // Configure ProductRepo.getProductByProduct_typeId(...).
        final List<Product> productList = List.of(new Product(0L, "name", 0, "img_path", "description", "short_description", new Maker(0L, "name"), new Product_type(0L, "name")));
        when(mockProductRepo.getProductByProduct_typeId(0L)).thenReturn(productList);

        // Run the test
        final List<Product> result = productServiceUnderTest.getProductByProduct_typeId(0L);

        // Verify the results
        verify(mockProductRepo).getProductByProduct_typeId(0L);
    }

    @Test
    void testGetProductByProduct_typeIdNotOk() {

        // Run the test
        when(mockProductRepo.getProductByProduct_typeId(0L)).thenReturn(Collections.emptyList());
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> productServiceUnderTest.getProductByProduct_typeId(0L));


        // Verify the results
        assertEquals(exception.getMessage(), "Product type with id '0' not found" );
    }

    @Test
    void testGetProductByProduct_typeIdAndOrderByCostAsc() {
        // Configure ProductRepo.getProductByProduct_typeIdAndOrderByCostAsc(...).
        final List<Product> productList = List.of(new Product(0L, "name", 0, "img_path", "description", "short_description", new Maker(0L, "name"), new Product_type(0L, "name")));
        when(mockProductRepo.getProductByProduct_typeIdAndOrderByCostAsc(0L)).thenReturn(productList);

        // Run the test
        final List<Product> result = productServiceUnderTest.getProductByProduct_typeIdAndOrderByCostAsc(0L);

        // Verify the result
        verify(mockProductRepo).getProductByProduct_typeIdAndOrderByCostAsc(0L);
    }

    @Test
    void testGetProductByProduct_typeIdAndOrderByCostDesc() {
        // Configure ProductRepo.getProductByProduct_typeIdAndOrderByCostDesc(...).
        final List<Product> productList = List.of(new Product(0L, "name", 0, "img_path", "description", "short_description", new Maker(0L, "name"), new Product_type(0L, "name")));
        when(mockProductRepo.getProductByProduct_typeIdAndOrderByCostDesc(0L)).thenReturn(productList);

        // Run the test
        final List<Product> result = productServiceUnderTest.getProductByProduct_typeIdAndOrderByCostDesc(0L);

        // Verify the results
        verify(mockProductRepo).getProductByProduct_typeIdAndOrderByCostDesc(0L);
    }

    @Test
    void testGetProductByProduct_typeIdAndOrderByNameDesc() {
        // Configure ProductRepo.getProductByProduct_typeIdAndOrderByNameDesc(...).
        final List<Product> productList = List.of(new Product(0L, "name", 0, "img_path", "description", "short_description", new Maker(0L, "name"), new Product_type(0L, "name")));
        when(mockProductRepo.getProductByProduct_typeIdAndOrderByNameDesc(0L)).thenReturn(productList);

        // Run the test
        final List<Product> result = productServiceUnderTest.getProductByProduct_typeIdAndOrderByNameDesc(0L);

        // Verify the results
        verify(mockProductRepo).getProductByProduct_typeIdAndOrderByNameDesc(0L);
    }

    @Test
    void testGetProductByProduct_typeIdAndOrderByNameAsc() {
        // Configure ProductRepo.getProductByProduct_typeIdAndOrderByNameAsc(...).
        final List<Product> productList = List.of(new Product(0L, "name", 0, "img_path", "description", "short_description", new Maker(0L, "name"), new Product_type(0L, "name")));
        when(mockProductRepo.getProductByProduct_typeIdAndOrderByNameAsc(0L)).thenReturn(productList);

        // Run the test
        final List<Product> result = productServiceUnderTest.getProductByProduct_typeIdAndOrderByNameAsc(0L);

        // Verify the results
        verify(mockProductRepo).getProductByProduct_typeIdAndOrderByNameAsc(0L);
    }

    @Test
    void testFindAllByIdList() {
        // Configure ProductRepo.findAll(...).
        final List<Product> productList = List.of(new Product(0L, "name", 0, "img_path", "description", "short_description", new Maker(0L, "name"), new Product_type(0L, "name")));
        when(mockProductRepo.findAll()).thenReturn(productList);

        // Run the test
        final List<Product> result = productServiceUnderTest.findAllByIdList(List.of(0L));

        // Verify the results
        assertEquals(result, productList);
    }

    @Test
    void testFindAllByIdList_ProductRepoReturnsNoItems() {
        // Setup
        when(mockProductRepo.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<Product> result = productServiceUnderTest.findAllByIdList(List.of(0L));

        // Verify the results
        assertEquals(result, Collections.emptyList());
    }
}
