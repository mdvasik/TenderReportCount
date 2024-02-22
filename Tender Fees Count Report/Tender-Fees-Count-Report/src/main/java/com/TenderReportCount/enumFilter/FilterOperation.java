package com.TenderReportCount.enumFilter;


public enum FilterOperation {
    EQUAL_TO("equalTo"),
    NOT_EQUAL("notEqual"),
    GREATER_THAN("greaterThan"),
    LESS_THAN("lessThan"),
    GREATER_THAN_OR_EQUAL_TO("greaterThanOrEqualTo"),
    LESS_THAN_OR_EQUAL_TO("lessThanOrEqualTo"),
    BETWEEN("between");

    private final String symbol;

    FilterOperation(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
