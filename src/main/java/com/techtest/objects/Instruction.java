package com.techtest.objects;

import com.techtest.enums.Currency;
import com.techtest.enums.Operation;
import com.techtest.main.BusinessRules;

import java.util.Date;

public class Instruction {

    private String entity;
    private Operation operation;
    private double agreedFx;
    private Currency currency;
    private Date instructionDate;
    private Date settlementDate;
    private int units;
    private double pricePerUnit;
    private double dollarValue;

    public Instruction(String entity, Operation operation, double agreedFx, Currency currency, Date instructionDate,
                       Date settlementDate, int units, double pricePerUnit) {
        this.entity = entity;
        this.operation = operation;
        this.agreedFx = agreedFx;
        this.currency = currency;
        this.instructionDate = instructionDate;
        this.settlementDate = settlementDate;
        this.units = units;
        this.pricePerUnit = pricePerUnit;
    }

    public String getEntity() {
        return entity;
    }

    public Operation getOperation() {
        return operation;
    }

    public double getAgreedFx() {
        return agreedFx;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Date getInstructionDate() {
        return instructionDate;
    }

    public Date getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(Date settlementDate) {
        this.settlementDate = settlementDate;
    }

    public int getUnits() {
        return units;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public double getDollarValue() {
        return dollarValue;
    }

    public void setDollarValue(double dollarValue) {
        this.dollarValue = dollarValue;
    }

    public String toString() {
        String entityString = "Entity: " + entity;
        String operationString = ", operation: " + operation.toString();
        String agreedFxString = ", agreedFx: " + agreedFx;
        String currencyString = ", currency: " + currency.toString();
        String instructionDateString = ", instruction date: " + BusinessRules.formatDate(instructionDate);
        String settlementDateString = ", settlement date: " + BusinessRules.formatDate(settlementDate);
        String unitsString = ", units: " + units;
        String priceString = ", price per unit: " + pricePerUnit;
        String dollarValueString = ", value of trade: $" + dollarValue;

        return entityString + operationString + agreedFxString + currencyString + instructionDateString
                + settlementDateString + unitsString + priceString + dollarValueString;
    }
}
