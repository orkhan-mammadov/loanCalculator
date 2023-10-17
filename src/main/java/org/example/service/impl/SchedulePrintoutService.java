package org.example.service.impl;

import org.example.model.response.MonthlyPaymentResponse;
import org.example.service.PrintoutService;

import java.util.Set;

public class SchedulePrintoutService implements PrintoutService {

    @Override
    public void print(Set<MonthlyPaymentResponse> resultSet) {
        System.out.println("Schedule:");
        resultSet.forEach(System.out::println);
    }
}
