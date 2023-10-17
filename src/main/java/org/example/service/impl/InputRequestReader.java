package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.model.request.LoanRequest;
import org.example.service.ReaderService;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

@RequiredArgsConstructor
public class InputRequestReader implements ReaderService {

    public LoanRequest createLoanRequest() {
        BigDecimal amount = readAmount();
        Integer paybackYears = readPaybackYears();
        return new LoanRequest(amount, paybackYears);
    }

    private BigDecimal readAmount() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter desired amount: ");
            BigDecimal amount = scanner.nextBigDecimal();
            if (amount.compareTo(BigDecimal.ZERO) < 0 || amount.scale() > 2) {
                throw new NumberFormatException();
            }
            return amount;
        } catch (NumberFormatException | InputMismatchException e) {
            System.out.println("Amount is not valid! Example: '1000', '123.45'. ");
            return readAmount();
        }
    }

    private Integer readPaybackYears() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter payback time in years: ");
            int paybackYears = scanner.nextInt();
            if (paybackYears < 1) {
                throw new NumberFormatException();
            }
            return paybackYears;
        } catch (InputMismatchException | NumberFormatException e) {
            System.out.println("Invalid value! Example: '1', '23'.");
            return readPaybackYears();
        }
    }
}
