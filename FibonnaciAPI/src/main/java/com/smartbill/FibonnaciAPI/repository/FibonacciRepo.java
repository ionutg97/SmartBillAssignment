package com.smartbill.FibonnaciAPI.repository;

import java.util.List;

public interface FibonacciRepo {

    void saveNumber(String clientId, Integer number);

    void removeLastNumber(String clientId);

    List<Integer> getSequence(String clientId);
}
