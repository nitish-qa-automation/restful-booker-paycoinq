# api-spring-boot-framework
API Back-end automation framework using spring dependencies , Junit-5 test runner , allure reporting , and maven build tool

1. Install **Maven** and Import as Maven project in IntelliJ or Eclipse

2. **Framework Structure:**
     - **_apicalls:_** All API Requests and common calls
     - **_data:_** Test data - in ENUMS and payload generation static and dynamically
     - **_dtos:_**  Serialize and de-serialize request and response using Gson and jackson 
     - **_expectations:_** Error Response code 
     - **_providers:_** De-seralized provider methods - all business verification written here used in steps class
     - **_utils:_**  Commonly used methods
     - **_tests:_** All Test cases 
     - **_steps:_** Business logic and assertions defined for test cases
     - **_payloads:_**  Static json files

4. **Command to run all scenarios:**
    ```bash
    mvn clean test  

5. **Setup and Teardown for test:**
    Setup : Token is created - before each test case  
    TearDown: Booking id is deleted , parameters are cleared after every test case
    
6. **Test Data Creation Examples:**
    - **Scenario 1:_**
    Guest Details Data - Enums used for payload and query params
    
    - **Scenario 2:_**
    
     **Dynamically generated test data:_**
        GeneratePayload class used Gson library to generate data as per parameters passed

    - **Scenario 3:_**
        Static json are kept and then jsonObject is used to generate data
      

7. **Report Generation command: Allure**
    ```bash
    allure generate -c target/allure-results
    ```
    For Windows, 
    
    In order to generate a report, we should install Allure command-line interpreter.
    
    Download the latest version as a zip archive from bintray: https://bintray.com/qameta/generic/allure2/2.7.0#files/io%2Fqameta%2Fallure%2Fallure%2F2.7.0
    Unpack the archive.
    Navigate to the bin directory.
    Add allure’s bin directory to system Path variable.
    
    
