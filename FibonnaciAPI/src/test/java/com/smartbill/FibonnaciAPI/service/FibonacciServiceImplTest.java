package com.smartbill.FibonnaciAPI.service;

import com.smartbill.FibonnaciAPI.repository.FibonacciRepoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FibonacciServiceImplTest {

  @Mock private FibonacciRepoImpl fibonacciRepo;

  private FibonacciServiceImpl fibonacciService;

  @BeforeEach
  void setUp() {
    fibonacciService = new FibonacciServiceImpl(fibonacciRepo);
  }

  @ParameterizedTest
  @CsvSource({
    "client1,1,1",
    "client1,1,2",
    "client1,2,3",
    "client2,1,1",
    "client1,3,4",
    "client2,1,2",
    "client1,5,5",})
  void calculateSumAndPersistSequence_whenNumberIsGreaterThanZero_thenCalculateSum(
      String clientId, Integer expectedNumber, Integer number) {
    when(fibonacciRepo.getSequence(clientId)).thenReturn(generateFibonacciSequence(number));
    assertEquals(expectedNumber, fibonacciService.addNextNumberAndPersistSequence(clientId));
  }

  private List<Integer> generateFibonacciSequence(Integer number) {
    var fibonacciSequence = new ArrayList<Integer>();
    for (int i = 1; i <= number; i++) {
      fibonacciSequence.add(calculateFibonacciSum(i));
    }
    return fibonacciSequence;
  }

  private Integer calculateFibonacciSum(Integer number) {
    if (number == 1) {
      return 1;
    } else if (number == 2) {
      return 2;
    } else {
      return calculateFibonacciSum(number - 1) + calculateFibonacciSum(number - 2);
    }
  }
}
