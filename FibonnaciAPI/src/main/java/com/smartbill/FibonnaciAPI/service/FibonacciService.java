package com.smartbill.FibonnaciAPI.service;

import java.util.List;

public interface FibonacciService {

  Integer addNextNumberAndPersistSequence(String clientId);

  Integer getPreviousSum(String clientId);

  List<Integer> getSequence(String clientId);
}
