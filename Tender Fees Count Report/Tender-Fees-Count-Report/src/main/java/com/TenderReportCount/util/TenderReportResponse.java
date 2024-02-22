package com.TenderReportCount.util;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TenderReportResponse {

	private String message;
	private int statusCode;
	private Object data;
}
