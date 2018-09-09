package com.techtest.enums;

public enum Currency {
    AED("AED", false),
    SAR("SAR", false),
    SGP("SGP", true),
    USD("USD", true);

    private String name;
    private boolean mondayToFriday;

    Currency(String name, boolean mondayToFriday) {
        this.name = name;
        this.mondayToFriday = mondayToFriday;
    }

    public boolean isMondayToFriday() {
        return mondayToFriday;
    }

    public String toString() {
        return name;
    }

}
