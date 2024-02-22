package com.TenderReportCount.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.TenderReportCount.entity.TenderReportCount;

@Repository
public class TenderReportRepository {

    private JdbcTemplate jdbcTemplate;

    public TenderReportRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
   

    public List<TenderReportCount> findBySearchingKeyByDate(String searchKey) {
        String sql = "SELECT * FROM TenderReport_Count_Wise";
        return jdbcTemplate.query(sql, new TenderReportCountRowMapper());
    }
    
    private static class TenderReportCountRowMapper implements RowMapper<TenderReportCount> {
    	
        @Override
        public TenderReportCount mapRow(ResultSet rs, int rowNum) throws SQLException {
            TenderReportCount tenderReportCount = new TenderReportCount();
            
            // Map columns of the ResultSet to fields of the TenderReportCount object
            tenderReportCount.setSrNo(rs.getFloat("Sr_No"));
            tenderReportCount.setTenderId(rs.getFloat("tenderid"));
            tenderReportCount.setTenderNo(rs.getString("tenderNo"));
            tenderReportCount.setTenderBrief(rs.getString("tenderBrief"));
            tenderReportCount.setTotalEMDPaid(rs.getFloat("TotalEMDPaid"));
            tenderReportCount.setTotalDocFees(rs.getFloat("TotalDocFees"));
            tenderReportCount.setTotalEventwiseReg(rs.getFloat("TotalEventwiseReg"));
            tenderReportCount.setTotalFinalSubmission(rs.getFloat("TotalFinalSubmission"));
            tenderReportCount.setDomainName(rs.getString("domainName"));
            tenderReportCount.setClientName(rs.getString("clientName"));
            tenderReportCount.setStartDate(rs.getDate("StartDate").toLocalDate());
            tenderReportCount.setEndDate(rs.getDate("EndDate").toLocalDate());
            tenderReportCount.setPublishDate(rs.getDate("PublishDate").toLocalDate());
            tenderReportCount.setOrganization(rs.getString("Organization"));
            tenderReportCount.setDepartment(rs.getString("Department"));  
            
            return tenderReportCount;
        }
    }

    public List<TenderReportCount> findByDepartmentAndOrganization(String searchKey,String searchValue) {
    	String sql = "SELECT * FROM TenderReport_Count_Wise WHERE " + searchKey.toLowerCase() + " ILIKE ?";
    	return jdbcTemplate.query(sql, new Object[]{"%" + searchValue.toLowerCase() + "%"}, new TenderReportCountRowMapper());
    }

    public List<TenderReportCount> findByTenderId( String searchValue) {
    	 String sql = "SELECT * FROM TenderReport_Count_Wise WHERE tenderid = ?";
    	    return jdbcTemplate.query(sql, new Object[]{Double.parseDouble(searchValue)}, new TenderReportCountRowMapper());
    	}
    }
