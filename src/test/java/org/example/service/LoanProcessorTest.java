package org.example.service;

import org.example.model.HousingLoan;
import org.example.model.base.Loan;
import org.example.model.request.LoanRequest;
import org.example.model.response.MonthlyPaymentResponse;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Set;
import java.util.TreeSet;

import static org.example.model.base.LoanType.HOUSING_LOAN;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class LoanProcessorTest {

    @Mock
    private PaymentPlanCalculator calculator;

    @Mock
    private PrintoutService printoutService;

    @Mock
    private ReaderService readerService;


    @InjectMocks
    private LoanProcessor loanProcessor;


    @Test
    public void testProcess() {
        LoanRequest loanRequest = new LoanRequest(BigDecimal.valueOf(10000), 5);
        when(readerService.createLoanRequest()).thenReturn(loanRequest);

        Loan loan = HousingLoan.of(loanRequest);

        Set<MonthlyPaymentResponse> responseSet = new TreeSet<>();

        when(calculator.generatePaymentPlan(loan)).thenReturn(responseSet);

        loanProcessor.process(HOUSING_LOAN);

        verify(readerService, times(1)).createLoanRequest();

        verify(calculator, times(1)).generatePaymentPlan(any(Loan.class));

        verify(printoutService, times(1)).print(responseSet);
    }

}