package com.tds.assessment.DAO;

import com.tds.assessment.Entity.Badges;
import com.tds.assessment.Entity.Departments;
import com.tds.assessment.Entity.Employees;
import com.tds.assessment.Entity.JobTitles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.util.List;

@Repository("RetrieveDataServiceImplementation")
public class RetrieveDataServiceImplementation {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Departments> retrieveDepartments() {
        List<Departments> departmentsList = null;
        try {
            departmentsList = jdbcTemplate.query("SELECT * FROM DEPARTMENT",
                    new BeanPropertyRowMapper<Departments>(Departments.class));
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return departmentsList;
    }


    public List<Badges> retrieveBadges() {
        List<Badges> badgesList = null;
        try {
            badgesList = jdbcTemplate.query("SELECT * FROM BADGE",
                    new BeanPropertyRowMapper<Badges>(Badges.class));
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return badgesList;
    }

    public List<Badges> retrieveActiveBadges() {
        List<Badges> badgesList = null;
        try {
            badgesList = jdbcTemplate.query("SELECT * FROM BADGE WHERE BADGE_STATUS = ?",
                    new Object[]{"Active"}, new BeanPropertyRowMapper<Badges>(Badges.class));
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return badgesList;
    }

    public Badges retrieveSelectedBadge(int badge_number) {
        List<Badges> badge = null;
        try {
            badge = jdbcTemplate.query("SELECT * FROM BADGE WHERE BADGE_NUMBER = ?",
                    new Object[]{badge_number}, new BeanPropertyRowMapper<Badges>(Badges.class));
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        if (badge.size() != 0)
            return badge.get(0);
        else
            return null;
    }

    public List<JobTitles> retrieveJobTitles() {
        List<JobTitles> jobTitles = null;
        try {
            jobTitles = jdbcTemplate.query("SELECT * FROM JOB_TITLE",
                    new BeanPropertyRowMapper<JobTitles>(JobTitles.class));
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return jobTitles;
    }

    public List<JobTitles> retrieveJobTitlesByDeptName(String department_name) {
        List<JobTitles> jobTitles = null;
        try {
            jobTitles = jdbcTemplate.query("SELECT * FROM JOB_TITLE WHERE DEPARTMENT_CODE IN (SELECT j.DEPARTMENT_CODE FROM JOB_TITLE j, DEPARTMENT d WHERE j.DEPARTMENT_CODE = d.DEPARTMENT_CODE AND d.DEPARTMENT_NAME = ?)",
                    new Object[]{department_name}, new BeanPropertyRowMapper<JobTitles>(JobTitles.class));
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return jobTitles;
    }

    public List<Employees> retrieveEmployees() {
        List<Employees> employees = null;
        try {
            employees = jdbcTemplate.query("SELECT * FROM EMPLOYEE",
                    new BeanPropertyRowMapper<Employees>(Employees.class));
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public String retrieveCountryName(String country_code) {
        String requestURL = "https://restcountries.eu/rest/v2/alpha/" + country_code;
        String responseBody = new RestTemplate().getForObject(requestURL, String.class);
        responseBody.toString();
        String[] response = responseBody.split(",");
        response[0] = response[0].replace("\"", "");
        return response[0].split(":")[1];
    }

    public List<Employees> retrieveActiveEmployees() {
        List<Employees> employees = null;
        try {
            employees = jdbcTemplate.query("SELECT * FROM EMPLOYEE WHERE LEAVE_DATE IS NULL",
                    new BeanPropertyRowMapper<Employees>(Employees.class));
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public List<Employees> retrieveSelectedEmployees(String department_name) {
        List<Employees> employees = null;
        try {
            employees = jdbcTemplate.query("SELECT * FROM EMPLOYEE WHERE JOB_TITLE_CODE IN (SELECT JOB_TITLE_CODE FROM JOB_TITLE j, DEPARTMENT d WHERE j.DEPARTMENT_CODE=d.DEPARTMENT_CODE AND d.DEPARTMENT_NAME = ?)",
                    new Object[]{department_name}, new BeanPropertyRowMapper<Employees>(Employees.class));
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public boolean validateBadgeNumber(int b_no) {
        List<Badges> badgesList = null;
        try {
            badgesList = jdbcTemplate.query("SELECT * FROM BADGE",
                    new BeanPropertyRowMapper<Badges>(Badges.class));
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < badgesList.size(); i++) {
            if (b_no == badgesList.get(i).getBadge_number()) {
                return true;
            }
        }
        return false;
    }

    public boolean validateDepartmentName(String department_name) {
        List<Departments> departmentsList = null;
        try {
            departmentsList = jdbcTemplate.query("SELECT * FROM DEPARTMENT",
                    new BeanPropertyRowMapper<Departments>(Departments.class));
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < departmentsList.size(); i++) {
            if (department_name.contains(departmentsList.get(i).getDepartment_name())) {
                return true;
            }
        }
        return false;
    }

    public boolean validateDepartmentCode(int d_code) {
        List<Departments> departmentsList = null;
        try {
            departmentsList = jdbcTemplate.query("SELECT * FROM DEPARTMENT",
                    new BeanPropertyRowMapper<Departments>(Departments.class));
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < departmentsList.size(); i++) {
            if (d_code == departmentsList.get(i).getDepartment_code()) {
                return true;
            }
        }
        return false;
    }
}
