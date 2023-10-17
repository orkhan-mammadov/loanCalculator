package org.example.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@ToString
@Builder
public class MonthlyPaymentResponse implements Comparable<MonthlyPaymentResponse> {

    private final int month;

    private final BigDecimal monthlyPay;

    private final BigDecimal principalPay;

    private final BigDecimal interestPay;

    private final BigDecimal remainingDebt;


    @Override
    public int compareTo(MonthlyPaymentResponse other) {
        // Compare objects based on their month field
        return Integer.compare(this.month, other.month);
    }

}
