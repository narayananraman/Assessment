Assesment:

Here I have created a SpringBoot application which hosts REST API Endpoints required per the specifications given.


After starting the program in your local you may access those APIs via http://localhost:8080/ with the below Basic Auth Credentials

Username = admin
Password = admin


Below are the list of API's accessible through the application:

http://localhost:8080/department

http://localhost:8080/badges

http://localhost:8080/badges?badge_number=${badge_number}

http://localhost:8080/job_titles

http://localhost:8080/job_titles/${department_name}

http://localhost:8080/employees

http://localhost:8080/employees/active

http://localhost:8080/employees?department_name=${department_name}



Note: Place holders (${}) must be replaced with actual values


Valid placeholder values:

badge_number - 101 to 120

department_name - Software/Sales/Finance/Marketing/HR

