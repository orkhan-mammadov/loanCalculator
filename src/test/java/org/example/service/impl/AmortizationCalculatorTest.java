package org.example.service.impl;

import org.example.model.HousingLoan;
import org.example.model.base.Loan;
import org.example.model.request.LoanRequest;
import org.example.model.response.MonthlyPaymentResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Set;
import java.util.TreeSet;

@RunWith(MockitoJUnitRunner.class)
public class AmortizationCalculatorTest {

    @InjectMocks
    private AmortizationCalculator calculator;

    private Loan loan;

    @Before
    public void setUp() {
        LoanRequest request = new LoanRequest(BigDecimal.valueOf(10000), 1);
        loan = HousingLoan.of(request);
    }

    @Test
    public void generatePaymentPlan() {
        Set<MonthlyPaymentResponse> expectedResultSet = generateExpectedResultSet();
        Set<MonthlyPaymentResponse> actual = calculator.generatePaymentPlan(loan);
        Assertions.assertEquals(expectedResultSet, actual);
    }

    private Set<MonthlyPaymentResponse> generateExpectedResultSet() {
        Set<MonthlyPaymentResponse> result = new TreeSet<>();
        result.add(generatePerMonth(1, 849.22, 820.05, 29.17, 9179.95));
        result.add(generatePerMonth(2, 849.22, 822.45, 26.77, 8357.50));
        result.add(generatePerMonth(3, 849.22, 824.84, 24.38, 7532.66));
        result.add(generatePerMonth(4, 849.22, 827.25, 21.97, 6705.41));
        result.add(generatePerMonth(5, 849.22, 829.66, 19.56, 5875.75));
        result.add(generatePerMonth(6, 849.22, 832.08, 17.14, 5043.67));
        result.add(generatePerMonth(7, 849.22, 834.51, 14.71, 4209.16));
        result.add(generatePerMonth(8, 849.22, 836.94, 12.28, 3372.22));
        result.add(generatePerMonth(9, 849.22, 839.38, 9.84, 2532.84));
        result.add(generatePerMonth(10, 849.22, 841.83, 7.39, 1691.01));
        result.add(generatePerMonth(11, 849.22, 844.29, 4.93, 846.72));
        result.add(generatePerMonth(12, 849.19, 846.72, 2.47, 0.00));
        return result;
    }

    private MonthlyPaymentResponse generatePerMonth(int month, Double mp, Double pp, Double ip, Double rb) {
        return MonthlyPaymentResponse.builder()
                .month(month)
                .monthlyPay(BigDecimal.valueOf(mp))
                .principalPay(BigDecimal.valueOf(pp))
                .interestPay(BigDecimal.valueOf(ip))
                .remainingDebt(BigDecimal.valueOf(rb))
                .build();
    }

}