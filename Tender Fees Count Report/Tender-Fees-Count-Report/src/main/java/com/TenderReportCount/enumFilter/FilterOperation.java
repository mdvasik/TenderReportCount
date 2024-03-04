package com.TenderReportCount.enumFilter;

import java.util.Arrays;

public enum FilterOperation {
    EQUAL_TO("="),
    NOT_EQUAL("<>"),
    GREATER_THAN(">"),
    LESS_THAN("<"),
    GREATER_THAN_OR_EQUAL_TO(">="),
    LESS_THAN_OR_EQUAL_TO("<="),
    BETWEEN("BETWEEN");

    private final String symbol;

    FilterOperation(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public static FilterOperation fromSymbol(String symbol) {
        for (FilterOperation operation : FilterOperation.values()) {
            if (operation.name().equalsIgnoreCase(symbol)) {
                return operation;
            }
        }
        throw new IllegalArgumentException("Invalid filter operation symbol: " + symbol);
    }

    public static boolean isValidSymbol(String symbol) {
        return Arrays.stream(FilterOperation.values())
                     .anyMatch(operation -> operation.name().equalsIgnoreCase(symbol));
    }
}
