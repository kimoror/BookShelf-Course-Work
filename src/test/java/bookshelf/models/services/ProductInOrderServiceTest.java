package bookshelf.models.services;

import bookshelf.models.entities.Maker;
import bookshelf.models.entities.Product;
import bookshelf.models.entities.ProductInOrder;
import bookshelf.models.entities.Product_type;
import bookshelf.models.repository.ProductInOrderRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductInOrderServiceTest {

    @Mock
    private ProductInOrderRepo mockProductInOrderRepo;

    private ProductInOrderService productInOrderServiceUnderTest;

    @BeforeEach
    void setUp() {
        productInOrderServiceUnderTest = new ProductInOrderService(mockProductInOrderRepo);
    }

    @Test
    void testSave() {
        // Setup
        final ProductInOrder productInOrder = new ProductInOrder(0L, 0L, 0L, 0);

        // Configure ProductInOrderRepo.findAllByOrder_idAndProduct_Id(...).
        final List<ProductInOrder> productInOrders = List.of(new ProductInOrder(0L, 0L, 0L, 0));
        when(mockProductInOrderRepo.findAllByOrder_idAndProduct_Id(0L, 0L)).thenReturn(productInOrders);

        when(mockProductInOrderRepo.save(new ProductInOrder(0L, 0L, 0L, 0))).thenReturn(new ProductInOrder(0L, 0L, 0L, 0));

        // Run the test
        productInOrderServiceUnderTest.save(productInOrder);

        // Verify the results
        verify(mockProductInOrderRepo).numOfProductInc(0L, 0L);
    }

    @Test
    void testSave_ProductInOrderRepoFindAllByOrder_idAndProduct_IdReturnsNoItems() {
        // Setup
        final ProductInOrder productInOrder = new ProductInOrder(0L, 0L, 0L, 0);
        when(mockProductInOrderRepo.findAllByOrder_idAndProduct_Id(0L, 0L)).thenReturn(Collections.emptyList());
        when(mockProductInOrderRepo.save(new ProductInOrder(0L, 0L, 0L, 0))).thenReturn(new ProductInOrder(0L, 0L, 0L, 0));

        // Run the test
        productInOrderServiceUnderTest.save(productInOrder);

        // Verify the results
        verify(mockProductInOrderRepo).numOfProductInc(0L, 0L);
    }

    @Test
    void testFindAll() {
        // Setup
        final List<ProductInOrder> expectedResult = List.of(new ProductInOrder(0L, 0L, 0L, 0));

        // Configure ProductInOrderRepo.findAll(...).
        final List<ProductInOrder> productInOrders = List.of(new ProductInOrder(0L, 0L, 0L, 0));
        when(mockProductInOrderRepo.findAll()).thenReturn(productInOrders);

        // Run the test
        final List<ProductInOrder> result = productInOrderServiceUnderTest.findAll();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testFindAll_ProductInOrderRepoReturnsNoItems() {
        // Setup
        final List<ProductInOrder> expectedResult = List.of(new ProductInOrder(0L, 0L, 0L, 0));
        when(mockProductInOrderRepo.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<ProductInOrder> result = productInOrderServiceUnderTest.findAll();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testExistByOrder_idAndProduct_Id() {
        // Setup

        // Configure ProductInOrderRepo.findAllByOrder_idAndProduct_Id(...).
        final List<ProductInOrder> productInOrders = List.of(new ProductInOrder(0L, 0L, 0L, 0));
        when(mockProductInOrderRepo.findAllByOrder_idAndProduct_Id(0L, 0L)).thenReturn(productInOrders);

        // Run the test
        final Boolean result = productInOrderServiceUnderTest.existByOrder_idAndProduct_Id(0L, 0L);

        // Verify the results
        assertTrue(result);
    }

    @Test
    void testExistByOrder_idAndProduct_Id_ProductInOrderRepoReturnsNoItems() {
        // Setup
        when(mockProductInOrderRepo.findAllByOrder_idAndProduct_Id(0L, 0L)).thenReturn(Collections.emptyList());

        // Run the test
        final Boolean result = productInOrderServiceUnderTest.existByOrder_idAndProduct_Id(0L, 0L);

        // Verify the results
        assertTrue(result);
    }

    @Test
    void testGetAllProduct_idByOrderId() {
        // Setup
        when(mockProductInOrderRepo.getAllProduct_idByOrderId(0L)).thenReturn(List.of(0L));

        // Run the test
        final List<Long> result = productInOrderServiceUnderTest.getAllProduct_idByOrderId(0L);

        // Verify the results
        assertEquals(List.of(0L), result);
    }

    @Test
    void testGetAllProduct_idByOrderId_ProductInOrderRepoReturnsNoItems() {
        // Setup
        when(mockProductInOrderRepo.getAllProduct_idByOrderId(0L)).thenReturn(Collections.emptyList());

        // Run the test
        final List<Long> result = productInOrderServiceUnderTest.getAllProduct_idByOrderId(0L);

        // Verify the results
        assertEquals(List.of(0L), result);
    }

    @Test
    void testGetProductsByOrder() {
        // Setup

        // Configure ProductInOrderRepo.getProductsByOrder(...).
        final List<Product> productList = List.of(new Product(0L, "name", 0, "img_path", "description", "short_description", new Maker(0L, "name"), new Product_type(0L, "name")));
        when(mockProductInOrderRepo.getProductsByOrder(0L)).thenReturn(productList);

        when(mockProductInOrderRepo.getNumOfProductsByOrder_idAAndProduct_id(0L, 0L)).thenReturn(0);

        // Run the test
        final Map<Product, Integer> result = productInOrderServiceUnderTest.getProductsByOrder(0L);

        // Verify the results
    }

    @Test
    void testGetProductsByOrder_ProductInOrderRepoGetProductsByOrderReturnsNoItems() {
        // Setup
        when(mockProductInOrderRepo.getProductsByOrder(0L)).thenReturn(Collections.emptyList());
        when(mockProductInOrderRepo.getNumOfProductsByOrder_idAAndProduct_id(0L, 0L)).thenReturn(0);

        // Run the test
        final Map<Product, Integer> result = productInOrderServiceUnderTest.getProductsByOrder(0L);

        // Verify the results
    }

    @Test
    void testRemoveProductFromOrder() {
        // Setup

        // Run the test
        productInOrderServiceUnderTest.removeProductFromOrder(0L, 0L);

        // Verify the results
        verify(mockProductInOrderRepo).removeByOrder_idAndProduct_id(0L, 0L);
    }

    @Test
    void testNumOfProductDesc() {
        // Setup
        when(mockProductInOrderRepo.getNumOfProductsByOrder_idAAndProduct_id(0L, 0L)).thenReturn(0);

        // Run the test
        productInOrderServiceUnderTest.numOfProductDesc(0L, 0L);

        // Verify the results
        verify(mockProductInOrderRepo).numOfProductDesc(0L, 0L);
        verify(mockProductInOrderRepo).removeByOrder_idAndProduct_id(0L, 0L);
    }

    @Test
    void testDeleteProductsFromCanceledOrder() {
        // Setup

        // Run the test
        productInOrderServiceUnderTest.deleteProductsFromCanceledOrder(0L);

        // Verify the results
        verify(mockProductInOrderRepo).deleteProductsFromCanceledOrder(0L);
    }
}
