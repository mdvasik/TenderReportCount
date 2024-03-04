package com.TenderReportCount.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.TenderReportCount.entity.TenderCount;
import com.TenderReportCount.enumFilter.FilterOperation;
import com.TenderReportCount.util.TenderReportResponse;

@Repository
public class TenderRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Method to find Tender entities based on various filters
    public List<TenderReportResponse> findByFilters(String departmentName, String domainName, LocalDate paymentDate,
                                      LocalDate eventPublishDateFrom, LocalDate bidSubmissionDateFrom,
                                      String eventStatus, Long eventId, String filterOperation) {
        // Constructing the SQL query
        StringBuilder sql = new StringBuilder("SELECT * FROM Tender_Count_NDA WHERE 1=1");
        List<Object> params = new ArrayList<>(); // List to hold query parameters

        // Adding departmentName filter if provided
        if (departmentName != null) {
            sql.append(" AND DepartmentName = ?");
            params.add(departmentName);
        }

        // Adding domainName filter if provided
        if (domainName != null) {
            sql.append(" AND domainName = ?");
            params.add(domainName);
        }

        // Adding date filters for paymentDate, eventPublishDateFrom, and bidSubmissionDateFrom
        appendDateFilter(sql, params, "PaymentDate", paymentDate, filterOperation);
        appendDateFilter(sql, params, "EventPublishDateFrom", eventPublishDateFrom, filterOperation);
        appendDateFilter(sql, params, "BidSubmissionDateFrom", bidSubmissionDateFrom, filterOperation);

        // Adding eventStatus filter if provided
        if (eventStatus != null) {
            sql.append(" AND EventStatus = ?");
            params.add(eventStatus);
        }

        // Adding eventId filter if provided
        if (eventId != null) {
            sql.append(" AND Eventid = ?");
            params.add(eventId);
        }

        // Executing the SQL query and returning the results
        List<TenderCount> tenders = jdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper<>(TenderCount.class));
        List<TenderReportResponse> tenderReportResponses = new ArrayList<>();
        for (TenderCount tender : tenders) {
            TenderReportResponse reportResponse = new TenderReportResponse();
            // Populate the reportResponse object from the Tender object
            // This might involve mapping attributes from Tender to TenderReportResponse
            reportResponse.setMessage("Search Successfully"); // Setting the message
            reportResponse.setStatusCode(200); // Example mapping
            reportResponse.setData(tender); // Example mapping
            tenderReportResponses.add(reportResponse);
        }
        return tenderReportResponses;
    }

    // Helper method to append date filter to the SQL query
    private void appendDateFilter(StringBuilder sql, List<Object> params, String fieldName, LocalDate date, String symbolName) {
        if (date != null && symbolName != null) {
            FilterOperation operation = FilterOperation.fromSymbol(symbolName); // Get the filter operation from symbol

            // Applying the appropriate filter operation to the SQL query
            switch (operation) {
                case EQUAL_TO:
                    sql.append(" AND ").append(fieldName).append(" = ?");
                    params.add(date);
                    break;
                case LESS_THAN:
                    sql.append(" AND ").append(fieldName).append(" < ?");
                    params.add(date);
                    break;
                case LESS_THAN_OR_EQUAL_TO:
                    sql.append(" AND ").append(fieldName).append(" <= ?");
                    params.add(date);
                    break;
                case GREATER_THAN:
                    sql.append(" AND ").append(fieldName).append(" > ?");
                    params.add(date);
                    break;
                case GREATER_THAN_OR_EQUAL_TO:
                    sql.append(" AND ").append(fieldName).append(" >= ?");
                    params.add(date);
                    break;
                case NOT_EQUAL:
                    sql.append(" AND ").append(fieldName).append(" <> ?");
                    params.add(date);
                    break;
                case BETWEEN:
                    // Calculate the end date for the BETWEEN operation
                    LocalDate endDate = date.plusDays(1);
                    // Construct the BETWEEN condition in SQL
                    sql.append(" AND ").append(fieldName).append(" BETWEEN ? AND ?");
                    // Add start date and end date parameters
                    params.add(date);
                    params.add(endDate);
                    break;
                default:
                    // Throw an exception for unsupported filter operations
                    throw new IllegalArgumentException("Invalid filter operation: " + operation);
            }
        }
    }
}
