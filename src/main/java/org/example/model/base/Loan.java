package org.example.model.base;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.model.base.util.MonthlyPaymentCalcUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;

@RequiredArgsConstructor
@Getter
public abstract class Loan {

    protected final BigDecimal interestRate;

    protected final Integer paybackYears;

    protected final BigDecimal amount;

    public BigDecimal calculateMonthlyPayment() {
        Integer numberOfMonths = this.paybackYears * 12;
        BigDecimal monthlyInterestRate = this.getMonthlyInterestRate();
        BigDecimal poweredRate = MonthlyPaymentCalcUtils.getPoweredRate(monthlyInterestRate, numberOfMonths);
        BigDecimal numerator = MonthlyPaymentCalcUtils.getNumerator(monthlyInterestRate, poweredRate);
        BigDecimal denominator = MonthlyPaymentCalcUtils.getDenominator(poweredRate);
        return this.amount.multiply(numerator.divide(denominator, 10, RoundingMode.HALF_UP))
                .setScale(2, RoundingMode.HALF_UP);
    }

    public final BigDecimal getMonthlyInterestRate() {
        return MonthlyPaymentCalcUtils.getMonthlyInterestRate(this.interestRate);
    }

}
