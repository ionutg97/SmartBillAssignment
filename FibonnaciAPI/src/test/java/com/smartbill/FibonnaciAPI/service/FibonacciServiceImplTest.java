package com.smartbill.FibonnaciAPI.service;

import com.smartbill.FibonnaciAPI.repository.FibonacciRepoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FibonacciServiceImplTest {

  @Mock private FibonacciRepoImpl fibonacciRepo;

  private FibonacciServiceImpl fibonacciService;

  @BeforeEach
  void setUp() { fibonacciService = new FibonacciServiceImpl(fibonacciRepo); }

  @ParameterizedTest
  @CsvSource({
    "client1,1,0",
    "client1,1,1",
    "client1,2,2",
    "client2,1,0",
    "client1,3,3",
    "client2,1,1",
    "client1,5,4",
  })
  void calculateSumAndPersistSequence_whenNumberIsGreaterThanZero_thenGetTheNextElementInSequence(
      String clientId, Integer expectedNumber, Integer number) {
    when(fibonacciRepo.getSequence(clientId)).thenReturn(Optional.of(generateFibonacciSequence(number)));
    assertEquals(expectedNumber, fibonacciService.addNextNumberAndPersistSequence(clientId));
  }

  @Test
  void
      removeLastNumberAndPersistSequence_whenSequenceExistsForGivenClient_thenRemoveTheLastElement() {
    final List<Integer> sequence = generateFibonacciSequence(3);
    when(fibonacciRepo.removeLastNumber("client1")).thenReturn(Optional.of(sequence.get(sequence.size() - 1)));
    assertEquals(Optional.of(2), fibonacciService.removeLastNumberAndPersistSequence("client1"));
  }

  @Test
  void removeLastNumberAndPersistSequence_whenSequenceDoNotExist_thenReturn0() {
    assertFalse(fibonacciService.removeLastNumberAndPersistSequence("client1").isPresent());
  }

  @Test
  void removeLastNumberAndPersistSequence_whenSequenceIsEmpty_thenReturn0() {
    assertFalse(fibonacciService.removeLastNumberAndPersistSequence("client1").isPresent());
  }

  @Test
  void getSequence_whenClientIdExist_thenReturnSequence() {
    when(fibonacciRepo.getSequence("client1")).thenReturn(Optional.of(generateFibonacciSequence(7)));
    assertEquals(7, fibonacciService.getSequence("client1").size());
    assertEquals(List.of(1,1,2,3,5,8,13), fibonacciService.getSequence("client1"));
  }

  @Test
  void removeLastNumberAndPersistSequence_whenNumberIsGreaterThanZero_thenCalculateSum() {
    final List<Integer> integers = generateFibonacciSequence(3);
    when(fibonacciRepo.removeLastNumber("client1")).thenReturn(Optional.of(integers.get(integers.size() - 1)));
    assertEquals(Optional.of(2), fibonacciService.removeLastNumberAndPersistSequence("client1"));
  }

  private List<Integer> generateFibonacciSequence(Integer number) {
    return IntStream.range(1, number + 1)
                    .mapToObj(this::calculateFibonacciSum)
                    .collect(Collectors.toList());
  }

  private Integer calculateFibonacciSum(Integer number) {
    if (number == 1 || number == 2) {
      return 1;
    } else {
      return calculateFibonacciSum(number - 1) + calculateFibonacciSum(number - 2);
    }
  }
}
