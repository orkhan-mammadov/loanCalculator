package org.example.factory;

import org.example.model.CarLoan;
import org.example.model.HousingLoan;
import org.example.model.base.Loan;
import org.example.model.base.LoanType;
import org.example.model.request.LoanRequest;

import java.util.Objects;

public class LoanFactory {

    public static Loan getLoanByType(LoanType type, LoanRequest request) {
        if (Objects.requireNonNull(type) == LoanType.HOUSING_LOAN) {
            return HousingLoan.of(request);
        } else if (Objects.requireNonNull(type) == LoanType.CAR_LOAN) {
            return CarLoan.of(request);
        }
        throw new IllegalArgumentException("Loan type not supported");
    }
}
