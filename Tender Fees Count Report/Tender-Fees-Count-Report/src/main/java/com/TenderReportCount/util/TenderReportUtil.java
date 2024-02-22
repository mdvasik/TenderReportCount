package com.TenderReportCount.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class TenderReportUtil {
	
	public static ResponseEntity<String> getResponseEntity(String responseMessage, HttpStatus httpStatus) {
	    return ResponseEntity.status(httpStatus)
	            .body("{\"message\":\"" + responseMessage + "\"}");
	    
	}
	
	 public static TenderReportResponse makeResponse(Object obj, String message, int code) {
		 TenderReportResponse tenderReport = new TenderReportResponse();
		 tenderReport.setMessage(message);
		 tenderReport.setStatusCode(code);
		 tenderReport.setData(obj);
	        return tenderReport;
	 }

}
