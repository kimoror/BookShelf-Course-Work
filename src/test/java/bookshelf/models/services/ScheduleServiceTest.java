package bookshelf.models.services;

import bookshelf.models.entities.*;
import bookshelf.models.enums.OrderStatus;
import bookshelf.models.enums.Role;
import bookshelf.models.enums.UserStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ScheduleServiceTest {

    @Mock
    private MakerService mockMakerService;
    @Mock
    private OrderService mockOrderService;
    @Mock
    private Product_typeService mockProduct_typeService;
    @Mock
    private ProductService mockProductService;
    @Mock
    private ProductInOrderService mockProductInOrderService;
    @Mock
    private UserService mockUserService;

    private ScheduleService scheduleServiceUnderTest;

    @BeforeEach
    void setUp() {
        scheduleServiceUnderTest = new ScheduleService(mockMakerService, mockOrderService, mockProduct_typeService, mockProductService, mockProductInOrderService, mockUserService);
    }

    @Test
    void testStart() throws Exception {
        // Setup
        when(mockMakerService.findAll()).thenReturn(List.of(new Maker(0L, "name")));

        // Configure OrderService.findAll(...).
        final List<Order> orders = List.of(new Order(0L, new User(0L, "name", 0, "phone_number", "address", Role.USER, UserStatus.ACTIVE, "email", "password"), Date.valueOf(LocalDate.of(2020, 1, 1)), OrderStatus.ACTIVE));
        when(mockOrderService.findAll()).thenReturn(orders);

        when(mockProduct_typeService.findAll()).thenReturn(List.of(new Product_type(0L, "name")));

        // Configure ProductService.findAll(...).
        final List<Product> productList = List.of(new Product(0L, "name", 0, "img_path", "description", "short_description", new Maker(0L, "name"), new Product_type(0L, "name")));
        when(mockProductService.findAll()).thenReturn(productList);

        // Configure ProductInOrderService.findAll(...).
        final List<ProductInOrder> productInOrders = List.of(new ProductInOrder(0L, 0L, 0L, 0));
        when(mockProductInOrderService.findAll()).thenReturn(productInOrders);

        // Configure UserService.findAll(...).
        final List<User> users = List.of(new User(0L, "name", 0, "phone_number", "address", Role.USER, UserStatus.ACTIVE, "email", "password"));
        when(mockUserService.findAll()).thenReturn(users);

        // Run the test
        scheduleServiceUnderTest.start();

        // Verify the results
    }

    @Test
    void testStart_MakerServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockMakerService.findAll()).thenReturn(Collections.emptyList());

        // Configure OrderService.findAll(...).
        final List<Order> orders = List.of(new Order(0L, new User(0L, "name", 0, "phone_number", "address", Role.USER, UserStatus.ACTIVE, "email", "password"), Date.valueOf(LocalDate.of(2020, 1, 1)), OrderStatus.ACTIVE));
        when(mockOrderService.findAll()).thenReturn(orders);

        when(mockProduct_typeService.findAll()).thenReturn(List.of(new Product_type(0L, "name")));

        // Configure ProductService.findAll(...).
        final List<Product> productList = List.of(new Product(0L, "name", 0, "img_path", "description", "short_description", new Maker(0L, "name"), new Product_type(0L, "name")));
        when(mockProductService.findAll()).thenReturn(productList);

        // Configure ProductInOrderService.findAll(...).
        final List<ProductInOrder> productInOrders = List.of(new ProductInOrder(0L, 0L, 0L, 0));
        when(mockProductInOrderService.findAll()).thenReturn(productInOrders);

        // Configure UserService.findAll(...).
        final List<User> users = List.of(new User(0L, "name", 0, "phone_number", "address", Role.USER, UserStatus.ACTIVE, "email", "password"));
        when(mockUserService.findAll()).thenReturn(users);

        // Run the test
        scheduleServiceUnderTest.start();

        // Verify the results
    }

    @Test
    void testStart_OrderServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockMakerService.findAll()).thenReturn(List.of(new Maker(0L, "name")));
        when(mockOrderService.findAll()).thenReturn(Collections.emptyList());
        when(mockProduct_typeService.findAll()).thenReturn(List.of(new Product_type(0L, "name")));

        // Configure ProductService.findAll(...).
        final List<Product> productList = List.of(new Product(0L, "name", 0, "img_path", "description", "short_description", new Maker(0L, "name"), new Product_type(0L, "name")));
        when(mockProductService.findAll()).thenReturn(productList);

        // Configure ProductInOrderService.findAll(...).
        final List<ProductInOrder> productInOrders = List.of(new ProductInOrder(0L, 0L, 0L, 0));
        when(mockProductInOrderService.findAll()).thenReturn(productInOrders);

        // Configure UserService.findAll(...).
        final List<User> users = List.of(new User(0L, "name", 0, "phone_number", "address", Role.USER, UserStatus.ACTIVE, "email", "password"));
        when(mockUserService.findAll()).thenReturn(users);

        // Run the test
        scheduleServiceUnderTest.start();

        // Verify the results
    }

    @Test
    void testStart_Product_typeServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockMakerService.findAll()).thenReturn(List.of(new Maker(0L, "name")));

        // Configure OrderService.findAll(...).
        final List<Order> orders = List.of(new Order(0L, new User(0L, "name", 0, "phone_number", "address", Role.USER, UserStatus.ACTIVE, "email", "password"), Date.valueOf(LocalDate.of(2020, 1, 1)), OrderStatus.ACTIVE));
        when(mockOrderService.findAll()).thenReturn(orders);

        when(mockProduct_typeService.findAll()).thenReturn(Collections.emptyList());

        // Configure ProductService.findAll(...).
        final List<Product> productList = List.of(new Product(0L, "name", 0, "img_path", "description", "short_description", new Maker(0L, "name"), new Product_type(0L, "name")));
        when(mockProductService.findAll()).thenReturn(productList);

        // Configure ProductInOrderService.findAll(...).
        final List<ProductInOrder> productInOrders = List.of(new ProductInOrder(0L, 0L, 0L, 0));
        when(mockProductInOrderService.findAll()).thenReturn(productInOrders);

        // Configure UserService.findAll(...).
        final List<User> users = List.of(new User(0L, "name", 0, "phone_number", "address", Role.USER, UserStatus.ACTIVE, "email", "password"));
        when(mockUserService.findAll()).thenReturn(users);

        // Run the test
        scheduleServiceUnderTest.start();

        // Verify the results
    }

    @Test
    void testStart_ProductServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockMakerService.findAll()).thenReturn(List.of(new Maker(0L, "name")));

        // Configure OrderService.findAll(...).
        final List<Order> orders = List.of(new Order(0L, new User(0L, "name", 0, "phone_number", "address", Role.USER, UserStatus.ACTIVE, "email", "password"), Date.valueOf(LocalDate.of(2020, 1, 1)), OrderStatus.ACTIVE));
        when(mockOrderService.findAll()).thenReturn(orders);

        when(mockProduct_typeService.findAll()).thenReturn(List.of(new Product_type(0L, "name")));
        when(mockProductService.findAll()).thenReturn(Collections.emptyList());

        // Configure ProductInOrderService.findAll(...).
        final List<ProductInOrder> productInOrders = List.of(new ProductInOrder(0L, 0L, 0L, 0));
        when(mockProductInOrderService.findAll()).thenReturn(productInOrders);

        // Configure UserService.findAll(...).
        final List<User> users = List.of(new User(0L, "name", 0, "phone_number", "address", Role.USER, UserStatus.ACTIVE, "email", "password"));
        when(mockUserService.findAll()).thenReturn(users);

        // Run the test
        scheduleServiceUnderTest.start();

        // Verify the results
    }

    @Test
    void testStart_ProductInOrderServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockMakerService.findAll()).thenReturn(List.of(new Maker(0L, "name")));

        // Configure OrderService.findAll(...).
        final List<Order> orders = List.of(new Order(0L, new User(0L, "name", 0, "phone_number", "address", Role.USER, UserStatus.ACTIVE, "email", "password"), Date.valueOf(LocalDate.of(2020, 1, 1)), OrderStatus.ACTIVE));
        when(mockOrderService.findAll()).thenReturn(orders);

        when(mockProduct_typeService.findAll()).thenReturn(List.of(new Product_type(0L, "name")));

        // Configure ProductService.findAll(...).
        final List<Product> productList = List.of(new Product(0L, "name", 0, "img_path", "description", "short_description", new Maker(0L, "name"), new Product_type(0L, "name")));
        when(mockProductService.findAll()).thenReturn(productList);

        when(mockProductInOrderService.findAll()).thenReturn(Collections.emptyList());

        // Configure UserService.findAll(...).
        final List<User> users = List.of(new User(0L, "name", 0, "phone_number", "address", Role.USER, UserStatus.ACTIVE, "email", "password"));
        when(mockUserService.findAll()).thenReturn(users);

        // Run the test
        scheduleServiceUnderTest.start();

        // Verify the results
    }

    @Test
    void testStart_UserServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockMakerService.findAll()).thenReturn(List.of(new Maker(0L, "name")));

        // Configure OrderService.findAll(...).
        final List<Order> orders = List.of(new Order(0L, new User(0L, "name", 0, "phone_number", "address", Role.USER, UserStatus.ACTIVE, "email", "password"), Date.valueOf(LocalDate.of(2020, 1, 1)), OrderStatus.ACTIVE));
        when(mockOrderService.findAll()).thenReturn(orders);

        when(mockProduct_typeService.findAll()).thenReturn(List.of(new Product_type(0L, "name")));

        // Configure ProductService.findAll(...).
        final List<Product> productList = List.of(new Product(0L, "name", 0, "img_path", "description", "short_description", new Maker(0L, "name"), new Product_type(0L, "name")));
        when(mockProductService.findAll()).thenReturn(productList);

        // Configure ProductInOrderService.findAll(...).
        final List<ProductInOrder> productInOrders = List.of(new ProductInOrder(0L, 0L, 0L, 0));
        when(mockProductInOrderService.findAll()).thenReturn(productInOrders);

        when(mockUserService.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        scheduleServiceUnderTest.start();

        // Verify the results
    }
}
