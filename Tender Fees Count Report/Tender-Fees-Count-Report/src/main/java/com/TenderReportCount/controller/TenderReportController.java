package com.TenderReportCount.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.TenderReportCount.service.TenderService;
import com.TenderReportCount.util.TenderReportResponse;

@RestController
@RequestMapping("/tenderReport")
public class TenderController {
    
    @Autowired
    private TenderService tenderService;
    
    @GetMapping("/filter")
    public ResponseEntity<List<TenderReportResponse>> getFilteredTenders(@RequestParam(required = false, name = "departmentName") String departmentName,
                                                           @RequestParam(required = false, name = "domainName") String domainName,
                                                           @RequestParam(required = false, name = "paymentDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate paymentDate,
                                                           @RequestParam(required = false, name = "eventPublishDateFrom") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate eventPublishDateFrom,
                                                           @RequestParam(required = false, name = "bidSubmissionDateFrom") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate bidSubmissionDateFrom,
                                                           @RequestParam(required = false, name = "eventStatus") String eventStatus,
                                                           @RequestParam(required = false, name = "eventId") Long eventId,
                                                           @RequestParam(required = false, name = "filterOperation") String filterOperation ) {
        List<TenderReportResponse> tenders = tenderService.getFilteredTenders(departmentName, domainName, paymentDate, eventPublishDateFrom, bidSubmissionDateFrom, eventStatus, eventId, filterOperation);
        return ResponseEntity.ok(tenders);
    }
}
