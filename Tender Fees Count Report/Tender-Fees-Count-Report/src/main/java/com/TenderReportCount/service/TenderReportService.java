package com.TenderReportCount.service;

import java.time.LocalDate;
import java.util.List;

import com.TenderReportCount.entity.TenderReportCount;
import com.TenderReportCount.util.TenderReportResponse;

public interface TenderReportService {

	List<TenderReportResponse> tenderReportCount(String searchKey, LocalDate searchDate, String searchValue);

}
