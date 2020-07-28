package com.tds.assessment.Entity;

public class JobTitles {

    private int job_title_code;
    private String job_title_name;
    private int department_code;

    public int getJob_title_code() {
        return job_title_code;
    }

    public void setJob_title_code(int job_title_code) {
        this.job_title_code = job_title_code;
    }

    public String getJob_title_name() {
        return job_title_name;
    }

    public void setJob_title_name(String job_title_name) {
        this.job_title_name = job_title_name;
    }

    public int getDepartment_code() {
        return department_code;
    }

    public void setDepartment_code(int department_code) {
        this.department_code = department_code;
    }
}
