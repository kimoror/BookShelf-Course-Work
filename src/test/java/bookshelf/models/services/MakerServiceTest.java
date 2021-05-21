package bookshelf.models.services;

import bookshelf.models.entities.Maker;
import bookshelf.models.repository.MakerRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MakerServiceTest {

    @Mock
    private MakerRepo mockMakerRepo;

    private MakerService makerServiceUnderTest;

    @BeforeEach
    void setUp() {
        makerServiceUnderTest = new MakerService(mockMakerRepo);
    }

    @Test
    void testFindAll() {
        // Setup
        when(mockMakerRepo.findAll()).thenReturn(List.of(new Maker(0L, "name")));

        // Run the test
        final List<Maker> result = makerServiceUnderTest.findAll();

        // Verify the results
    }

    @Test
    void testFindAll_MakerRepoReturnsNoItems() {
        // Setup
        when(mockMakerRepo.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<Maker> result = makerServiceUnderTest.findAll();

        // Verify the results
    }
}
