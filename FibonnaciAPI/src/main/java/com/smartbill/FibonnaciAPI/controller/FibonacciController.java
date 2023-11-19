package com.smartbill.FibonnaciAPI.controller;

import com.smartbill.FibonnaciAPI.dto.Result;
import com.smartbill.FibonnaciAPI.service.FibonacciService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/fibonacci")
@AllArgsConstructor
public class FibonacciController {

  @Autowired private final FibonacciService service;

  @PostMapping("/nextNumber/{clientId}")
  public ResponseEntity<Result> generateNextElementInSequence(
      @PathVariable("clientId") String clientId) {

    var sum = service.addNextNumberAndPersistSequence(clientId);
    var result = new Result(clientId, sum);
    return new ResponseEntity<>(result, HttpStatus.CREATED);
  }

  @GetMapping("/previousNumber/{clientId}")
  public ResponseEntity<String> previousElementInSequence(
      @PathVariable("clientId") String clientId) {
    var lastNumberInSequence = service.removeLastNumberAndPersistSequence(clientId);
    if (lastNumberInSequence > 0) return new ResponseEntity<>("OK", HttpStatus.OK);
    else return new ResponseEntity<>("No numbers in sequence", HttpStatus.OK);
  }

  @GetMapping("/sequence/{clientId}")
  public ResponseEntity<List<Integer>> getSequenceOfNumbers(
      @PathVariable("clientId") String clientId) {
    return new ResponseEntity<>(service.getSequence(clientId), HttpStatus.OK);
  }
}
