package com.smartbill.FibonnaciAPI.repository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.function.Predicate;

@Repository
public class FibonacciRepoImpl implements FibonacciRepo {

  private final ConcurrentHashMap<String, ConcurrentLinkedDeque<Integer>> fibonacciSequence = new ConcurrentHashMap<>();

  @Override
  public void saveNumber(String clientId, Integer number) {
    fibonacciSequence.computeIfAbsent(clientId, id -> new ConcurrentLinkedDeque<>()).add(number);
  }

  @Override
  public Optional<Integer> removeLastNumber(String clientId) {
    return Optional.ofNullable(fibonacciSequence.get(clientId))
                   .filter(Predicate.not(ConcurrentLinkedDeque::isEmpty))
                   .map(ConcurrentLinkedDeque::removeLast);
  }

  @Override
  public Optional<List<Integer>> getSequence(String clientId) {
    return Optional.ofNullable(fibonacciSequence.get(clientId)).map(ArrayList::new);
  }
}
