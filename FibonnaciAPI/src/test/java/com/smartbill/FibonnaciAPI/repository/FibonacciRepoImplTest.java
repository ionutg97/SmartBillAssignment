package com.smartbill.FibonnaciAPI.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FibonacciRepoImplTest {

    private FibonacciRepoImpl fibonacciRepo;

    @BeforeEach
    void setUp() {
        fibonacciRepo = new FibonacciRepoImpl();
    }

    @Test
    void saveNumber_whenClientIdDoesNotExist_thenCreateNewList() {
        fibonacciRepo.saveNumber("client1",1);
        assertEquals(1, fibonacciRepo.getSequence("client1").get().size());
    }

    @Test
    void saveNumber_whenClientIdExists_thenAddNumberToList() {
        fibonacciRepo.saveNumber("client1",1);
        fibonacciRepo.saveNumber("client1",2);
        assertEquals(2, fibonacciRepo.getSequence("client1").get().size());
        assertEquals(List.of(1,2), fibonacciRepo.getSequence("client1").get());
    }

    @Test
    void removeLastNumber_whenClientIdExists_thenRemoveLastNumberFromList() {
        fibonacciRepo.saveNumber("client1",1);
        fibonacciRepo.saveNumber("client1",2);
        fibonacciRepo.removeLastNumber("client1");
        assertEquals(1, fibonacciRepo.getSequence("client1").get().size());
        assertEquals(List.of(1), fibonacciRepo.getSequence("client1").get());
    }

    @Test
    void removeLastNumber_whenClientIdDoesNotExist_thenDoNothing() {
        fibonacciRepo.removeLastNumber("client1");
        assertFalse(fibonacciRepo.getSequence("client1").isPresent());
    }

    @Test
    void getSequence_whenClientIdExists_thenReturnSequence() {
        fibonacciRepo.saveNumber("client1",1);
        fibonacciRepo.saveNumber("client1",2);
        assertEquals(List.of(1,2), fibonacciRepo.getSequence("client1").get());
    }
}
