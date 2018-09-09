package com.techtest.main;

import com.techtest.objects.Instruction;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BusinessRules {

    public static void calculateDollarValue(Instruction instruction) {
        instruction.setDollarValue(instruction.getPricePerUnit() * instruction.getUnits() * instruction.getAgreedFx());
    }

    public static void fixSettlementDate(Instruction instruction) {
        Calendar requestedSettlementDate = Calendar.getInstance();
        requestedSettlementDate.setTime(instruction.getSettlementDate());
        int dayOfWeek = requestedSettlementDate.get(Calendar.DAY_OF_WEEK);

        if (instruction.getCurrency().isMondayToFriday()) {
            adjustWeekendSettlementDatesToMonday(instruction, requestedSettlementDate, dayOfWeek);
        } else {
            adjustWeekendSettlementDatesToSunday(instruction, requestedSettlementDate, dayOfWeek);
        }
    }

    public static void adjustWeekendSettlementDatesToSunday(Instruction instruction, Calendar requestedSettlementDate, int dayOfWeek) {
        if (dayOfWeek == Calendar.FRIDAY) {
            requestedSettlementDate.add(Calendar.HOUR_OF_DAY, 48);
            instruction.setSettlementDate(requestedSettlementDate.getTime());
        } else if (dayOfWeek == Calendar.SATURDAY) {
            requestedSettlementDate.add(Calendar.HOUR_OF_DAY, 24);
            instruction.setSettlementDate(requestedSettlementDate.getTime());
        }
    }

    public static void adjustWeekendSettlementDatesToMonday(Instruction instruction, Calendar requestedSettlementDate, int dayOfWeek) {
        if (dayOfWeek == Calendar.SATURDAY) {
            requestedSettlementDate.add(Calendar.HOUR_OF_DAY, 48);
            instruction.setSettlementDate(requestedSettlementDate.getTime());
        } else if (dayOfWeek == Calendar.SUNDAY) {
            requestedSettlementDate.add(Calendar.HOUR_OF_DAY, 24);
            instruction.setSettlementDate(requestedSettlementDate.getTime());
        }
    }

    public static String formatDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("EE dd MMM yyyy");
        return formatter.format(date);
    }
}
