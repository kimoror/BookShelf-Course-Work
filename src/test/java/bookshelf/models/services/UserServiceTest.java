package bookshelf.models.services;

import bookshelf.models.entities.User;
import bookshelf.models.enums.Role;
import bookshelf.models.enums.UserStatus;
import bookshelf.models.repository.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepo mockUserRepo;

    private UserService userServiceUnderTest;

    @BeforeEach
    void setUp() {
        userServiceUnderTest = new UserService(mockUserRepo);
    }

    @Test
    void testFindAll() {
        // Setup
        final List<User> expectedResult = List.of(new User(0L, "name", 0, "phone_number", "address", Role.USER, UserStatus.ACTIVE, "email", "password"));

        // Configure UserRepo.findAll(...).
        final List<User> users = List.of(new User(0L, "name", 0, "phone_number", "address", Role.USER, UserStatus.ACTIVE, "email", "password"));
        when(mockUserRepo.findAll()).thenReturn(users);

        // Run the test
        final List<User> result = userServiceUnderTest.findAll();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testFindByEmail() {
        // Setup
        final User expectedResult = new User(0L, "name", 0, "phone_number", "address", Role.USER, UserStatus.ACTIVE, "email", "password");

        // Configure UserRepo.findByEmail(...).
        final Optional<User> user = Optional.of(new User(0L, "name", 0, "phone_number", "address", Role.USER, UserStatus.ACTIVE, "email", "password"));
        when(mockUserRepo.findByEmail("email")).thenReturn(user);

        // Run the test
        final User result = userServiceUnderTest.findByEmail("email");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testChangeUserRole() {
        // Setup

        // Run the test
        userServiceUnderTest.changeUserRole(0L, Role.USER);

        // Verify the results
        verify(mockUserRepo).changeUserRole(0L, Role.USER);
    }
}
