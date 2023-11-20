package com.smartbill.FibonnaciAPI.service;

import java.util.List;
import java.util.Optional;

public interface FibonacciService {

  Integer addNextNumberAndPersistSequence(String clientId);

  Optional<Integer> removeLastNumberAndPersistSequence(String clientId);

  List<Integer> getSequence(String clientId);
}
