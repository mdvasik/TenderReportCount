package com.TenderReportCount.service;

import java.time.LocalDate;
import java.util.List;

import com.TenderReportCount.util.TenderReportResponse;

public interface TenderService {

    List<TenderReportResponse> getFilteredTenders(String departmentName, String domainName, LocalDate paymentDate,
                                    LocalDate eventPublishDateFrom, LocalDate bidSubmissionDateFrom,
                                    String eventStatus, Long eventId, String filterOperation);
}
