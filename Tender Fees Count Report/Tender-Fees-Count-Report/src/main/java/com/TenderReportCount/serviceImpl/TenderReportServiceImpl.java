package com.TenderReportCount.serviceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.TenderReportCount.entity.TenderReportCount;
import com.TenderReportCount.enumFilter.FilterOperation;
import com.TenderReportCount.repository.TenderReportRepository;
import com.TenderReportCount.service.TenderReportService;
import com.TenderReportCount.util.TenderReportResponse;
import com.TenderReportCount.util.TenderReportUtil;

@Service
public class TenderReportServiceImpl implements TenderReportService {

    @Autowired
    private TenderReportRepository tenderReportRepository;

    private final FilterOperation filterOperation = FilterOperation.EQUAL_TO; // Default filter operation

	@Override
	public List<TenderReportResponse> tenderReportCount(String searchKey, LocalDate searchDate, String searchValue) {
		List<TenderReportCount> searchResults = new ArrayList<>();

		List<TenderReportCount> searchingKeyDb = null;
		if ("startDate".equals(searchKey) || "endDate".equals(searchKey) || "publishDate".equals(searchKey)) {
			// Fetch data based on date
			List<TenderReportCount> searchingDate = this.tenderReportRepository.findBySearchingKeyByDate(searchKey);
			if (!searchingDate.isEmpty()) {
				// Perform further filtering based on date and searchValue
				searchResults = applyFilter(searchingDate, searchDate, searchValue);
				return Collections
						.singletonList(TenderReportUtil.makeResponse(searchResults, "Search successfully", 200));
			} else {
				// If no data found, return an error response
				return Collections.singletonList(TenderReportUtil.makeResponse(null,
						"No data found for the specified date.", HttpStatus.NOT_FOUND.value()));
			}
		} else if ("tenderId".equals(searchKey) && searchValue != null && !searchValue.isEmpty()) {
			List<TenderReportCount> searchTenderId = this.tenderReportRepository.findByTenderId(searchValue);
			if (!searchTenderId.isEmpty()) {
				// If data is found, return a success response
				return Collections.singletonList(
						TenderReportUtil.makeResponse(searchTenderId, "Search successfully", 200));
			} else {
				// If no data found, return an error response
				return Collections.singletonList(TenderReportUtil.makeResponse(null,
						"No data found for the specified tender ID.", HttpStatus.NOT_FOUND.value()));
			}
		}  else {
					searchingKeyDb = this.tenderReportRepository.findByDepartmentAndOrganization(searchKey,
							searchValue);
					System.out.println("SQL Query is:" + searchingKeyDb);

					if (searchingKeyDb != null && !searchingKeyDb.isEmpty()) {
						// If data is found, format the results and return a success response
						List<Map<String, String>> formattedResults = new ArrayList<>();
						for (TenderReportCount report : searchingKeyDb) {
							Map<String, String> formattedResult = new HashMap<>();
							formattedResult.put("organization", report.getOrganization());
							formattedResult.put("department", report.getDepartment());
							formattedResults.add(formattedResult);
						}
						return Collections.singletonList(
								TenderReportUtil.makeResponse(formattedResults, "Search successfully", 200));
					} else {
						// If no data found, return an error response
						return Collections.singletonList(TenderReportUtil.makeResponse(null,
								"No data found for the specified organization or department.",
								HttpStatus.NOT_FOUND.value()));
					}
				}
			}

	private List<TenderReportCount> applyFilter(List<TenderReportCount> searchingKey, LocalDate searchDate,
	        String searchValue) {

	    List<TenderReportCount> searchResults = new ArrayList<>();
	    LocalDate currentDate = LocalDate.now();

	    for (TenderReportCount report : searchingKey) {
	        LocalDate startDate = report.getStartDate();

	        if (startDate != null) {
	            if ("BETWEEN".equals(searchValue)) {
	                LocalDate endDate = report.getEndDate() != null ? report.getEndDate() : currentDate;
	                if (!searchDate.isBefore(startDate) && !endDate.isBefore(searchDate)) {
	                    searchResults.add(report);
	                }
	            } else if ("EQUAL_TO".equals(searchValue) && startDate.isEqual(searchDate)) {
	                searchResults.add(report);
	            } else if ("NOT_EQUAL".equals(searchValue) && !startDate.isEqual(searchDate)) {
	                searchResults.add(report);
	            } else if ("GREATER_THAN".equals(searchValue) && startDate.isAfter(searchDate)) {
	                searchResults.add(report);
	            } else if ("LESS_THAN".equals(searchValue) && startDate.isBefore(searchDate)) {
	                searchResults.add(report);
	            } else if ("GREATER_THAN_OR_EQUAL_TO".equals(searchValue) && !startDate.isBefore(searchDate)) {
	                searchResults.add(report);
	            } else if ("LESS_THAN_OR_EQUAL_TO".equals(searchValue) && !startDate.isAfter(searchDate)) {
	                searchResults.add(report);
	            }
	        }
	    }
	    
	    return searchResults;
	}

}
