package org.example.service;

import org.example.model.base.Loan;
import org.example.model.response.MonthlyPaymentResponse;

import java.util.Set;

public interface PaymentPlanCalculator {

    Set<MonthlyPaymentResponse> generatePaymentPlan(Loan loan);
}
