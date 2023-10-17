package org.example.model;

import org.example.model.base.Loan;
import org.example.model.request.LoanRequest;

import java.math.BigDecimal;

import static org.example.model.base.LoanType.HOUSING_LOAN;

public class HousingLoan extends Loan {

    public static HousingLoan of(LoanRequest request) {
        return new HousingLoan(request.getAmount(), request.getPaybackYears());
    }

    private HousingLoan(BigDecimal amount, Integer paybackYears) {
        super(BigDecimal.valueOf(HOUSING_LOAN.getRate()), paybackYears, amount);
    }

}
