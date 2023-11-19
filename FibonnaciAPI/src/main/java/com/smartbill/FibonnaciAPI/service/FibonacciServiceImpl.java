package com.smartbill.FibonnaciAPI.service;

import com.smartbill.FibonnaciAPI.repository.FibonacciRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class FibonacciServiceImpl implements FibonacciService {

  @Autowired private final FibonacciRepo fibonacciRepo;

  @Override
  public Integer addNextNumberAndPersistSequence(String clientId) {
    var number = 1;
    if (fibonacciRepo.getSequence(clientId) != null) {
      number = fibonacciRepo.getSequence(clientId).size();
    }
    Integer lastNumberInSequence = generateFibonacciSequence(number);
    fibonacciRepo.saveNumber(clientId, lastNumberInSequence);
    return lastNumberInSequence;
  }

  @Override
  public Integer removeLastNumberAndPersistSequence(String clientId) {
    if (fibonacciRepo.getSequence(clientId) != null
        && !fibonacciRepo.getSequence(clientId).isEmpty()) {
      var sequence = fibonacciRepo.getSequence(clientId);
      fibonacciRepo.removeLastNumber(clientId);
      return sequence.get(sequence.size() - 1);
    } else {
      return 0;
    }
  }

  @Override
  public List<Integer> getSequence(String clientId) {
    return fibonacciRepo.getSequence(clientId);
  }

  private Integer calculateFibonacciSum(Integer number) {
    if (number == 1 || number == 2) {
      return 1;
    } else {
      return calculateFibonacciSum(number - 1) + calculateFibonacciSum(number - 2);
    }
  }

  private Integer generateFibonacciSequence(Integer number) {
    var fibonacciSequence = new ArrayList<Integer>();
    for (int i = 1; i <= number; i++) {
      fibonacciSequence.add(calculateFibonacciSum(i));
    }
    return fibonacciSequence.get(fibonacciSequence.size() - 1);
  }
}
