package org.example.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@ToString
public class LoanRequest {
    private final BigDecimal amount;
    private final Integer paybackYears;
}
