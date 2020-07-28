package com.tds.assessment.Controller;

import com.tds.assessment.Entity.*;
import com.tds.assessment.Service.RetrieveDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RetrieveDataController {

    @Autowired
    RetrieveDataService retrieveDataService;

    //Method for Retrieving All Departments
    @GetMapping("/department")
    public ResponseEntity<List<Departments>> getDepartments() {

        HttpHeaders headers = new HttpHeaders();
        List<Departments> departments = retrieveDataService.retrieveDepartments();

        if (departments == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        headers.add("Number Of Records Found", String.valueOf(departments.size()));
        return new ResponseEntity<>(departments, headers, HttpStatus.OK);
    }

    //Method for Retrieving all Badges
    @GetMapping("/badges")
    public ResponseEntity<List<Badges>> getBadges() {

        HttpHeaders headers = new HttpHeaders();
        List<Badges> badges = retrieveDataService.retrieveBadges();

        if (badges == null || badges.size()==0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        headers.add("Number Of Records Found", String.valueOf(badges.size()));
        return new ResponseEntity<>(badges, headers, HttpStatus.OK);
    }

    //Method for retrieving active badges
    @GetMapping("/badges/active")
    public ResponseEntity<List<Badges>> getActiveBadges() {

        HttpHeaders headers = new HttpHeaders();
        List<Badges> badges = retrieveDataService.retrieveActiveBadges();

        if (badges == null || badges.size()==0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        headers.add("Number Of Records Found", String.valueOf(badges.size()));
        return new ResponseEntity<>(badges, headers, HttpStatus.OK);
    }

    //Method to retrieve a specific badge
    @RequestMapping(value = "/badges", params = "badge_number", method = RequestMethod.GET)
    public ResponseEntity<Badges> getSelectedBadge(@RequestParam String badge_number) {
        int b_no = 0;
        HttpHeaders headers = new HttpHeaders();

        try {
             b_no = Integer.parseInt(badge_number);
        } catch (NumberFormatException e) {
            headers.add("Invalid Badge Number  - ", badge_number);
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        if (!retrieveDataService.validateBadgeNumber(b_no)) {
            headers.add("Invalid Badge Number  - ", badge_number);
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        Badges badge = retrieveDataService.retrieveSelectedBadge(b_no);

        if (badge == null) {
            headers.add("No matching records found for Badge  - ", badge_number);
            return new ResponseEntity<>(HttpStatus.PAYMENT_REQUIRED);
        }
        headers.add("Matching records found for Badge  - ", badge_number);
        return new ResponseEntity<>(badge, headers, HttpStatus.OK);
    }

    //Method to retrieve All Job Titles
    @GetMapping("/job_titles")
    public ResponseEntity<List<JobTitles>> getJobTitles() {

        HttpHeaders headers = new HttpHeaders();
        List<JobTitles> jobTitles = retrieveDataService.retrieveJobTitles();

        if (jobTitles == null || jobTitles.size()==0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        headers.add("Number Of Records Found", String.valueOf(jobTitles.size()));
        return new ResponseEntity<>(jobTitles, headers, HttpStatus.OK);
    }

    //Method to retrieve Job Titles by Department Name
    @GetMapping("/job_titles/{department_name}")
    public ResponseEntity<List<JobTitles>> getJobTitlesByDepartmentName(@PathVariable String department_name) {
        HttpHeaders headers = new HttpHeaders();
        if (!retrieveDataService.validateDepartmentName(department_name)) {
            headers.add("Invalid Department Code  - ", department_name);
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        List<JobTitles> jobTitles = retrieveDataService.retrieveJobTitlesByDeptName(department_name);

        if (jobTitles == null || jobTitles.size()==0) {
            headers.add("No matching records found for Department Code - ", department_name);
            return new ResponseEntity<>(HttpStatus.PAYMENT_REQUIRED);
        }
        headers.add("Matching Department Code found for - ", department_name);
        return new ResponseEntity<>(jobTitles, headers, HttpStatus.OK);
    }

    //Method to retrieve all Employees
    @GetMapping("/employees")
    public ResponseEntity<List<Employees>> getEmployees() {

        HttpHeaders headers = new HttpHeaders();
        List<Employees> employees = retrieveDataService.retrieveEmployees();

        if (employees == null || employees.size()==0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        for (int i=0; i<employees.size(); i++) {
            employees.get(i).setCountry_code(
                    retrieveDataService.retrieveCountryName(
                            employees.get(i).getCountry_Code()));
        }
        headers.add("Number Of Records Found", String.valueOf(employees.size()));
        return new ResponseEntity<>(employees, headers, HttpStatus.OK);
    }

    //Method to Retrieve Active Employees
    @GetMapping("/employees/active")
    public ResponseEntity<List<Employees>> getActiveEmployees() {

        HttpHeaders headers = new HttpHeaders();
        List<Employees> employees = retrieveDataService.retrieveActiveEmployees();

        if (employees == null || employees.size()==0) {
            headers.add("No active Employees found ", null);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        for (int i=0; i<employees.size(); i++) {
            employees.get(i).setCountry_code(
                    retrieveDataService.retrieveCountryName(
                            employees.get(i).getCountry_Code()));
        }

        headers.add("Active Employees count : ", String.valueOf(employees.size()));
        return new ResponseEntity<>(employees, headers, HttpStatus.OK);
    }

    //Method to retrieve Employees by Department Name
    @RequestMapping(value = "/employees", params = "department_name", method = RequestMethod.GET)
    public ResponseEntity<List<Employees>> getSelectedEmployees(@RequestParam String department_name) {

        HttpHeaders headers = new HttpHeaders();

        if(!retrieveDataService.validateDepartmentName(department_name)) {
            headers.add("Invalid Department Name  - ", department_name);
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        List<Employees> employees = retrieveDataService.retrieveSelectedEmployees(department_name);

        if (employees == null || employees.size()==0) {
            headers.add("No matching records found for Department Code  - ", department_name);
            return new ResponseEntity<>(HttpStatus.PAYMENT_REQUIRED);
        }

        for (int i=0; i<employees.size(); i++) {
            employees.get(i).setCountry_code(
                    retrieveDataService.retrieveCountryName(
                            employees.get(i).getCountry_Code()));
        }
        headers.add("Matching records found for Department Code  - ", department_name);
        return new ResponseEntity<>(employees, headers, HttpStatus.OK);
    }

}
