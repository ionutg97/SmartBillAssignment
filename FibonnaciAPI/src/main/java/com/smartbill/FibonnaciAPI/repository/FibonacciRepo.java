package com.smartbill.FibonnaciAPI.repository;

import java.util.List;
import java.util.Optional;

public interface FibonacciRepo {

    void saveNumber(String clientId, Integer number);

    Optional<Integer> removeLastNumber(String clientId);

    Optional<List<Integer>> getSequence(String clientId);
}
