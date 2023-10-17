package org.example;

import org.example.service.LoanProcessor;
import org.example.service.impl.AmortizationCalculator;
import org.example.service.impl.InputRequestReader;
import org.example.service.impl.SchedulePrintoutService;

import static org.example.model.base.LoanType.HOUSING_LOAN;

public class App {

    public static void main(String[] args) {
        LoanProcessor processor = new LoanProcessor(
                new AmortizationCalculator(),
                new SchedulePrintoutService(),
                new InputRequestReader()
        );
        //By default, HOUSING_LOAN is used as wanted in the task
        processor.process(HOUSING_LOAN);
    }

}
