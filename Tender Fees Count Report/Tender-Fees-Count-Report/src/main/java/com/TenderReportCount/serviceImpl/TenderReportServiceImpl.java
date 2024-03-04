package com.TenderReportCount.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TenderReportCount.repository.TenderRepository;
import com.TenderReportCount.service.TenderService;
import com.TenderReportCount.util.TenderReportResponse;


@Service
public class TenderServiceImpl implements TenderService {

    @Autowired
    private TenderRepository tenderRepository;

    @Override
    public List<TenderReportResponse> getFilteredTenders(String departmentName, String domainName, LocalDate paymentDate,
                                           LocalDate eventPublishDateFrom, LocalDate bidSubmissionDateFrom,
                                           String eventStatus, Long eventId, String filterOperation) {
    	
        return tenderRepository.findByFilters(departmentName, domainName, paymentDate, eventPublishDateFrom,
                                              bidSubmissionDateFrom, eventStatus, eventId, filterOperation);
    }
}
