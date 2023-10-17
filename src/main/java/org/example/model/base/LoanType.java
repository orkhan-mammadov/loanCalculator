package org.example.model.base;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LoanType {

    HOUSING_LOAN(0.035), // 3.5% interest rate
    CAR_LOAN(0.055); // 5.5% interest rate

    private final double rate;

}
