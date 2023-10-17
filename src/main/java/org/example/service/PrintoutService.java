package org.example.service;

import org.example.model.response.MonthlyPaymentResponse;

import java.util.Set;

public interface PrintoutService {

    void print(Set<MonthlyPaymentResponse> resultSet);
}
