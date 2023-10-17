package org.example.model.base.util;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class MonthlyPaymentCalcUtils {

    public static BigDecimal getDenominator(BigDecimal poweredRate) {
        return poweredRate.subtract(BigDecimal.ONE);
    }

    public static BigDecimal getNumerator(BigDecimal monthlyInterestRate, BigDecimal poweredRate) {
        return monthlyInterestRate.multiply(poweredRate);
    }

    public static BigDecimal getPoweredRate(BigDecimal monthlyInterestRate, Integer numberOfMonths) {
        return BigDecimal.ONE.add(monthlyInterestRate).pow(numberOfMonths);
    }

    public static BigDecimal getMonthlyInterestRate(BigDecimal interestRate) {
        return interestRate.divide(BigDecimal.valueOf(12), 10, RoundingMode.HALF_UP);
    }
}
