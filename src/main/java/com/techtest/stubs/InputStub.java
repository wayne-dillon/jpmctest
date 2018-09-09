package com.techtest.stubs;

import com.techtest.enums.Currency;
import com.techtest.enums.Operation;
import com.techtest.objects.Instruction;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InputStub {

    private InputStub() {
    }

    public static List<Instruction> getInstructions() {
        List<Instruction> instructions = new ArrayList<Instruction>();

        instructions.add(new Instruction("foo", Operation.BUY, 0.50, Currency.SGP,
                new DateTime().withDate(2016, 1, 1).toDate(), new DateTime().withDate(2016, 1, 2).toDate(), 200, 100.25));

        instructions.add(new Instruction("bar", Operation.SELL, 0.22, Currency.AED,
                new DateTime().withDate(2016, 1, 5).toDate(), new DateTime().withDate(2016, 1, 7).toDate(), 450, 150.5));

        instructions.add(new Instruction("foo", Operation.BUY, 0.50, Currency.SGP,
                new DateTime().withDate(2016, 1, 1).toDate(), new DateTime().withDate(2016, 1, 6).toDate(), 200, 100.25));

        instructions.add(new Instruction("bar2", Operation.SELL, 0.22, Currency.AED,
                new DateTime().withDate(2016, 1, 5).toDate(), new DateTime().withDate(2016, 1, 6).toDate(), 500, 150.5));

        instructions.add(new Instruction("bar3", Operation.SELL, 0.22, Currency.AED,
                new DateTime().withDate(2016, 1, 5).toDate(), new DateTime().withDate(2016, 1, 5).toDate(), 550, 150.5));

        return instructions;
    }
}
