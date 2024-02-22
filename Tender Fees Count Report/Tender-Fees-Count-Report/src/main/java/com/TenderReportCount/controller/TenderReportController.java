package com.TenderReportCount.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.TenderReportCount.entity.TenderReportCount;
import com.TenderReportCount.service.TenderReportService;
import com.TenderReportCount.util.TenderReportResponse;

@RestController
@RequestMapping("/tenderReport")
public class TenderReportController {
    
    @Autowired
    private TenderReportService tenderReportService;
    
    @GetMapping("/filterSearch")
    public List<TenderReportResponse> reportCount(
            @RequestParam(required = false , name = "searchKey") String searchKey,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate searchDate,
            @RequestParam(required = false , name = "searchValue") String searchValue
    ) {

    	return this.tenderReportService.tenderReportCount(searchKey, searchDate, searchValue);   	
    }
}
