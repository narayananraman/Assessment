package com.tds.assessment.Service;

import com.tds.assessment.DAO.RetrieveDataServiceImplementation;
import com.tds.assessment.Entity.Badges;
import com.tds.assessment.Entity.Departments;
import com.tds.assessment.Entity.Employees;
import com.tds.assessment.Entity.JobTitles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

@Service("RetrieveDataService")
public class RetrieveDataService {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    RetrieveDataServiceImplementation retrieveDataServiceImplementation;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Departments> retrieveDepartments() {
        return retrieveDataServiceImplementation.retrieveDepartments();
    }

    public List<Badges> retrieveBadges() {
        return retrieveDataServiceImplementation.retrieveBadges();
    }

    public List<Badges> retrieveActiveBadges() {
        return retrieveDataServiceImplementation.retrieveActiveBadges();
    }

    public Badges retrieveSelectedBadge(int badge_number) {
        return retrieveDataServiceImplementation.retrieveSelectedBadge(badge_number);
    }

    public List<JobTitles> retrieveJobTitles() {
        return retrieveDataServiceImplementation.retrieveJobTitles();
    }

    public List<JobTitles> retrieveJobTitlesByDeptName(String department_name) {
        return retrieveDataServiceImplementation.retrieveJobTitlesByDeptName(department_name);
    }

    public List<Employees> retrieveEmployees() {
        return retrieveDataServiceImplementation.retrieveEmployees();
    }

    public String retrieveCountryName(String country_code) {
        return retrieveDataServiceImplementation.retrieveCountryName(country_code);
    }

    public List<Employees> retrieveActiveEmployees() {
        return retrieveDataServiceImplementation.retrieveActiveEmployees();
    }

    public List<Employees> retrieveSelectedEmployees(String department_name) {
        return retrieveDataServiceImplementation.retrieveSelectedEmployees(department_name);
    }

    public boolean validateBadgeNumber(int b_no) {
        return retrieveDataServiceImplementation.validateBadgeNumber(b_no);
    }

    public boolean validateDepartmentName(String department_name) {
        return retrieveDataServiceImplementation.validateDepartmentName(department_name);
    }

}
