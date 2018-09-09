package com.techtest.main;

import com.techtest.enums.Currency;
import com.techtest.enums.Operation;
import com.techtest.objects.Instruction;
import org.joda.time.DateTime;
import org.junit.*;
import java.util.Date;

import static org.junit.Assert.*;

public class BusinessRulesTest {

    Instruction testInstruction1;
    Instruction testInstruction2;
    Instruction testInstruction3;
    Instruction testInstruction4;

    @Before
    public void setUp() throws Exception {
        testInstruction1 = new Instruction("test", Operation.BUY, 0.5, Currency.USD,
                new Date(), new DateTime().withDate(2018, 9, 9).toDate(), 100, 20);
        testInstruction2 = new Instruction("test", Operation.BUY, 0.5, Currency.USD,
                new Date(), new DateTime().withDate(2018, 9, 8).toDate(), 100, 20);
        testInstruction3 = new Instruction("test", Operation.BUY, 0.5, Currency.AED,
                new Date(), new DateTime().withDate(2018, 9, 8).toDate(), 100, 20);
        testInstruction4 = new Instruction("test", Operation.BUY, 0.5, Currency.AED,
                new Date(), new DateTime().withDate(2018, 9, 7).toDate(), 100, 20);
    }

    @Test
    public void calculateDollarValue() {
        BusinessRules.calculateDollarValue(testInstruction1);
        assertEquals(1000.0, testInstruction1.getDollarValue(), 0.0);
    }

    @Test
    public void formatDate() {
        assertEquals("Sun 09 Sep 2018", BusinessRules.formatDate(testInstruction1.getSettlementDate()));
    }

    @Test
    public void fixSettlementDate() {
        Date expectedDateMonday = new DateTime().withDate(2018, 9, 10).toDate();
        Date expectedDateSunday = new DateTime().withDate(2018, 9, 9).toDate();

        BusinessRules.fixSettlementDate(testInstruction1);
        assertEquals(BusinessRules.formatDate(expectedDateMonday), BusinessRules.formatDate(testInstruction1.getSettlementDate()));

        BusinessRules.fixSettlementDate(testInstruction2);
        assertEquals(BusinessRules.formatDate(expectedDateMonday), BusinessRules.formatDate(testInstruction2.getSettlementDate()));

        BusinessRules.fixSettlementDate(testInstruction3);
        assertEquals(BusinessRules.formatDate(expectedDateSunday), BusinessRules.formatDate(testInstruction3.getSettlementDate()));

        BusinessRules.fixSettlementDate(testInstruction4);
        assertEquals(BusinessRules.formatDate(expectedDateSunday), BusinessRules.formatDate(testInstruction4.getSettlementDate()));
    }
}
