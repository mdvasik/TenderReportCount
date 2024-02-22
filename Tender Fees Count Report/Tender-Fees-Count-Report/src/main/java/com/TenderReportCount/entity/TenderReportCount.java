package com.TenderReportCount.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "TenderReport_Count_Wise")
public class TenderReportCount {
	
	@Column(name = "Sr_No")
	private float srNo;

	@Column(name = "tenderid")
	private float tenderId;

	@Column(name = "tenderNo")
	private String tenderNo;

	@Column(name = "tenderBrief")
	private String tenderBrief;

	@Column(name = "TotalEMDPaid")
	private float totalEMDPaid;

	@Column(name = "TotalDocFees")
	private float totalDocFees;

	@Column(name = "TotalEventwiseReg")
	private float totalEventwiseReg;

	@Column(name = "TotalFinalSubmission")
	private float totalFinalSubmission;

	@Column(name = "domainName")
	private String domainName;

	@Column(name = "clientName")
	private String clientName;
	
	@Column(name = "StartDate")
	private LocalDate startDate;
	
	@Column(name = "EndDate")
	private LocalDate endDate;
	
	@Column(name = "PublishDate")
	private LocalDate publishDate;
	
	@Column(name = "Organization")
	private String organization;
	
	@Column(name = "Department")
	private String department;
}
