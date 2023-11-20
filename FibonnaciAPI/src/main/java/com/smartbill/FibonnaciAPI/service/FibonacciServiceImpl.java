package com.smartbill.FibonnaciAPI.service;

import ch.qos.logback.core.joran.sanity.Pair;
import com.smartbill.FibonnaciAPI.repository.FibonacciRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class FibonacciServiceImpl implements FibonacciService {
  private static final int DEFAULT_SEQUENCE_NUMBER = 1;

  @Autowired private final FibonacciRepo fibonacciRepo;

  @Override
  public Integer addNextNumberAndPersistSequence(String clientId) {
    final Integer newNumber = fibonacciRepo
            .getSequence(clientId)
            .filter(sequence -> sequence.size() > 1)
            .map(sequence -> {
              int size = sequence.size();
              return sequence.get(size - 2) + sequence.get(size - 1);
            }).orElse(DEFAULT_SEQUENCE_NUMBER);
    fibonacciRepo.saveNumber(clientId, newNumber);
    return newNumber;
  }

  @Override
  public Optional<Integer> removeLastNumberAndPersistSequence(String clientId) {
    return fibonacciRepo.removeLastNumber(clientId);
  }

  @Override
  public List<Integer> getSequence(String clientId) {
    return fibonacciRepo.getSequence(clientId).orElse(Collections.emptyList());
  }
}
