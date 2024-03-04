package com.TenderReportCount.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "Tender_Count_NDA")
public class TenderCount {
	
	@Column(name = "Sr_No")
	private Long srNo;

	@Column(name = "tenderid")
	private String tenderId;

	@Column(name = "tenderNo")
	private String tenderNo;

	@Column(name = "tenderBrief")
	private String tenderBrief;

	@Column(name = "TotalEMDPaid")
	private Double totalEMDPaid;

	@Column(name = "TotalDocFees")
	private Double totalDocFees;

	@Column(name = "TotalEventwiseReg")
	private Integer totalEventwiseReg;

	@Column(name = "TotalFinalSubmission")
	private Integer totalFinalSubmission;

	@Column(name = "domainName")
	private String domainName;

	@Column(name = "clientName")
	private String clientName;
	
	@Column(name = "DepartmentName")
	private String departmentName;
	
	@Column(name = "PaymentDate")
	private LocalDate paymentDate;
	
	@Column(name = "EventPublishDateFrom")
	private LocalDate eventPublishDateFrom;
	
	@Column(name = "BidSubmissionDateFrom")
	private LocalDate bidSubmissionDateFrom;
	
	@Column(name = "EventStatus")
	private String eventStatus;
	
	@Column(name = "Eventid")
	private long eventId;
}
