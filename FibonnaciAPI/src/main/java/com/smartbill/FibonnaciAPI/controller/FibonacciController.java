package com.smartbill.FibonnaciAPI.controller;

import com.smartbill.FibonnaciAPI.dto.Result;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/fibonacci")
@AllArgsConstructor
public class FibonacciController {

  @PostMapping("/nextNumber/{clientId}")
  public ResponseEntity<Result> generateSumForNumber(
      @PathVariable("clientId") String clientId, @RequestParam("number") Integer number) {
    return new ResponseEntity<>(null, HttpStatus.CREATED);
  }

  @GetMapping("/previousNumber/{clientId}")
  public ResponseEntity<String> previousSumForNumber(@PathVariable("clientId") String clientId) {
    return new ResponseEntity<>("OK", HttpStatus.OK);
  }

  @GetMapping("/sequence/{clientId}")
  public ResponseEntity<List<Integer>> getSequenceOfNumbers(
      @PathVariable("clientId") String clientId) {
    return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
  }
}
