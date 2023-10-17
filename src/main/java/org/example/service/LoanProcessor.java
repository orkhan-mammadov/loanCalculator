package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.factory.LoanFactory;
import org.example.model.base.Loan;
import org.example.model.base.LoanType;
import org.example.model.request.LoanRequest;
import org.example.model.response.MonthlyPaymentResponse;

import java.util.Set;

@RequiredArgsConstructor
public class LoanProcessor {

    private final PaymentPlanCalculator calculator;

    private final PrintoutService printoutService;

    private final ReaderService readerService;

    public void process(LoanType type) {
        LoanRequest request = readerService.createLoanRequest();
        Loan loan = LoanFactory.getLoanByType(type, request);
        Set<MonthlyPaymentResponse> responseSet = calculator.generatePaymentPlan(loan);
        printoutService.print(responseSet);
    }
}
