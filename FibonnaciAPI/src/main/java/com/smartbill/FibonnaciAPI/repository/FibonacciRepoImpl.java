package com.smartbill.FibonnaciAPI.repository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Repository
public class FibonacciRepoImpl implements FibonacciRepo {

  private final LinkedHashMap<String, List<Integer>> fibonacciSequence = new LinkedHashMap<>();

  @Override
  public void saveNumber(String clientId, Integer number) {

    if (!fibonacciSequence.containsKey(clientId)) {
      var listOfNumbers = new ArrayList<Integer>();
      listOfNumbers.add(number);
      fibonacciSequence.put(clientId, listOfNumbers);
    } else {
      var listOfNumbers = fibonacciSequence.get(clientId);
      listOfNumbers.add(number);
      fibonacciSequence.put(clientId, listOfNumbers);
    }
  }

  @Override
  public void removeLastNumber(String clientId) {
    if (fibonacciSequence.containsKey(clientId)) {
      var listOfNumbers =
          fibonacciSequence.get(clientId).subList(0, fibonacciSequence.get(clientId).size() - 1);
      fibonacciSequence.put(clientId, listOfNumbers);
    }
  }

  @Override
  public List<Integer> getSequence(String clientId) {
    return fibonacciSequence.get(clientId);
  }
}
