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
        assertEquals(1, fibonacciRepo.getSequence("client1").size());
    }

    @Test
    void saveNumber_whenClientIdExists_thenAddNumberToList() {
        fibonacciRepo.saveNumber("client1",1);
        fibonacciRepo.saveNumber("client1",2);
        assertEquals(2, fibonacciRepo.getSequence("client1").size());
        assertEquals(List.of(1,2), fibonacciRepo.getSequence("client1"));
    }
}