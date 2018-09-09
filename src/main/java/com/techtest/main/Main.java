package com.techtest.main;

import com.techtest.enums.Operation;
import com.techtest.objects.Instruction;
import com.techtest.stubs.InputStub;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static List<Instruction> instructions;
    private static List<Instruction> incomingInstructions = new ArrayList<>();
    private static List<Instruction> outgoingInstructions = new ArrayList<>();
    private static double dollarValueTotal;
    private static int i;

    public static void main(String[] args) {
        setInstructions(InputStub.getInstructions());

        // list of instructions given
        System.out.println("Instructions:");
        printInstructionList();
        System.out.println();

        // dollar value settled incoming daily
        System.out.println("Daily total settled incoming:");
        totalAndPrintDaily(incomingInstructions);
        System.out.println();

        // dollar value settled outgoing daily
        System.out.println("Daily total settled outgoing:");
        totalAndPrintDaily(outgoingInstructions);
        System.out.println();

        // ranking of entities based on incoming and outgoing amount
        System.out.println("Entities ranked by incoming amount");
        calculateAndPrintEntityRankings(incomingInstructions);
        System.out.println();
        System.out.println("Entities ranked by outgoing amount");
        calculateAndPrintEntityRankings(outgoingInstructions);
        System.out.println();
    }

    public static void printInstructionList() {
        instructions.forEach(instruction -> {
            BusinessRules.fixSettlementDate(instruction);
            BusinessRules.calculateDollarValue(instruction);
            System.out.println(instruction);
            sortInstructions(instruction);
        });
    }

    public static void calculateAndPrintEntityRankings(List<Instruction> istructions) {
        Map<String, List<Instruction>> instructionsGrouped = istructions.stream()
                .collect(Collectors.groupingBy(Instruction::getEntity));
        Set<String> entities = instructionsGrouped.keySet();
        HashMap<String, Double> entityTotals = calculateTotalValueForEntities(instructionsGrouped, entities);
        rankedListOfEntities(entityTotals);
    }

    public static HashMap<String, Double> calculateTotalValueForEntities(Map<String, List<Instruction>> instructionsGrouped, Set<String> entities) {
        HashMap<String, Double> entityTotals = new HashMap<>();
        entities.forEach(entity -> {
            List<Instruction> entityInstructions = instructionsGrouped.get(entity);
            entityInstructions.forEach(instruction -> dollarValueTotal += instruction.getDollarValue());
            entityTotals.put(entity, dollarValueTotal);
            dollarValueTotal = 0;
        });
        return entityTotals;
    }

    public static void rankedListOfEntities(HashMap<String, Double> entityTotals) {
        i = 1;
        sortByValue(entityTotals).forEach((entity, value) -> {
            System.out.println(i + ") " + entity + ": " + value);
            i++;
        });
    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }

    public static void totalAndPrintDaily(List<Instruction> instructionsList) {
        Map<String, List<Instruction>> instructionsGrouped = instructionsList.stream()
                .collect(Collectors.groupingBy(instruction -> BusinessRules.formatDate(instruction.getSettlementDate())));
        Set<String> settlementDates = instructionsGrouped.keySet();
        settlementDates.forEach(date -> {
            List<Instruction> dailyInstructions = instructionsGrouped.get(date);
            dailyInstructions.forEach(instruction -> dollarValueTotal += instruction.getDollarValue());
            System.out.println(date + ": $" + dollarValueTotal);
            dollarValueTotal = 0;
        });
    }

    public static void sortInstructions(Instruction instruction) {
        if (instruction.getOperation() == Operation.SELL) {
            incomingInstructions.add(instruction);
        } else {
            outgoingInstructions.add(instruction);
        }
    }

    public static void setInstructions(List<Instruction> instructions) {
        Main.instructions = instructions;
    }

    public List<Instruction> getInstructions() {
        return instructions;
    }

    public static List<Instruction> getIncomingInstructions() {
        return incomingInstructions;
    }

    public static List<Instruction> getOutgoingInstructions() {
        return outgoingInstructions;
    }

    public static double getDollarValueTotal() {
        return dollarValueTotal;
    }

}
