package org.example.service.impl;

import org.example.model.base.Loan;
import org.example.model.response.MonthlyPaymentResponse;
import org.example.service.PaymentPlanCalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Set;
import java.util.TreeSet;

public class AmortizationCalculator implements PaymentPlanCalculator {

    @Override
    public Set<MonthlyPaymentResponse> generatePaymentPlan(Loan loan) {
        int numberOfMonths = loan.getPaybackYears() * 12;

        BigDecimal monthlyPayment = loan.calculateMonthlyPayment();
        System.out.printf("Expected calculated monthly payment: %.2f%n", monthlyPayment);

        BigDecimal remainingBalance = loan.getAmount();

        Set<MonthlyPaymentResponse> resultSet = new TreeSet<>();
        for (int month = 1; month < numberOfMonths; month++) {
            BigDecimal interestPayment = getInterestPayment(remainingBalance, loan);
            BigDecimal principalPayment = monthlyPayment.subtract(interestPayment);
            remainingBalance = remainingBalance.subtract(principalPayment);
            resultSet.add(createResponse(monthlyPayment, remainingBalance, interestPayment, principalPayment, month));
        }
        MonthlyPaymentResponse adjustedLastMonth = adjustLastMonth(remainingBalance, monthlyPayment, loan);
        resultSet.add(adjustedLastMonth);
        return resultSet;
    }


    private MonthlyPaymentResponse adjustLastMonth(BigDecimal remainingBalance, BigDecimal monthlyPay, Loan loan) {
        BigDecimal interestPayment = getInterestPayment(remainingBalance, loan);
        BigDecimal principalPayment = monthlyPay.subtract(interestPayment);
        if (principalPayment.compareTo(remainingBalance) > 0) {
            principalPayment = remainingBalance;
            monthlyPay = principalPayment.add(interestPayment);
        } else if (principalPayment.compareTo(remainingBalance) < 0) {
            principalPayment = remainingBalance;
            monthlyPay = principalPayment.add(interestPayment);
        }
        remainingBalance = remainingBalance.subtract(principalPayment);
        return createResponse(monthlyPay, remainingBalance, interestPayment, principalPayment, 12);
    }

    private BigDecimal getInterestPayment(BigDecimal remainingBalance, Loan loan) {
        return remainingBalance.multiply(loan.getMonthlyInterestRate())
                .setScale(2, RoundingMode.HALF_UP);
    }

    private MonthlyPaymentResponse createResponse(BigDecimal monthlyPayment, BigDecimal remainingBalance,
                                                  BigDecimal interestPayment, BigDecimal principalPayment,
                                                  int month) {
        return MonthlyPaymentResponse.builder()
                .month(month)
                .monthlyPay(monthlyPayment)
                .principalPay(principalPayment)
                .interestPay(interestPayment)
                .remainingDebt(remainingBalance)
                .build();
    }
}
