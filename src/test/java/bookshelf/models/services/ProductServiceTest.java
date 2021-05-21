package bookshelf.models.services;

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

import static org.mockito.ArgumentMatchers.any;
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
        // Setup

        // Configure ProductRepo.findAll(...).
        final List<Product> productList = List.of(new Product(0L, "name", 0, "img_path", "description", "short_description", new Maker(0L, "name"), new Product_type(0L, "name")));
        when(mockProductRepo.findAll()).thenReturn(productList);

        // Run the test
        final List<Product> result = productServiceUnderTest.findAll();

        // Verify the results
    }

    @Test
    void testFindAll_ProductRepoReturnsNoItems() {
        // Setup
        when(mockProductRepo.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<Product> result = productServiceUnderTest.findAll();

        // Verify the results
    }

    @Test
    void testFindById() {
        // Setup

        // Configure ProductRepo.findById(...).
        final Optional<Product> product = Optional.of(new Product(0L, "name", 0, "img_path", "description", "short_description", new Maker(0L, "name"), new Product_type(0L, "name")));
        when(mockProductRepo.findById(0L)).thenReturn(product);

        // Run the test
        final Product result = productServiceUnderTest.findById(0L);

        // Verify the results
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
    }

    @Test
    void testGetProductByProduct_typeId() {
        // Setup

        // Configure ProductRepo.getProductByProduct_typeId(...).
        final List<Product> productList = List.of(new Product(0L, "name", 0, "img_path", "description", "short_description", new Maker(0L, "name"), new Product_type(0L, "name")));
        when(mockProductRepo.getProductByProduct_typeId(0L)).thenReturn(productList);

        // Run the test
        final List<Product> result = productServiceUnderTest.getProductByProduct_typeId(0L);

        // Verify the results
    }

    @Test
    void testGetProductByProduct_typeIdAndOrderByCostAsc() {
        // Setup

        // Configure ProductRepo.getProductByProduct_typeIdAndOrderByCostAsc(...).
        final List<Product> productList = List.of(new Product(0L, "name", 0, "img_path", "description", "short_description", new Maker(0L, "name"), new Product_type(0L, "name")));
        when(mockProductRepo.getProductByProduct_typeIdAndOrderByCostAsc(0L)).thenReturn(productList);

        // Run the test
        final List<Product> result = productServiceUnderTest.getProductByProduct_typeIdAndOrderByCostAsc(0L);

        // Verify the results
    }

    @Test
    void testGetProductByProduct_typeIdAndOrderByCostDesc() {
        // Setup

        // Configure ProductRepo.getProductByProduct_typeIdAndOrderByCostDesc(...).
        final List<Product> productList = List.of(new Product(0L, "name", 0, "img_path", "description", "short_description", new Maker(0L, "name"), new Product_type(0L, "name")));
        when(mockProductRepo.getProductByProduct_typeIdAndOrderByCostDesc(0L)).thenReturn(productList);

        // Run the test
        final List<Product> result = productServiceUnderTest.getProductByProduct_typeIdAndOrderByCostDesc(0L);

        // Verify the results
    }

    @Test
    void testGetProductByProduct_typeIdAndOrderByNameDesc() {
        // Setup

        // Configure ProductRepo.getProductByProduct_typeIdAndOrderByNameDesc(...).
        final List<Product> productList = List.of(new Product(0L, "name", 0, "img_path", "description", "short_description", new Maker(0L, "name"), new Product_type(0L, "name")));
        when(mockProductRepo.getProductByProduct_typeIdAndOrderByNameDesc(0L)).thenReturn(productList);

        // Run the test
        final List<Product> result = productServiceUnderTest.getProductByProduct_typeIdAndOrderByNameDesc(0L);

        // Verify the results
    }

    @Test
    void testGetProductByProduct_typeIdAndOrderByNameAsc() {
        // Setup

        // Configure ProductRepo.getProductByProduct_typeIdAndOrderByNameAsc(...).
        final List<Product> productList = List.of(new Product(0L, "name", 0, "img_path", "description", "short_description", new Maker(0L, "name"), new Product_type(0L, "name")));
        when(mockProductRepo.getProductByProduct_typeIdAndOrderByNameAsc(0L)).thenReturn(productList);

        // Run the test
        final List<Product> result = productServiceUnderTest.getProductByProduct_typeIdAndOrderByNameAsc(0L);

        // Verify the results
    }

    @Test
    void testFindAllByIdList() {
        // Setup

        // Configure ProductRepo.findAll(...).
        final List<Product> productList = List.of(new Product(0L, "name", 0, "img_path", "description", "short_description", new Maker(0L, "name"), new Product_type(0L, "name")));
        when(mockProductRepo.findAll()).thenReturn(productList);

        // Run the test
        final List<Product> result = productServiceUnderTest.findAllByIdList(List.of(0L));

        // Verify the results
    }

    @Test
    void testFindAllByIdList_ProductRepoReturnsNoItems() {
        // Setup
        when(mockProductRepo.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<Product> result = productServiceUnderTest.findAllByIdList(List.of(0L));

        // Verify the results
    }
}
