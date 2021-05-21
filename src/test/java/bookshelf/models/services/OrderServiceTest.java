package bookshelf.models.services;

import bookshelf.models.entities.Order;
import bookshelf.models.entities.User;
import bookshelf.models.enums.OrderStatus;
import bookshelf.models.enums.Role;
import bookshelf.models.enums.UserStatus;
import bookshelf.models.repository.OrderRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepo mockOrderRepo;

    private OrderService orderServiceUnderTest;

    @BeforeEach
    void setUp() {
        orderServiceUnderTest = new OrderService(mockOrderRepo);
    }

    @Test
    void testSave() {
        // Setup
        final Order order = new Order(0L, new User(0L, "name", 0, "phone_number", "address", Role.USER, UserStatus.ACTIVE, "email", "password"), Date.valueOf(LocalDate.of(2020, 1, 1)), OrderStatus.ACTIVE);

        // Configure OrderRepo.save(...).
        final Order order1 = new Order(0L, new User(0L, "name", 0, "phone_number", "address", Role.USER, UserStatus.ACTIVE, "email", "password"), Date.valueOf(LocalDate.of(2020, 1, 1)), OrderStatus.ACTIVE);
        when(mockOrderRepo.save(new Order(0L, new User(0L, "name", 0, "phone_number", "address", Role.USER, UserStatus.ACTIVE, "email", "password"), Date.valueOf(LocalDate.of(2020, 1, 1)), OrderStatus.ACTIVE))).thenReturn(order1);

        // Run the test
        orderServiceUnderTest.save(order);

        // Verify the results
    }

    @Test
    void testFindAll() {
        // Setup
        final List<Order> expectedResult = List.of(new Order(0L, new User(0L, "name", 0, "phone_number", "address", Role.USER, UserStatus.ACTIVE, "email", "password"), Date.valueOf(LocalDate.of(2020, 1, 1)), OrderStatus.ACTIVE));

        // Configure OrderRepo.findAll(...).
        final List<Order> orders = List.of(new Order(0L, new User(0L, "name", 0, "phone_number", "address", Role.USER, UserStatus.ACTIVE, "email", "password"), Date.valueOf(LocalDate.of(2020, 1, 1)), OrderStatus.ACTIVE));
        when(mockOrderRepo.findAll()).thenReturn(orders);

        // Run the test
        final List<Order> result = orderServiceUnderTest.findAll();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testFindActiveOrdersByUser_id() {
        // Setup
        final Order expectedResult = new Order(0L, new User(0L, "name", 0, "phone_number", "address", Role.USER, UserStatus.ACTIVE, "email", "password"), Date.valueOf(LocalDate.of(2020, 1, 1)), OrderStatus.ACTIVE);

        // Configure OrderRepo.findActiveOrdersByUser_id(...).
        final Order order = new Order(0L, new User(0L, "name", 0, "phone_number", "address", Role.USER, UserStatus.ACTIVE, "email", "password"), Date.valueOf(LocalDate.of(2020, 1, 1)), OrderStatus.ACTIVE);
        when(mockOrderRepo.findActiveOrdersByUser_id(0L)).thenReturn(order);

        // Run the test
        final Order result = orderServiceUnderTest.findActiveOrdersByUser_id(0L);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testBuyProduct() {
        // Setup

        // Run the test
        orderServiceUnderTest.buyProduct(0L, Date.valueOf(LocalDate.of(2020, 1, 1)));

        // Verify the results
        verify(mockOrderRepo).setOrderTime(0L, Date.valueOf(LocalDate.of(2020, 1, 1)));
        verify(mockOrderRepo).makeOrderStatusClosed(0L);
    }

    @Test
    void testMakeOrderStatusCanceled() {
        // Setup

        // Run the test
        orderServiceUnderTest.makeOrderStatusCanceled(0L);

        // Verify the results
        verify(mockOrderRepo).makeOrderStatusCanceled(0L);
    }

    @Test
    void testChangeStatus() {
        // Setup

        // Run the test
        orderServiceUnderTest.changeStatus(0L, OrderStatus.ACTIVE);

        // Verify the results
        verify(mockOrderRepo).changeStatus(0L, OrderStatus.ACTIVE);
    }
}
