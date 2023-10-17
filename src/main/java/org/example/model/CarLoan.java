package org.example.model;

import org.example.model.base.Loan;
import org.example.model.request.LoanRequest;

import java.math.BigDecimal;

import static org.example.model.base.LoanType.CAR_LOAN;

public class CarLoan extends Loan {

    public static CarLoan of(LoanRequest request) {
        return new CarLoan(request.getAmount(), request.getPaybackYears());
    }

    private CarLoan(BigDecimal amount, Integer paybackYears) {
        super(BigDecimal.valueOf(CAR_LOAN.getRate()), paybackYears, amount);
    }
}
